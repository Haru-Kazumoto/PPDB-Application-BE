package dev.pack.modules.user;

import dev.pack.modules.auth.AuthenticationService;
import dev.pack.modules.enums.Role;
import dev.pack.payloads.HttpResponse;
import dev.pack.payloads.PayloadsResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v${application.version}/admin")
@PreAuthorize("hasAnyRole('ADMIN')")
public class UserController {

    private final UserService userService;
    private final ModelMapper model;
    private final AuthenticationService authenticationService;
    private final HttpResponse http;

    @GetMapping(path = "/index-all-user")
    @PreAuthorize("hasAnyAuthority('admin:read')")
    public ResponseEntity<Iterable<?>> index(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Iterable<User> dataUsers = userService.getAllUser(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(dataUsers);
    }

    @GetMapping(path = "/index")
    @PreAuthorize("hasAnyAuthority('admin:read')")
    public ResponseEntity<?> index(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "role", defaultValue = "USER") Role role) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(
                this.userService.getAllUserByRole(role, pageable));
    }

    @GetMapping(path = "/session")
    public ResponseEntity<?> decodeJWT(){
        return ResponseEntity.status(OK).body(
                new PayloadsResponse(
                        OK.value(),
                        new Date(),
                        authenticationService.decodeJwt()
                )
        );
    }

    @PostMapping("/post")
    @PreAuthorize("hasAnyAuthority('admin:create')")
    public ResponseEntity<?> store(@RequestBody @Valid UserDto bodyDto) {
        User mapData = model.map(bodyDto, User.class);
        User result = userService.createAdmin(mapData);

        return ResponseEntity.status(HttpStatus.CREATED).body(this.http.response(HttpStatus.CREATED.value(), new Date(), result));
    }
}
