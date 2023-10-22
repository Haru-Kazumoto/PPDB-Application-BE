package dev.pack.modules.registration_paths;

import dev.pack.payloads.HttpResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v${application.version}/registration-paths")
public class RegistrationPathsController {

    private final RegistrationPathsService registrationPathsService;
    private final ModelMapper modelMapper;
    private final HttpResponse http;

    /**
     * @[POST] [http://localhost:8080/api/v1/registration-paths/post]
     *
     * @Request:
     * {
     *     "name": "PEMBELIAN FORMULIR",
     *     "type": "PEMBELIAN"
     * }
     *
     * @param dto
     * @return ?
     */
    @PostMapping(path = "/post")
    public ResponseEntity<?> store(@RequestBody @Valid RegistrationPathDto.Create dto){
        RegistrationPaths entity = this.modelMapper.map(dto, RegistrationPaths.class);
        return http.response(CREATED.value(), new Date(), this.registrationPathsService.create(entity));
    }

    /**
     * @[GET] [http://localhost:8080/api/v1/registration-paths/index]
     * @return ?
     */
    @GetMapping(path = "/index")
    public ResponseEntity<?> index(){
        return http.response(OK.value(), new Date(), this.registrationPathsService.index());
    }

    /**
     * @[PATCH] [http://localhost:8080/api/v1/registration-paths/update?id=0]
     *
     *
     * @param id
     * @param dto
     * @return ?
     *
     */
    @PatchMapping(path = "/update")
    public ResponseEntity<?> update(
            @RequestParam(name = "id", defaultValue = "0")int id,
            @RequestBody @Valid RegistrationPathDto.Update dto
    ){
        RegistrationPaths entity = this.modelMapper.map(dto, RegistrationPaths.class);
        return http.response(OK.value(), new Date(), this.registrationPathsService.update(id, entity));
    }

}
