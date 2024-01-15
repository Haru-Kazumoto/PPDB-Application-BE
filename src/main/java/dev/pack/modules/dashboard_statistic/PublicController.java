package dev.pack.modules.dashboard_statistic;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.pack.modules.student.Student;
import dev.pack.modules.student.StudentRepository;
import dev.pack.modules.student.StudentService;
import dev.pack.utils.excel.ExcelService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/public")
public class PublicController {

    private final StudentService studentService;

    @GetMapping(path = "/get-students-to-excel")
    public void exportStudentsToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=data_seluruh_siswa.xls";

        response.setHeader(headerKey, headerValue);

        this.studentService.exportExcelAllDataStudent(response);
    }

    // private final StudentRepository studentRepository;
    // private final ExcelService excelService;

    // public void exportExcelAllDataStudent(HttpServletResponse response) throws IOException {
    //     try{
    //         List<Student> students = this.studentRepository.findAll();

    //         List<String> headers = Arrays.asList(
    //                 "Formulir-id",
    //                 "Pendaftar ke",
    //                 "Nama siswa",
    //                 "Nomor telepon",
    //                 "Alamat",
    //                 "Jenis kelamin",
    //                 "Agama",
    //                 "Asal sekolah",
    //                 "Jurusan",
    //                 "Tanggal mendaftar"
    //         );

    //         List<List<Object>> data = new ArrayList<>();
    //         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    //         Year year = Year.now();

    //         for(Student student : students){
    //             List<Object> rowData = new ArrayList<>();

    //             rowData.add(student.getFormulirId());
    //             rowData.add(student.getLastInsertedNumber());
    //             rowData.add(student.getName());
    //             rowData.add(student.getPhone());
    //             rowData.add(student.getAddress());
    //             rowData.add(student.getGender());
    //             rowData.add(student.getReligion());
    //             rowData.add(student.getSchool_origin());
    //             rowData.add(student.getMajor());
    //             rowData.add(dateFormat.format(student.getRegistrationDate()));

    //             data.add(rowData);
    //         }

    //         this.excelService.generateExcelCustomHeader(response,String.format("Data siswa %s",year), headers, data);
    //     } catch (Exception err){
    //         throw new IOException(err);
    //     }
    // }
}
