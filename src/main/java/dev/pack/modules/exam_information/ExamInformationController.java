package dev.pack.modules.exam_information;

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
@RequestMapping(path = "/api/v${application.version}/user/exam-information")
public class ExamInformationController {

    private final ExamInformationService examInformationService;
    private final ModelMapper modelMapper;
    private final HttpResponse http;

    @PostMapping(path = "/create")
    private ResponseEntity<?> createExamInformation(@RequestBody @Valid ExamInformationDto.Create dto){
        ExamInformation entity = this.modelMapper.map(dto, ExamInformation.class);
        var result = this.examInformationService.create(entity);

        return this.http.response(
                HttpStatus.CREATED.value(),
                new Date(),
                result
        );
    }

    @GetMapping("/index")
    private ResponseEntity<?> indexAllExamInformationByPathId(@RequestParam("batchId") Integer batchId){
        return this.http.response(
                HttpStatus.OK.value(),
                new Date(),
                this.examInformationService.index(batchId)
        );
    }

    @GetMapping("/index-all")
    private ResponseEntity<?> indexAll(){
        return this.http.response(
                HttpStatus.OK.value(),
                new Date(),
                this.examInformationService.indexAll()
        );
    }

    @PatchMapping("/update")
    private ResponseEntity<?> updateDataWithId(@RequestBody @Valid ExamInformationDto.Update dto, @RequestParam("id") Integer id){
        ExamInformation updatedEntity = this.modelMapper.map(dto, ExamInformation.class);

        return this.http.response(
                HttpStatus.OK.value(),
                new Date(),
                this.examInformationService.update(updatedEntity, id)
        );
    }

    @DeleteMapping("/delete")
    private void deleteDataWithId(@RequestParam("id") Integer id){
        this.examInformationService.delete(id);
    }

}
