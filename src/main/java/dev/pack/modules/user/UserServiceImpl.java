package dev.pack.modules.user;

import dev.pack.exception.DataNotFoundException;
import dev.pack.exception.DuplicateDataException;
import dev.pack.modules.auth.AuthenticationService;
import dev.pack.modules.enums.Role;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static dev.pack.constraint.ErrorMessage.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder password;
    private final AuthenticationService authenticationService;

    @Override
    public User createAdmin(User bodyAdminCreate) {
        //Validating
        checkDuplicateUsername(bodyAdminCreate.getUsername());

        String hashedPassword = this.password.encode(bodyAdminCreate.getPassword());
        bodyAdminCreate.setPassword(hashedPassword);

        return this.userRepository.save(bodyAdminCreate);
    }

    @Override
    public Iterable<User> getAllUser(Pageable pageable) {
        return this.userRepository.findAllActive(pageable);
    }

    @Override
    public Iterable<User> getAllUserByRole(Role role, Pageable pageable) {
        var roles = this.userRepository
                .findByRole(role)
                .orElseThrow(() -> new DataNotFoundException(String.format(USER_ROLE_ERROR, role.getAuthorities())));

        return this.userRepository.findAllByRole(roles.getRole());
    }

    @Override
    public User getUserByUsername(String username) {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new DataNotFoundException(USERNAME_NOT_FOUND));
    }

    @Override
    public User updateUser(@NonNull Integer id, User bodyUpdate) {
        User user = this.userRepository
                .findById(id)
                .orElseThrow(
                        () -> new DataNotFoundException(String.format(USER_ID_NOT_FOUND, id))
                );

        checkDuplicateUsernameForUpdate(user.getUsername(), bodyUpdate.getUsername());

        user.setUsername(bodyUpdate.getUsername());
        user.setPassword(password.encode(bodyUpdate.getPassword()));

        return this.userRepository.save(user);
    }

    @Override
    public UserDto.Profile updatePasswordStudent(Integer id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Id siswa tidak ditemukan"));

        User data = this.userRepository.findById(user.getId()).orElseThrow();

        return this.mapToProfileResponse(data);
    }

    @Override
    public void updatePasswordStudent(UserDto.UpdateProfile body, Integer id) {
        var user = this.userRepository.findUserByStudentId(id).orElseThrow(
                () -> new DataNotFoundException("Id akun siswa tidak ditemukan")
        );

        user.setPassword(this.password.encode(body.getPassword()));

        this.userRepository.save(user);
    }

    @Override
    public void updatePasswordAdmin(UserDto.UpdateProfile body, Integer id) {
        var user = this.userRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("Id akun admin tidak ditemukan")
        );

        user.setPassword(this.password.encode(body.getPassword()));

        this.userRepository.save(user);
    }

    private UserDto.UpdateProfile mapToUpdatePasswordProfile(User data){
        UserDto.UpdateProfile.UpdateProfileBuilder profileBuilder = UserDto.UpdateProfile.builder()
                .password(data.getPassword());

        return profileBuilder.build();
    }

    private UserDto.Profile mapToProfileResponse(User data){
        UserDto.Profile.ProfileBuilder profileBuilder = UserDto.Profile.builder()
                .fullname(data.getFullname())
                .username(data.getUsername());

        if(data.getStudent() != null){
            profileBuilder.name(data.getStudent().getName());
            profileBuilder.address(data.getStudent().getAddress());
            profileBuilder.school_origin(data.getStudent().getSchool_origin());
        } else {
            profileBuilder.address(null);
            profileBuilder.school_origin(null);
            profileBuilder.name(null);
        }

        return profileBuilder.build();
    }



    private void checkDuplicateUsername(String username){
        this.userRepository
                .findByUsername(username)
                .ifPresent(user -> {
                    throw new DuplicateDataException(USERNAME_EXISTS);
                });
    }

    private void checkDuplicateUsernameForUpdate(String currentUsername, String newUsername) {
        if (!currentUsername.equals(newUsername)) {
            checkDuplicateUsername(newUsername);
        }
    }
}