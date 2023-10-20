package dev.pack.modules.user;

import dev.pack.modules.admin.enums.Role;
import dev.pack.payloads.HttpResponse;
import dev.pack.payloads.PayloadsResponse;
import jakarta.annotation.security.RolesAllowed;
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

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v${application.version}/user")
@PreAuthorize("hasAnyRole('USER','ADMIN')")
@RolesAllowed({ "ROLE_USER", "ROLE_ADMIN" })
public class UserController {

    private final UserService userService;
    private final ModelMapper model;

    private final HttpResponse http;

    @GetMapping(path = "/index-all")
    @PreAuthorize("hasAnyAuthority('user:read','admin:read')")
    public ResponseEntity<Iterable<?>> index(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Iterable<User> dataUsers = userService.getAllUser(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(dataUsers);
    }

    @GetMapping(path = "/index-by-role")
    @PreAuthorize("hasAnyAuthority('user:read','admin:read')")
    public ResponseEntity<?> index(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "role", defaultValue = "") Role role) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(
                this.userService.getAllUserByRole(role, pageable));
    }


    @PostMapping("/post")
    @PreAuthorize("hasAnyAuthority('user:create','admin:create')")
    public ResponseEntity<?> store(@RequestBody @Valid UserDto bodyDto) {
        User mapData = model.map(bodyDto, User.class);
        User result = userService.createUser(mapData);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new PayloadsResponse(
                        HttpStatus.CREATED.value(),
                        new Date(),
                        result));
    }
}
