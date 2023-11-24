package dev.pack.modules.user;

import dev.pack.modules.enums.Role;
import dev.pack.modules.student.StudentDto;
import jakarta.annotation.Nullable;
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
    private String password;

    @NotNull()
    private Role role;

    public static class StudentCreate{
        @NotEmpty()
        @NotNull()
        private String username;
        @NotEmpty()
        @NotNull()
        private String password;

        @NotNull()
        private StudentDto.OnRegister student;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Profile{
        private String username;
        private String fullname;
        private String name;
        private String address;
        private String school_origin;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateProfile{
        private String password;
    }

}
