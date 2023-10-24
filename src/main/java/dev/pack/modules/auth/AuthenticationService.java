package dev.pack.modules.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.pack.config.JwtService;
import dev.pack.exception.DataNotFoundException;
import dev.pack.exception.DuplicateDataException;
import dev.pack.exception.UserNotFoundException;
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
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

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

  @Value("${application.security.jwt.secret-key}")
  private String SIGNING_KEY;

  public AuthenticationResponse registerAdmin(RegisterRequest.Admin request) {
    var user = User.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(request.getRole())
            .build();

    var savedUser = userRepository.save(user);
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);

    saveUserToken(savedUser, jwtToken);

    return AuthenticationResponse.builder()
            .accessToken(jwtToken)
            .refreshToken(refreshToken)
            .role(String.valueOf(savedUser.getRole()))
            .build();
  }

  public AuthenticationResponse registerStudent(RegisterRequest.User request){
    this.userRepository.findByUsername(request.getUsername()).ifPresent((username) -> {
      throw new DuplicateDataException("Nomor whatsapp telah di registerasi.");
    });

    Roles role = this.roleRepository.findRolesByName("User").orElseThrow(() -> new DataNotFoundException("Error"));

    User user = User.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(request.getRole())
            .role_id(role)
            .build();

    user = this.userRepository.save(user); // Simpan User terlebih dahulu

    var student = Student.builder()
            .name(request.getStudentData().getName())
            .address(request.getStudentData().getAddress())
            .school_origin(request.getStudentData().getSchool_origin())
            .userId(user)
            .build();

    this.studentRepository.save(student); // Simpan Student setelah User disimpan

    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);

    saveUserToken(user, jwtToken);

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
            orElseThrow(() -> new UserNotFoundException("Username / Password Salah"));


    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);

    revokeAllUserTokens(user);
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
    var user = userRepository.findByUsername(userData.getUsername()).orElseThrow(() -> new DataNotFoundException("Sesi tidak ditemukan, harap login kembali"));


    return User.builder()
            .id(user.getId())
            .role_id(user.getRole_id())
            .username(user.getUsername())
            .student(user.getStudent())
            .build();

  }

  public User findUserByUsername(String username){
    return this.userRepository.findByUsername(username)
            .orElseThrow(() -> new DataNotFoundException("Username not found."));
  }

  public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    final String refreshToken;
    final String userEmail;

    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
      return;
    }

    refreshToken = authHeader.substring(7);
    userEmail = jwtService.extractUsername(refreshToken);

    if (userEmail != null) {
      var user = this.userRepository
              .findByUsername(userEmail)
              .orElseThrow();

      if (jwtService.isTokenValid(refreshToken, user)) {
        var accessToken = jwtService.generateToken(user);

        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);

        var authResponse = AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
      }
    }
  }
}
