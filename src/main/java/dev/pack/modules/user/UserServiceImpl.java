package dev.pack.modules.user;

import dev.pack.exception.DataNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserPagingRepository userPagingRepository;
    private final PasswordEncoder password;

    @Override
    public User createUser(User bodyCreate) {
        //Validating
        this.userRepository
                .findByUsername(bodyCreate.getUsername())
                .ifPresent(username -> {
                    throw new DataNotFoundException(String.format("Username %s is not found.", bodyCreate.getUsername()));
                });

        this.userRepository
                .findByEmail(bodyCreate.getEmail())
                .ifPresent(email -> {
                    throw new DataNotFoundException(String.format("Email %s is not found.", bodyCreate.getEmail()));
                });

        String hashedPassword = this.password.encode(bodyCreate.getPassword());
        bodyCreate.setPassword(hashedPassword);

        return this.userRepository.save(bodyCreate);
    }

    @Override
    public Iterable<User> getAllUser(Pageable pageable) {
        return this.userPagingRepository.findAll(pageable);
    }

    @Override
    public User updateUser(@NonNull Integer id, User bodyUpdate) {
        User user = this.userRepository
                .findById(id)
                .orElseThrow(
                        () -> new DataNotFoundException(String.format("Data with id %d is not found.", id))
                );

        this.userRepository
                .findByUsername(bodyUpdate.getUsername())
                .ifPresent(username -> {
                    throw new DataNotFoundException(String.format("Username %s is not found.", bodyUpdate.getUsername()));
                });

        this.userRepository
                .findByEmail(bodyUpdate.getEmail())
                .ifPresent(email -> {
                    throw new DataNotFoundException(String.format("Email %s is not found.", bodyUpdate.getEmail()));
                });

        user.setUsername(bodyUpdate.getUsername());
        user.setEmail(bodyUpdate.getEmail());
        user.setPassword(password.encode(bodyUpdate.getPassword()));
        user.setRole(bodyUpdate.getRole());

        return this.userRepository.save(user);
    }

    @Override
    public Map<String, String> deleteUser(@NonNull Integer id) {
        Map<String, String> response = new HashMap<>();

        User user = this.userRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException(String.format("Data with id %d is not found.", id)));
        this.userRepository.delete(user);

        response.put("message", String.format("Data with id %s has success to delete.", id));

        return response;
    }
}
