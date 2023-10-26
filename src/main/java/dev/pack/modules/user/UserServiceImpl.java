package dev.pack.modules.user;

import dev.pack.exception.DataNotFoundException;
import dev.pack.exception.DuplicateDataException;
import dev.pack.exception.ErrorSoftDelete;
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
