package dev.pack.modules.data.student;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v${application.version}/student/")
public class StudentController {

    private final StudentRepository studentRepository;



}
