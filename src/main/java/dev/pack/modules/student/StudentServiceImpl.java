package dev.pack.modules.student;

import dev.pack.exception.DataNotFoundException;
import dev.pack.exception.DuplicateDataException;
import dev.pack.modules.auth.AuthenticationService;
import dev.pack.modules.lookup.Lookup;
import dev.pack.modules.lookup.LookupRepository;
import dev.pack.modules.registration_batch.ChooseBatchDto;
import dev.pack.modules.registration_batch.RegistrationBatch;
import dev.pack.modules.registration_batch.RegistrationBatchRepository;
import dev.pack.modules.staging.Staging;
import dev.pack.modules.staging.StagingRepository;
import dev.pack.modules.student_logs.StudentLogs;
import dev.pack.modules.student_logs.StudentLogsRepository;
import dev.pack.modules.user.User;
import dev.pack.modules.user.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static dev.pack.constraint.ErrorMessage.*;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;
    private final LookupRepository lookupRepository;
    private final RegistrationBatchRepository registrationBatchRepo;
    private final StudentLogsRepository studentLogsRepository;
    private final StagingRepository stagingRepository;
    private final AuthenticationService authenticationService;

    @Override
    @Transactional
    public Student createStudent(Student bodyStudent, Integer idStudent) {
        checkIfNISNExists(bodyStudent.getNisn());

        Student existingStudent = studentRepository.findById(idStudent)
                .orElseThrow(() -> new DataNotFoundException(STUDENT_ID_NOT_FOUND));

        Lookup dataLookup = getLookupByType(bodyStudent.getMajor());

        existingStudent.setMajor(dataLookup.getType());

        return studentRepository.save(existingStudent);
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    @Transactional
    public RegistrationBatch chooseRegistrationBatch(ChooseBatchDto batchDto) {
        RegistrationBatch registrationBatch = getRegistrationBatchById(batchDto.getBatch_id());

        User user = authenticationService.decodeJwt();
        Staging staging = getStagingByName("Pilih Gelombang PPDB");

        saveStudentLogs(batchDto.getBatch_id(), registrationBatch.getRegistrationPaths().getId(), staging);
        updateStudentBatchAndPath(user.getStudent().getId(), batchDto.getBatch_id(), registrationBatch.getRegistrationPaths().getId());

        return registrationBatch;
    }

    private void checkIfNISNExists(String nisn) {
        studentRepository.findByNisn(nisn)
                .ifPresent(nisnResult -> {
                    throw new DuplicateDataException(NISN_EXISTS);
                });
    }

    private Lookup getLookupByType(String major) {
        return lookupRepository.getLookupByType(major)
                .orElseThrow(() -> new DataNotFoundException(String.format(LOOOKUP_NOT_FOUND,major)));
    }

    private RegistrationBatch getRegistrationBatchById(Integer batchId) {
        return registrationBatchRepo.findById(batchId)
                .orElseThrow(() -> new DataNotFoundException(BATCH_ID_NOT_FOUND));
    }

    private Staging getStagingByName(String name) {
        return stagingRepository.findByName(name)
                .orElseThrow(() -> new DataNotFoundException(STAGING_NAME_NOT_FOUND));
    }

    private void saveStudentLogs(Integer batchId, Integer pathId, Staging staging) {
        studentLogsRepository.save(StudentLogs.builder()
                .batch_id(batchId)
                .path_id(pathId)
                .remark("Melakukan Pendaftaran")
                .staging(staging)
                .build());
    }

    private void updateStudentBatchAndPath(Integer studentId, Integer batchId, Integer pathId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new DataNotFoundException(STUDENT_ID_NOT_FOUND));

        student.setBatch_id(batchId);
        student.setPath_id(pathId);

        studentRepository.save(student);
    }
}
