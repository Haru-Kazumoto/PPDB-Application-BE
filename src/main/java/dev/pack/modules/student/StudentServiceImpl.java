package dev.pack.modules.student;

import dev.pack.exception.DataNotFoundException;
import dev.pack.exception.DuplicateDataException;
import dev.pack.modules.auth.AuthenticationService;
import dev.pack.modules.lookup.LookupRepository;
import dev.pack.modules.registration_batch.ChooseBatchDto;
import dev.pack.modules.registration_batch.RegistrationBatch;
import dev.pack.modules.registration_batch.RegistrationBatchRepository;
import dev.pack.modules.user.User;
import dev.pack.modules.user.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;
    private final LookupRepository lookupRepository;
    private final RegistrationBatchRepository registrationBatchRepo;

    private final AuthenticationService authenticationService;

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

    @Override
    @Transactional
    public RegistrationBatch chooseRegistrationBatch(ChooseBatchDto batchDto) {
        RegistrationBatch registrationBatch = this.registrationBatchRepo.findById(batchDto.getBatch_id()).orElseThrow(() -> new DataNotFoundException("Gelombang tidak ditemukan"));

        User user = this.authenticationService.decodeJwt();

        this.studentRepository.save(
            Student.builder()
                    .id(user.getStudent().getId())
                    .batch_id(batchDto.getBatch_id())
                    .path_id(registrationBatch.getRegistrationPaths().getId())
                    .build()
        );

        return registrationBatch;
    }
}
