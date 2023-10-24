package dev.pack.modules.student;

import dev.pack.modules.registration_batch.ChooseBatchDto;
import dev.pack.modules.user.User;
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

}
