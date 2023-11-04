package dev.pack.modules.student;

import dev.pack.modules.auth.AuthenticationService;
import dev.pack.modules.enums.FormPurchaseType;
import dev.pack.modules.registration_batch.ChooseBatchDto;
import dev.pack.modules.registration_batch.GetStagingStatusDto;
import dev.pack.modules.student_logs.StudentLogs;
import dev.pack.modules.user.User;
import dev.pack.payloads.HttpResponse;
import dev.pack.services.FilesStorageService;
import dev.pack.utils.Filenameutils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.Optional;
import java.util.StringTokenizer;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v${application.version}/student")
public class StudentController {

    private final StudentService studentService;
    private final ModelMapper modelMapper;
    private final HttpResponse http;

    @PatchMapping(path = "/update")
    public ResponseEntity<?> store(
            @RequestParam(name = "id", defaultValue = "0") int id,
            @RequestBody @Valid StudentDto.onUpdate dto
    ){
        Student student = this.modelMapper.map(dto, Student.class);
        return http.response(HttpStatus.OK.value(), new Date(), this.studentService.createStudent(student, id));
    }

    @PutMapping(path = "/choose-batch")
    public ResponseEntity<?> chooseBatch(@RequestBody @Valid ChooseBatchDto chooseBatchDto) {
        return this.http.response(HttpStatus.OK.value(),new Date(),this.studentService.chooseRegistrationBatch(chooseBatchDto));
    }

    @GetMapping(path = "/get-staging-status")
    public ResponseEntity<?> getStagingStatus(@RequestParam(name = "stagingId") Integer stagingId, @RequestParam(name = "type") FormPurchaseType type) {

        return this.http.response(
                HttpStatus.OK.value(),
                new Date(),
                this.studentService.getCurrentRegistrationStatus(
                        GetStagingStatusDto.builder()
                                .stagingId(stagingId)
                                .type(type)
                                .build()
                )
        );
    }

    @PutMapping(path = "/print-card")
    public ResponseEntity<?> printCard(@RequestBody PrintCardDto printCardDto) {
        return this.http.response(
                HttpStatus.OK.value(),
                new Date(),
                this.studentService.printCard(printCardDto)
        );
    }

    @PutMapping(path = "/choose-major")
    public ResponseEntity<?> chooseMajor(@RequestBody ChooseMajorDto chooseMajorDto) {
        return this.http.response(
                HttpStatus.OK.value(),
                new Date(),
                this.studentService.chooseMajor(chooseMajorDto)
        );
    }

    @PutMapping(path = "/confirm-payment")
    public ResponseEntity<?> confirmPayment(@RequestBody ConfirmPaymentDto paymentDto) {
        return this.http.response(HttpStatus.OK.value(),new Date(),this.studentService.confirmPayment(
                paymentDto
        ));
    }

    @PostMapping(path = "/upload-payment")
    public ResponseEntity<?> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("amount") String nominal,
            @RequestParam("payment_method") String payment_method,
            @RequestParam("type") FormPurchaseType type,
            @RequestParam(value = "bank_name", required = false) String bank_name,
            @RequestParam(value = "bank_account",required = false) String bank_account,
            @RequestParam(value = "bank_user",required = false) String bank_user
    ) {

        return this.http.response(HttpStatus.OK.value(),new Date(),this.studentService.uploadPayment(
           UploadPaymentDto.builder()
                   .file(file)
                   .amount(nominal)
                   .bank_account(bank_account)
                   .bank_name(bank_name)
                   .bank_user(bank_user)
                   .payment_method(payment_method)
                   .type(type)
                   .build()
        ));
    }

    @DeleteMapping(path = "/delete")
    public void delete(@RequestParam("studentId") Integer studentId){
        this.studentService.deleteById(studentId);
    }

    @GetMapping(path = "/get-students-by")
    public ResponseEntity<?> findAllStudentByGrade(@RequestParam("grade") String grade){
        return this.http.response(
                HttpStatus.OK.value(),
                new Date(),
                this.studentService.getAllStudentByGrade(grade)
        );
    }
}
