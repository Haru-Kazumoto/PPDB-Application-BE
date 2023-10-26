package dev.pack.modules.student;

import dev.pack.modules.registration_batch.ChooseBatchDto;
import dev.pack.modules.registration_batch.GetStagingStatusDto;
import dev.pack.modules.registration_batch.RegistrationBatch;
import dev.pack.modules.student_logs.StudentLogs;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Student createStudent(Student bodyStudent, Integer idStudent);
    List<Student> getAll(); //developing

    RegistrationBatch chooseRegistrationBatch(ChooseBatchDto batchDto);

    Optional<StudentLogs> getCurrentRegistrationStatus(GetStagingStatusDto stagingStatusDto);

    StudentLogs uploadPayment(UploadPaymentDto dto);

}
