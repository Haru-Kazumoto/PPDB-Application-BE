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
public class UserController {

    private final UserService userService;
    private final ModelMapper model;
    private final AuthenticationService authenticationService;
    private final HttpResponse http;

    @GetMapping(path = "/index-all-user")
    public ResponseEntity<Iterable<?>> index(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Iterable<User> dataUsers = userService.getAllUser(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(dataUsers);
    }

    @GetMapping(path = "/index")
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

    @GetMapping(path = "/get-by")
    public ResponseEntity<?> getByUsername(@RequestParam("username")String username){
        return this.http.response(
                HttpStatus.OK.value(),
                new Date(),
                this.userService.getUserByUsername(username)
        );
    }

    @PostMapping("/post")
    public ResponseEntity<?> store(@RequestBody @Valid UserDto bodyDto) {
        User mapData = model.map(bodyDto, User.class);
        User result = userService.createAdmin(mapData);

        return ResponseEntity.status(HttpStatus.CREATED).body(this.http.response(HttpStatus.CREATED.value(), new Date(), result));
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfileData(){
        return http.response(
                OK.value(),
                new Date(),
                this.userService.updateUser(null, null)
        );
    }

    //NEW FEATURE
    @PutMapping("/update-password/student")
    public void updatePasswordStudent(@RequestBody UserDto.UpdateProfile body, @RequestParam("studentId") Integer id){
        this.userService.updatePasswordStudent(body, id);
    }

    @PutMapping("/update-password/admin")
    public void updatePasswordAdmin(@RequestBody UserDto.UpdateProfile body, @RequestParam("id") Integer id){
        this.userService.updatePasswordAdmin(body, id);
    }
}
