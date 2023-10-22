package dev.pack.modules.auth;

import dev.pack.payloads.HttpResponse;
import dev.pack.payloads.PayloadsResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(value = "/api/v${application.version}/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8000/")
public class AuthenticationController {

  private final AuthenticationService service;
  private final HttpResponse http;

  @PostMapping("/register")
  public ResponseEntity<PayloadsResponse> registerAdmin(@RequestBody RegisterRequest.Admin request) {
    return ResponseEntity.status(CREATED).body(
            new PayloadsResponse(
                    CREATED.value(),
                    new Date(),
                    this.service.registerAdmin(request)
            )
    );
  }

  @PostMapping("/register-student")
  public ResponseEntity<PayloadsResponse> registerStudent(@RequestBody RegisterRequest.User request) {
    return ResponseEntity.status(CREATED).body(
            new PayloadsResponse(
                    CREATED.value(),
                    new Date(),
                    this.service.registerStudent(request)
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

  @GetMapping(path = "/decode")
  public ResponseEntity<?> decodeJWT(@RequestParam(name = "token", defaultValue = "") String token){
    return ResponseEntity.status(OK).body(
            new PayloadsResponse(
                    OK.value(),
                    new Date(),
                    service.decodeJwt(token)
            )
    );
  }

  @PostMapping("/refresh-token")
  public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
    service.refreshToken(request, response);
  }

  @GetMapping(path = "/findByUsername")
  public ResponseEntity<?> findByusername(@RequestParam(name = "username", defaultValue = "") String username){
    return http.response(HttpStatus.OK.value(), new Date(), this.service.findUserByUsername(username));
  }
}
