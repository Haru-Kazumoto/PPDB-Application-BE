package dev.pack.modules.registration_general_information;

import dev.pack.payloads.HttpResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v${application.version}/admin/registration-info")
public class RegistrationGeneralInformationController {

    private final RegistrationGeneralInformationService registrationGeneralInformationService;
    private final ModelMapper modelMapper;
    private final HttpResponse http;

    @PostMapping(path = "/post")
    public ResponseEntity<?> create(@RequestBody @Valid RegistrationGeneralInformationDto.onCreate dto){
        RegistrationGeneralInformation data = this.modelMapper.map(dto, RegistrationGeneralInformation.class);
        return this.http.response(
                HttpStatus.CREATED.value(),
                new Date(),
                this.registrationGeneralInformationService.create(data)
        );
    }

    @GetMapping(path = "/index")
    public ResponseEntity<?> index(@RequestParam("regisPathsId") Integer regisPathsId){
        return this.http.response(
                HttpStatus.OK.value(),
                new Date(),
                this.registrationGeneralInformationService.index(regisPathsId)
        );
    }

    @PatchMapping(path = "/update")
    public ResponseEntity<?> update(
            @RequestParam("id") Integer id,
            @RequestBody @Valid RegistrationGeneralInformationDto.onUpdate dto
    ){
        RegistrationGeneralInformation entity = this.modelMapper.map(dto, RegistrationGeneralInformation.class);

        return this.http.response(
                HttpStatus.OK.value(),
                new Date(),
                this.registrationGeneralInformationService.update(id, entity)
        );
    }

    @DeleteMapping(path = "/delete")
    public void delete(
            @RequestParam("id") Integer id
    ){
        this.registrationGeneralInformationService.delete(id);
    }
}
