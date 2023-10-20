package dev.pack.modules.user;

import dev.pack.payloads.HttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/public")
public class TestController {
    
    private final UserService userService;
    private final HttpResponse http;
    
    @GetMapping(path = "/findByUsername")
    // @PreAuthori/ze("hasAnyAuthority('user:read','admin:read')")
    public ResponseEntity<?> findByusername(@RequestParam(name = "username", defaultValue = "") String username){
        return http.response(HttpStatus.OK.value(), new Date(), this.userService.getUserByUsername(username));
    }
}