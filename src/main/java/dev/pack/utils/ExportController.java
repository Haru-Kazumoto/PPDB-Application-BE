package dev.pack.utils;

import dev.pack.modules.registration_batch.RegistrationBatchRepository;
import dev.pack.modules.student.StudentRepository;
import dev.pack.modules.student.StudentService;
import dev.pack.utils.excel.ExcelService;
import dev.pack.utils.pdf.PdfService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/public")
public class ExportController {

    private final ExcelService excelService;
    private final PdfService pdfService;
    private final StudentRepository studentRepository;
    private final StudentService studentService;
    private final RegistrationBatchRepository registrationBatchRepository;

    @GetMapping(path = "/get-student-to-excel")
    public void exportToExcel(HttpServletResponse response, @RequestParam("batchId") Integer batchId)throws IOException {
        var registrationBatch = this.registrationBatchRepository.findById(batchId).orElseThrow();

        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment;filename=data_siswa_%s_%s.xls",new Date(), registrationBatch.getBatchCode());

        response.setHeader(headerKey, headerValue);

        this.studentService.exportExcelDataStudent(response,batchId);
    }
}
