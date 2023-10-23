package dev.pack.modules.registration_batch;

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
@RequestMapping(path = "/api/v${application.version}/admin/registration-batch")
public class RegistrationBatchController {

    private final RegistrationBatchService registrationBatchService;
    private final HttpResponse http;
    private final ModelMapper modelMapper;

    @PostMapping(path = "/post")
    public ResponseEntity<?> store(
            @RequestBody @Valid RegistrationBatchDto dto,
            @RequestParam(name = "regisId", defaultValue = "0") int regisId
    ){
        RegistrationBatch entity = this.modelMapper.map(dto, RegistrationBatch.class);
        return this.http.response(CREATED.value(), new Date(), this.registrationBatchService.store(entity, regisId));
    }

    @GetMapping(path = "/index")
    public ResponseEntity<?> index(){
        return this.http.response(OK.value(), new Date(), this.registrationBatchService.index());
    }

    @PatchMapping(path = "/update")
    public ResponseEntity<?> update(
            @RequestParam(name = "id", defaultValue = "0") int id,
            @RequestBody @Valid RegistrationBatchDto dto
    ){
        RegistrationBatch entity = this.modelMapper.map(dto, RegistrationBatch.class);
        return this.http.response(OK.value(), new Date(), this.registrationBatchService.update(id, entity));
    }

    @DeleteMapping(path = "/delete")
    public void delete(@RequestParam(name = "id", defaultValue = "0")int id){
        this.registrationBatchService.delete(id);
    }

    @GetMapping(path = "/index-total-pendaftar")
    public ResponseEntity<?> indexTotalPendaftar(){
        return this.http.response(OK.value(), new Date(), this.registrationBatchService.getTotalPendaftarPerBatch());
    }

    @GetMapping(path = "/get-total")
    public Long getTotal(){
        return this.registrationBatchService.getTotalPendaftar();
    }
}
