package dev.pack.utils;

import dev.pack.exception.DataNotFoundException;
import dev.pack.modules.student.Student;
import dev.pack.modules.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ExportCardStudentController {

    private final StudentRepository studentRepository;

    @GetMapping(
            value = "api/public/generate-card",
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    public @ResponseBody byte[] generateCard(@RequestParam(value = "id") Integer studentId) throws Exception{
        Student dataStudent = this.studentRepository.findById(studentId).orElseThrow(() -> new DataNotFoundException("Id siswa tidak ditemukan."));

        String uuid = UUID.randomUUID().toString();
        Path pathHtml = Paths.get("/tmp/"+uuid+".html");
        Path pathPdf = Paths.get("/tmp/"+uuid+".pdf");

        try{
            String htmlContent = new Scanner(
                    Objects.requireNonNull(
                            getClass().getClassLoader().getResourceAsStream("dev/pack/tmp/kartu-peserta-smk.html")
                    ), StandardCharsets.UTF_8
            )
                    .useDelimiter("\\A")
                    .next();

            htmlContent = htmlContent
                    .replace("$formulirId", dataStudent.getFormulirId())
                    .replace("$name", dataStudent.getName())
                    .replace("$phone", dataStudent.getPhone())
                    .replace("$address", dataStudent.getAddress())
                    .replace("$school_origin", dataStudent.getSchool_origin());

            Files.write(pathHtml,htmlContent.getBytes());

            Process generateToPdf = Runtime.getRuntime().exec("wkhtmltopdf " + pathHtml.toString() + " " + pathPdf.toString() );

            generateToPdf.waitFor();

            return Files.readAllBytes(pathPdf);
        } finally {
            Files.delete(pathHtml);
            Files.delete(pathPdf);
        }
    }

}
