package dev.pack.modules.user;

import lombok.NonNull;
import org.springframework.data.domain.Pageable;


import java.util.Map;

public interface UserService {

    User createUser(User bodyCreate);
    Iterable<User> getAllUser(Pageable pageable);
    User updateUser(@NonNull Integer id, User bodyUpdate);
    Map<String, String> deleteUser(@NonNull Integer id);

}
