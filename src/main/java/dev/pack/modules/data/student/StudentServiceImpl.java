package dev.pack.modules.data.student;

import dev.pack.exception.DuplicateDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;

    @Override
    public Student createStudent(Student bodyStudent) {
        this.studentRepository.findByNisn(bodyStudent.getNisn()).ifPresent((nisn) -> {
            throw new DuplicateDataException("NISN has already exists");
        });

        return this.studentRepository.save(bodyStudent);
    }

    @Override
    public List<Student> getAll() {
        return null;
    }
}
