package dev.pack.modules.newdata.user;

import dev.pack.exception.DataNotFoundException;
import dev.pack.exception.DuplicateDataException;
import dev.pack.exception.ErrorSoftDelete;
import dev.pack.modules.enums.Role;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder password;

    //Creating student
    @Override
    public User createStudent(User bodyUserCreate) {
        this.userRepository.findByUsername(bodyUserCreate.getUsername()).ifPresent((username) -> {
            throw new DuplicateDataException("Nomor whatsapp telah ada!");
        });

        return null;
    }

    @Override
    public User createAdmin(User bodyAdminCreate) {
        //Validating
        this.userRepository
                .findByUsername(bodyAdminCreate.getUsername())
                .ifPresent(username -> {
                    throw new DuplicateDataException("Username has already exists.");
                });

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
                .orElseThrow(() -> new DataNotFoundException(String.format("Role only have : ", role.getAuthorities())));

        return this.userRepository.findAllByRole(roles.getRole());
    }

    @Override
    public User getUserByUsername(String username) {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new DataNotFoundException("Username not found."));
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
                    throw new DuplicateDataException("Username has already exists.");
                });


        user.setUsername(bodyUpdate.getUsername());
        user.setPassword(password.encode(bodyUpdate.getPassword()));
//        user.setRole(bodyUpdate.getRole()); <- once has set role, cannot update.

        return this.userRepository.save(user);
    }

    @Override
    public void softDelete(Integer id) {
        User data = this.userRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Id not found."));
        if(data.getDeletedAt() != null) throw new ErrorSoftDelete("Data has been deactive.");
//        this.softDeleteRepository.softDeleteById(data.getId());
    }
}
