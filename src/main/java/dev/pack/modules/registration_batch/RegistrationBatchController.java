package dev.pack.modules.registration_batch;

import dev.pack.modules.enums.FormPurchaseType;
import dev.pack.modules.enums.Grade;
import dev.pack.modules.student.StudentRepository;
import dev.pack.modules.student.StudentService;
import dev.pack.modules.user.UserService;
import dev.pack.payloads.HttpResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
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
    private final StudentService studentService;

    private final HttpResponse http;
    private final ModelMapper modelMapper;

    @PostMapping(path = "/post")
    public ResponseEntity<?> store(@RequestBody @Valid RegistrationBatchDto.Create dto){
        RegistrationBatch entity = this.modelMapper.map(dto, RegistrationBatch.class);
        return this.http.response(CREATED.value(), new Date(), this.registrationBatchService.store(entity));
    }

    @GetMapping(path = "/index")
    public ResponseEntity<?> index(@RequestParam(name = "pathId", defaultValue = "") int pathId){
        return this.http.response(OK.value(), new Date(), this.registrationBatchService.index(pathId));
    }

    @GetMapping(path = "/getByType")
    public ResponseEntity<?> getByType(@RequestParam(name = "type")FormPurchaseType type) {
        return this.http.response(OK.value(),new Date(),this.registrationBatchService.getAllBatchByType(type));
    }

    @GetMapping(path = "/get-detail-student-pembelian")
    public ResponseEntity<?> getDetailStudentPembelian(@RequestParam("studentId") Integer studentId){
        return this.http.response(
                HttpStatus.OK.value(),
                new Date(),
                this.studentService.getDetailStudentPembelian(studentId)
        );
    }

    @GetMapping(path = "/get-detail-student-pengembalian")
    public ResponseEntity<?> getDetailStudentPengembalian(@RequestParam("studentId")Integer studentId){
        return this.http.response(
                HttpStatus.OK.value(),
                new Date(),
                this.studentService.getDetailStudentPengembalian(studentId)
        );
    }

    @GetMapping(path = "/get-batch-by-grade")
    public ResponseEntity<?> getAllBatchByGrade(@RequestParam("grade")Grade grade){
        return this.http.response(
                HttpStatus.OK.value(),
                new Date(),
                this.registrationBatchService.getAllBatchByGrade(grade)
        );
    }

    @PatchMapping(path = "/update")
    public ResponseEntity<?> update(
            @RequestParam(name = "id", defaultValue = "0") int id,
            @RequestBody @Valid RegistrationBatchDto.Update dto
    ){
        RegistrationBatch entity = this.modelMapper.map(dto, RegistrationBatch.class);
        return this.http.response(OK.value(), new Date(), this.registrationBatchService.update(id, entity));
    }

    @GetMapping(path = "/index-path")
    public ResponseEntity<?> indexByGelombangIsOpen(@RequestParam("is")Boolean is){
        return this.http.response(OK.value(), new Date(), this.registrationBatchService.getAllGelombangWhereIsOpen(is));
    }

    @DeleteMapping(path = "/delete")
    public Map<String, String> delete(@RequestParam(name = "id", defaultValue = "0")int id){
        return this.registrationBatchService.delete(id);
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

    @GetMapping(path = "/get")
    public ResponseEntity<?> getById(@RequestParam("id") Integer id){
        return this.http.response(
                OK.value(),
                new Date(),
                this.registrationBatchService.getById(id)
        );
    }

    @GetMapping(path = "/get-students")
    public ResponseEntity<?> getStudentsById(
            @RequestParam("batchId") Integer batchId,
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "1") Integer size
    ){
        return this.http.response(
                OK.value(),
                new Date(),
                this.registrationBatchService.getStudentByBatchId(
                        batchId,
                        PageRequest.of(page, size)
                )
        );
    }

    @GetMapping(path = "/detail")
    public ResponseEntity<?> getDetailStudentByStudentId(@RequestParam("studentId") Integer studentId){
        return this.http.response(
                OK.value(),
                new Date(),
                this.studentService.getStudentById(studentId)
        );
    }

    @GetMapping(path = "/count-data")
    public ResponseEntity<?> countDataStudents(@RequestParam("batchId") Integer batchId){
        return this.http.response(
                OK.value(),
                new Date(),
                this.registrationBatchService.countStudent(batchId)
        );
    }

    @DeleteMapping(path = "/delete-student-from-batch")
    public Map<String, String> deleteStudentFromBatchIdByStudentId(@RequestParam("studentId") Integer studentId){
        return this.registrationBatchService.deleteStudentFromBatch(studentId);
    }

}
