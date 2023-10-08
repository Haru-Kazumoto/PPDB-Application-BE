package dev.pack.modules.auth;

import dev.pack.payloads.PayloadsResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v${application.version}/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/register")
  public ResponseEntity<PayloadsResponse> register(@RequestBody RegisterRequest request) {
    return ResponseEntity.status(CREATED).body(
            new PayloadsResponse(
                    CREATED.value(),
                    new Date(),
                    this.service.register(request)
            )
    );
  }

  @PostMapping("/login")
  public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
    return ResponseEntity.status(ACCEPTED).body(
            new PayloadsResponse(
                    ACCEPTED.value(),
                    new Date(),
                    service.authenticate(request)
            )
    );
  }

  @PostMapping("/refresh-token")
  public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
    service.refreshToken(request, response);
  }
}
