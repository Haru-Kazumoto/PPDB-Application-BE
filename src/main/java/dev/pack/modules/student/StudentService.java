package dev.pack.modules.student;

import dev.pack.modules.registration_batch.ChooseBatchDto;
import dev.pack.modules.registration_batch.GetStagingStatusDto;
import dev.pack.modules.registration_batch.RegistrationBatch;
import dev.pack.modules.student_logs.StudentLogs;
import dev.pack.modules.student_payments.StudentPayments;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Student createStudent(Student bodyStudent, Integer idStudent);

    List<Student> getAll(); //developing

    RegistrationBatch chooseRegistrationBatch(ChooseBatchDto batchDto);

    StudentOffsetResponse getCurrentRegistrationStatus(GetStagingStatusDto stagingStatusDto);

    StudentLogs uploadPayment(UploadPaymentDto dto);

    StudentLogs confirmPayment(ConfirmPaymentDto dto);

//    Student getStudentById(Integer studentId, Integer batchId);
    Student getStudentById(Integer studentId);

    StudentLogs updateBio(UpdateBioDto updateBioDto);

    StudentLogs chooseMajor(ChooseMajorDto major);

    StudentLogs printCard(PrintCardDto printCardDto);

    void deleteById(Integer studentId);

    List<Student> getAllStudentByGrade(String grade);

    List<StudentPayments> getAllStudentPayments(Integer batchId, Integer studentId);
}
