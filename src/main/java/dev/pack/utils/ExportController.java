package dev.pack.utils;

import dev.pack.modules.registration_batch.RegistrationBatch;
import dev.pack.modules.student.Student;
import dev.pack.modules.student.StudentRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/public")
public class ExportController {

    private final ExportService exportService;
    private final StudentRepository studentRepository;

    @GetMapping(path = "/generate-excel")
    public void generateExcel(HttpServletResponse response) throws IOException {
        RegistrationBatch registrationBatch = new RegistrationBatch();

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment;filename=student_%s_data.xls",registrationBatch.getBatchCode());

        response.setHeader(headerKey, headerValue);

        exportService.generateExcel(response);
    }

    @GetMapping(path = "/excel-student")
    public void generateExcelStudent(HttpServletResponse response) throws IOException {
        List<Student> dataStudents = this.studentRepository.findAll();

        List<String> headers = Arrays.asList(
                "Id",
                "formulir-id",
                "name",
                "phone",
                "surname",
                "address"
        );

        List<List<Object>> data = new ArrayList<>();

        for(Student student : dataStudents){
            List<Object> rowData = new ArrayList<>();

            rowData.add(student.getId());
            rowData.add(student.getName());
            rowData.add(student.getPhone());
            rowData.add(student.getGrade());

            data.add(rowData);
        }

        RegistrationBatch registrationBatch = new RegistrationBatch();

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment;filename=student_%s_data.xls",registrationBatch.getBatchCode());

        response.setHeader(headerKey, headerValue);

        exportService.generateExcelCustomHeader(response,"Data Siswa", headers, data);
    }
}
