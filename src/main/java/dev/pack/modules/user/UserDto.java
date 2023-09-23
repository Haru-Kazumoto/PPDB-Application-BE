package dev.pack.modules.user;

import dev.pack.modules.authorization.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotEmpty()
    @NotNull()
    private String username;

    @NotEmpty()
    @NotNull()
    @Email()
    private String email;

    @NotEmpty()
    @NotNull()
    private String password;

    @NotNull()
    private Role role;

}
