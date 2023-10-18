package dev.pack.modules.user;

import dev.pack.modules.admin.enums.Role;
import lombok.NonNull;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User createUser(User bodyCreate);
    Iterable<User> getAllUser(Pageable pageable);
    Iterable<User> getAllUserByRole(Role role,Pageable pageable);
    User getUserByUsername(String username);
    User updateUser(@NonNull Integer id, User bodyUpdate);
    void softDelete(Integer id);
//    List<User> getAllDataHasDeactive(@NonNull Integer id);
}   
