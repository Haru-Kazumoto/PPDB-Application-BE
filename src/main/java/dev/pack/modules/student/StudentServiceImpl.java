package dev.pack.modules.student;

import dev.pack.exception.DataNotFoundException;
import dev.pack.exception.DuplicateDataException;
import dev.pack.modules.lookup.LookupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;
    private final LookupRepository lookupRepository;

    @Override
    public Student createStudent(Student bodyStudent, Integer idStudent) {
        this.studentRepository.findByNisn(bodyStudent.getNisn()).ifPresent((nisn) -> {
            throw new DuplicateDataException("NISN has already exists");
        });

        this.studentRepository.findById(idStudent).orElseThrow(() -> new DataNotFoundException("Id student not found."));

        var dataLookup = this.lookupRepository.getLookupByType(bodyStudent.getMajor());
        if(dataLookup.isEmpty()) throw new DataNotFoundException(String.format("Data lookup [%s] not found.", bodyStudent.getMajor()));

        return this.studentRepository.save(bodyStudent);
    }

    @Override
    public List<Student> getAll() {
        return null;
    }
}
