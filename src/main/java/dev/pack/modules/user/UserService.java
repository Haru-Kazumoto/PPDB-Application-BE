package dev.pack.modules.user;

import dev.pack.modules.enums.Role;
import lombok.NonNull;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User createAdmin(User bodyAdminCreate);

    Iterable<User> getAllUser(Pageable pageable);

    Iterable<User> getAllUserByRole(Role role,Pageable pageable);

    User getUserByUsername(String username);

    User updateUser(@NonNull Integer id, User bodyUpdate);

    UserDto.Profile updatePasswordStudent(Integer id);

    void updatePasswordStudent(UserDto.UpdateProfile body, Integer id);

    void updatePasswordAdmin(UserDto.UpdateProfile body, Integer id);
}
