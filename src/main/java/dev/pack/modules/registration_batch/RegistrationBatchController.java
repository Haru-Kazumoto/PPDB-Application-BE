package dev.pack.modules.registration_batch;

import dev.pack.modules.enums.FormPurchaseType;
import dev.pack.payloads.HttpResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

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
    public ResponseEntity<?> index(@RequestParam(name = "pathId", defaultValue = "") int pathId){
        return this.http.response(OK.value(), new Date(), this.registrationBatchService.index(pathId));
    }

    @GetMapping(path = "/getByType")
    public ResponseEntity<?> getByType(@RequestParam(name = "type")FormPurchaseType type) {
        return this.http.response(OK.value(),new Date(),this.registrationBatchService.getAllBatchByType(type));
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
    public Map<String, String> delete(@RequestParam(name = "id", defaultValue = "0")int id){
        return this.registrationBatchService.delete(id);
    }

    @GetMapping(path = "/count")
    public ResponseEntity<?> countStudentsByBatch(@RequestParam("regisPathsId")Integer regisPathsId){
        return this.http.response(
                OK.value(),
                new Date(),
                this.registrationBatchService.countStudents(regisPathsId)
        );
    }

    @GetMapping(path = "/get-batch-by-pathsId")
    public ResponseEntity<?> getAllRegisBatchByRegisPathsId(
            @RequestParam(name = "pathsId", defaultValue = "0") int pathsId
    ){
        return this.http.response(
                OK.value(),
                new Date(),
                this.registrationBatchService.getAllGelombangByPathsId(pathsId)
        );
    }
}
