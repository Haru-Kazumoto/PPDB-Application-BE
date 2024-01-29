package dev.pack.modules.student;

import dev.pack.modules.registration_batch.RegistrationBatch;
import dev.pack.modules.registration_batch.dto.ChooseBatchDto;
import dev.pack.modules.registration_batch.dto.GetStagingStatusDto;
import dev.pack.modules.student.dto.ChooseMajorDto;
import dev.pack.modules.student.dto.GetStudentAchievementDto;
import dev.pack.modules.student.dto.PaymentDto;
import dev.pack.modules.student.dto.PrintCardDto;
import dev.pack.modules.student.dto.UpdateBioDto;
import dev.pack.modules.student_logs.StudentLogs;
import dev.pack.modules.student_payments.StudentPayments;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface StudentService {

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

    List<StudentPayments> getAllStudentPayments(Integer batchId, Integer studentId);

    void exportExcelDataStudent(HttpServletResponse response, Integer batchId) throws IOException;

}
