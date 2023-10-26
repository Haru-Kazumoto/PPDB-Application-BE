package dev.pack.modules.auth;

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
public class AuthenticationRequest {

  @NotEmpty()
  @NotNull()
  private String username;

  @NotEmpty()
  @NotNull()
  String password;


  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class RefreshToken {
    @NotEmpty
    @NotNull
    String refresh_token;
  }
}
