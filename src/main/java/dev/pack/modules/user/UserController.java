package dev.pack.modules.user;

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

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v${application.version}/user")
@PreAuthorize("hasRole('USER')")
public class UserController {

    private final UserService userService;
    private final ModelMapper model;

    @GetMapping(path = "/get/{size}/{page}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<Iterable<?>> getAll(
            @PathVariable("size") int size,
            @PathVariable("page") int page){
        Pageable pageable = PageRequest.of(page, size);

        Iterable<User> dataUsers = userService.getAllUser(pageable);

        return ResponseEntity.status(HttpStatus.OK).body(dataUsers);
    }

    @PostMapping("/post")
    @PreAuthorize("hasAuthority('user:create')")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserDto bodyDto){
        User mapData = model.map(bodyDto, User.class);
        User result = userService.createUser(mapData);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new PayloadsResponse(
                        HttpStatus.CREATED.value(),
                        new Date(),
                        result
                )
        );
    }
}
