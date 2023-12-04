package dev.pack.modules.registration_paths;

import dev.pack.modules.enums.FormPurchaseType;
import dev.pack.modules.enums.Grade;
import dev.pack.payloads.HttpResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v${application.version}/admin/registration-paths")
public class RegistrationPathsController {

    private final RegistrationPathsService registrationPathsService;
    private final ModelMapper modelMapper;
    private final HttpResponse http;

    @PostMapping(path = "/post-multi")
    public ResponseEntity<?> storeMultiModel(@RequestBody @Valid RegistrationPathDto.Create dto){
        RegistrationPaths entity = this.modelMapper.map(dto, RegistrationPaths.class);
        return http.response(CREATED.value(), new Date(), this.registrationPathsService.create(entity));
    }

    @PostMapping(path = "/post-single")
    public ResponseEntity<?> storeSingleModel(@RequestBody @Valid RegistrationPathDto.Create dto){
        return null;
    }

    @GetMapping(path = "/index")
    public ResponseEntity<?> index(@RequestParam("grade") Grade grade){
        return http.response(OK.value(), new Date(), this.registrationPathsService.index(grade));
    }

    @GetMapping(path = "/get-path-by-session")
    public ResponseEntity<?> getById(){
        return http.response(OK.value(), new Date(), this.registrationPathsService.getPathByStudentSession());
    }

    @GetMapping(path = "/get-type")
    public ResponseEntity<?> getAllByType(@RequestParam(name = "type")FormPurchaseType type){
        return http.response(OK.value(), new Date(), this.registrationPathsService.indexByTypeRegistrationPaths(type));
    }

    @GetMapping(path = "/count-registrar")
    public ResponseEntity<?> getAllByType(){
        return http.response(OK.value(), new Date(), this.registrationPathsService.getPathWithTotalStudents());
    }

    @PatchMapping(path = "/update")
    public ResponseEntity<?> update(
            @RequestParam(name = "id", defaultValue = "0")int id,
            @RequestBody @Valid RegistrationPathDto.Update dto
    ){
        RegistrationPaths entity = this.modelMapper.map(dto, RegistrationPaths.class);
        return http.response(OK.value(), new Date(), this.registrationPathsService.update(id, entity));
    }

    @DeleteMapping(path = "/delete")
    public void delete(@RequestParam(name = "id", defaultValue = "0")int id){
        this.registrationPathsService.delete(id);
    }

    @GetMapping(path = "/index-recursion")
    public ResponseEntity<?> indexWithRecursion(){
        return this.http.response(
                HttpStatus.OK.value(),
                new Date(),
                this.registrationPathsService.indexAllWithRecursion()
        );
    }
}