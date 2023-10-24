package dev.pack.modules.registration_paths;

import dev.pack.modules.enums.FormPurchaseType;
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
    public ResponseEntity<?> index(){
        return http.response(OK.value(), new Date(), this.registrationPathsService.index());
    }

    @GetMapping(path = "/get-type")
    public ResponseEntity<?> getAllByType(@RequestParam(name = "type")FormPurchaseType type){
        return http.response(OK.value(), new Date(), this.registrationPathsService.indexByTypeRegistrationPaths(type));
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
}