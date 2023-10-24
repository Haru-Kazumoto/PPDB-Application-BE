package dev.pack.modules.auth;

import dev.pack.modules.enums.Role;
import dev.pack.modules.student.StudentDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

public class RegisterRequest {

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Admin{
    @UniqueElements
    @NotEmpty(message = "Username cannot be empty!")
    private String username;

    @NotEmpty(message = "Fullname cannot be empty!")
    private String fullname;

    @NotEmpty(message = "Password cannot be empty!")
    private String password;

  }

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class User{

    @NotEmpty(message = "Username cannot be empty!")
    private String username;

    @NotEmpty(message = "Password cannot be empty!")
    private String password;

    @NotNull(message = "Role is required")
    private Role role;

    @NotNull(message = "student data cannot be null!")
    private StudentDto.OnRegister studentData;

  }

}
