package dev.pack.modules.student;

import dev.pack.modules.enums.FormPurchaseType;
import dev.pack.modules.registration_batch.ChooseBatchDto;
import dev.pack.modules.registration_batch.GetStagingStatusDto;
import dev.pack.payloads.HttpResponse;
import dev.pack.utils.StringUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.Date;

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

    @GetMapping(path = "/get-payments")
    public ResponseEntity<?> findAllStudentPayments(
            @RequestParam("batchId") Integer batchId,
            @RequestParam("studentId") Integer studentId
    ){
        return this.http.response(HttpStatus.OK.value(), new Date(), this.studentService.getAllStudentPayments(batchId,studentId));
    }

    @PutMapping(path = "/update-bio")
    public ResponseEntity<?> updateCV(
            @RequestParam(value = "profile_picture") MultipartFile profile_picture,
            @RequestParam(value = "family_card") MultipartFile family_card,
            @RequestParam(value = "birth_card") MultipartFile birth_card,
            @RequestParam(value = "nisn",required = false) String nisn,
            @RequestParam("phone") String phone,
            @RequestParam("name") String name,
            @RequestParam(value = "surname",required = false) String surname,
            @RequestParam("gender") String gender,
            @RequestParam("religion") String religion,
            @RequestParam("birth_place") String birth_place,
            @RequestParam("birth_date") String birth_date,
            @RequestParam("address") String address,
            @RequestParam("province") String province,
            @RequestParam("city") String city,
            @RequestParam("district") String district,
            @RequestParam("sub_district") String sub_district,
            @RequestParam(value = "postal_code",required = false) String postal_code,
            @RequestParam("school_origin") String school_origin,
            @RequestParam("dad_name") String dad_name,
            @RequestParam("dad_phone") String dad_phone,
            @RequestParam(value = "dad_job",required = false) String dad_job,
            @RequestParam(value = "dad_address",required = false) String dad_address,
            @RequestParam("mother_name") String mother_name,
            @RequestParam("mother_phone") String mother_phone,
            @RequestParam(value = "mother_job",required = false) String mother_job,
            @RequestParam(value = "mother_address",required = false) String mother_address
    ) throws ParseException {
        return this.http.response(HttpStatus.OK.value(),new Date(),this.studentService.updateBio(
                UpdateBioDto.builder()
                        .name(name)
                        .profile_picture(profile_picture)
                        .family_card(family_card)
                        .birth_card(birth_card)
                        .nisn(nisn)
                        .phone(phone)
                        .surname(surname)
                        .gender(gender)
                        .religion(religion)
                        .birth_place(birth_place)
                        .birth_date(StringUtils.toDate(birth_date))
                        .address(address)
                        .province(province)
                        .city(city)
                        .district(district)
                        .sub_district(sub_district)
                        .postal_code(postal_code)
                        .school_origin(school_origin)
                        .dad_name(dad_name)
                        .dad_phone(dad_phone)
                        .dad_job(dad_job)
                        .dad_address(dad_address)
                        .mother_name(mother_name)
                        .mother_phone(mother_phone)
                        .mother_job(mother_job)
                        .mother_address(mother_address)
                        .build()
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
