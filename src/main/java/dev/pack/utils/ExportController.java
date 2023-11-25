package dev.pack.utils;

import com.lowagie.text.DocumentException;
import dev.pack.modules.registration_batch.RegistrationBatch;
import dev.pack.modules.registration_batch.RegistrationBatchRepository;
import dev.pack.modules.student.Student;
import dev.pack.modules.student.StudentRepository;
import dev.pack.modules.student.StudentService;
import dev.pack.utils.excel.ExcelService;
import dev.pack.utils.pdf.PdfService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/public")
public class ExportController {

    private final ExcelService excelService;
    private final PdfService pdfService;
    private final StudentRepository studentRepository;
    private final StudentService studentService;
    private final RegistrationBatchRepository registrationBatchRepository;

    @GetMapping(path = "/generate-excel")
    public void generateExcel(HttpServletResponse response) throws IOException {
        RegistrationBatch registrationBatch = new RegistrationBatch();

        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=student_%s_data.xls",registrationBatch.getBatchCode());

        response.setHeader(headerKey, headerValue);

        excelService.generateExcel(response);
    }

    @GetMapping(path = "/excel-student")
    public void generateExcelStudent(HttpServletResponse response) throws IOException {
        List<Student> dataStudents = this.studentRepository.findAll();

//        List<String> headers = Arrays.asList(
//                "Id",
//                "Formulir-id",
//                "Pendaftar ke",
//                "Nama siswa",
//                "Nomor telepon",
//                "Alamat",
//                "Jenjang",
//                "Jenis kelamin",
//                "Agama",
//                "Asal sekolah",
//                "Jurusan",
//                "Tanggal mendaftar"
//        );

        List<String> headers = Arrays.asList(
                "Id",
                "Nama siswa",
                "Nomor telepon",
                "Alamat",
                "Jenjang",
                "Asal sekolah"
        );

        List<List<Object>> data = new ArrayList<>();

        for(Student student : dataStudents) {
            List<Object> rowData = new ArrayList<>();

            rowData.add(student.getId());
            rowData.add(student.getName());
            rowData.add(student.getPhone());
            rowData.add(student.getAddress());
            rowData.add(student.getGrade());
            rowData.add(student.getSchool_origin());

            data.add(rowData);
        }

        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=student_data_%s.xls",currentDate);

        response.setHeader(headerKey, headerValue);

        excelService.generateExcelCustomHeader(response,"Data Siswa", headers, data);
    }

    @GetMapping(path = "/pdf-student")
    public void generatePdfStudent(HttpServletResponse response) throws DocumentException, IOException{
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        String currentDateTime = dateFormat.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_student_"+currentDateTime+".pdf";

        response.setHeader(headerKey, headerValue);
        
        this.pdfService.generate(response);
    }

    @GetMapping(path = "/get-student-to-excel")
    public void exportToExcel(HttpServletResponse response, @RequestParam("batchId") Integer batchId)throws IOException {
        var registrationBatch = this.registrationBatchRepository.findById(batchId).orElseThrow();

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment;filename=student_%s_data.xls",registrationBatch.getBatchCode());

        response.setHeader(headerKey, headerValue);

        this.studentService.exportExcelDataStudent(response,batchId);
    }
}
