package dev.pack.modules.student;

import dev.pack.modules.registration_batch.ChooseBatchDto;
import dev.pack.modules.registration_batch.GetStagingStatusDto;
import dev.pack.modules.registration_batch.RegistrationBatch;
import dev.pack.modules.student_logs.StudentLogs;
import dev.pack.modules.student_payments.StudentPayments;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface StudentService {

    Student createStudent(Student bodyStudent, Integer idStudent);

    List<Student> getAll(); //developing

    RegistrationBatch chooseRegistrationBatch(ChooseBatchDto batchDto);

    StudentOffsetResponse getCurrentRegistrationStatus(GetStagingStatusDto stagingStatusDto);

    StudentLogs uploadPayment(UploadPaymentDto dto);

    List<StudentAchievement> getStudentAchievement(GetStudentAchievementDto dto);

    ResponseStudentDto getDetailStudentPembelian(Integer studentId);

    Student getDetailStudentPengembalian(Integer studentId);

    StudentLogs confirmPayment(PaymentDto.Confirm dto);

    Student getStudentById(Integer studentId);

    StudentLogs fillBio(UpdateBioDto updateBioDto);

    Map<String, String> updateBio(Integer id, UpdateBioDto updateBioDto);

    StudentLogs chooseMajor(ChooseMajorDto major);

    StudentLogs printCard(PrintCardDto printCardDto);

    void deleteById(Integer studentId);

    List<Student> getAllStudentByGrade(String grade);

    List<StudentPayments> getAllStudentPayments(Integer batchId, Integer studentId);

    void exportExcelDataStudent(HttpServletResponse response, Integer batchId) throws IOException;

}
