package dev.pack.modules.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.pack.config.JwtService;
import dev.pack.exception.DataNotFoundException;
import dev.pack.exception.DuplicateDataException;
import dev.pack.exception.UserNotFoundException;
import dev.pack.modules.enums.Grade;
import dev.pack.modules.enums.Role;
import dev.pack.modules.role.RoleRepository;
import dev.pack.modules.role.Roles;
import dev.pack.modules.student.Student;
import dev.pack.modules.student.StudentRepository;
import dev.pack.modules.token.Token;
import dev.pack.modules.token.TokenRepository;
import dev.pack.modules.token.TokenType;
import dev.pack.modules.user.User;
import dev.pack.modules.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.io.IOException;
import java.util.Arrays;

import static dev.pack.constraint.ErrorMessage.*;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;

  private final UserRepository userRepository;
  private final StudentRepository studentRepository;
  private final TokenRepository tokenRepository;
  private final RoleRepository roleRepository;

  public User registerAdmin(RegisterRequest.Admin request) {
    Roles role = this.roleRepository.findRolesByName("Admin").orElseThrow(() -> new DataNotFoundException("Data tidak ditemukan untuk Role Admin"));

    var user = User.builder()
            .username(request.getUsername())
            .fullname(request.getFullname())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(Role.ADMIN)
            .role_id(role)
            .build();

    return userRepository.save(user);
  }

  @Transactional(rollbackOn = {
          DataNotFoundException.class
  })
  public AuthenticationResponse registerStudent(RegisterRequest.User request){
    this.userRepository.findByUsername(request.getUsername()).ifPresent((username) -> {
      throw new DuplicateDataException(NUMBER_ALREADY_EXISTS);
    });

    Roles role = this.roleRepository.findRolesByName("User").orElseThrow(() -> new DataNotFoundException(ROLE_NOT_FOUND));

    User user = User.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(request.getRole())
            .role_id(role)
            .build();

    user = this.userRepository.save(user); // Simpan User terlebih dahulu

    Grade grade = request.getStudentData().getGrade();

    if(!grade.equals(Grade.SMP) && !grade.equals(Grade.SMK)){
      throw new DataNotFoundException("Grade not valid");
    }

    var student = Student.builder()
            .name(request.getStudentData().getName())
            .grade(grade)
            .address(request.getStudentData().getAddress())
            .phone(request.getUsername())
            .school_origin(request.getStudentData().getSchool_origin())
            .userId(user)
            .build();

    this.studentRepository.save(student); // Simpan Student setelah User disimpan

    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);

    saveUserToken(user, jwtToken);
    saveUserToken(user,refreshToken);

    return AuthenticationResponse.builder()
            .accessToken(jwtToken)
            .refreshToken(refreshToken)
            .role(String.valueOf(user.getRole()))
            .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getUsername(),
            request.getPassword()
        )
    );

    var user = userRepository.
            findByUsername(request.getUsername()).
            orElseThrow(() -> new UserNotFoundException("Username atau password invalid"));

    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);

    revokeAllUserTokens(user);

    saveUserToken(user,refreshToken);
    saveUserToken(user, jwtToken);

    return AuthenticationResponse.builder()
            .accessToken(jwtToken)
            .refreshToken(refreshToken)
            .role(String.valueOf(user.getRole()))
            .build();
  }

  private void saveUserToken(User user, String jwtToken) {
    var token = Token.builder()
        .userId(user)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();

    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());

    if (validUserTokens.isEmpty()) return;

    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });

    tokenRepository.saveAll(validUserTokens);
  }

  public User decodeJwt(){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    var userData =  (org.springframework.security.core.userdetails.User)authentication.getPrincipal();
    var user = userRepository.findByUsername(userData.getUsername()).orElseThrow(() -> new DataNotFoundException(AUTHENTICATION_BAD_SESSION));


    return User.builder()
            .id(user.getId())
            .role_id(user.getRole_id())
            .username(user.getUsername())
            .fullname(user.getFullname())
            .student(user.getStudent())
            .build();

  }

  public User findUserByUsername(String username){
    return this.userRepository.findByUsername(username)
            .orElseThrow(() -> new DataNotFoundException(USERNAME_NOT_FOUND));
  }

  public AuthenticationResponse refreshToken(AuthenticationRequest.RefreshToken token)  {
    tokenRepository.findByToken(token.getRefresh_token()).orElseThrow(() -> new DataNotFoundException("Unauthenticated"));

    var userEmail = jwtService.extractUsername(token.getRefresh_token());

    // if token not resulting a username
    if(userEmail.isEmpty()) {
      throw new BadCredentialsException("Unauthenticated");
    }

    var user = this.userRepository
            .findByUsername(userEmail)
            .orElseThrow(() -> new DataNotFoundException("Unauthenticated"));

    // if token not valid
    if (!jwtService.isTokenValid(token.getRefresh_token(), user)) {
      throw new BadCredentialsException("Unauthenticated");
    }

    var accessToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);

    // cabut dulu kabeh
    revokeAllUserTokens(user);

    saveUserToken(user, accessToken);
    saveUserToken(user,refreshToken);

    return AuthenticationResponse.builder()
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .role(String.valueOf(user.getRole()))
            .build();
  }
}
