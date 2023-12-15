package dev.pack.modules.student;

import dev.pack.modules.dashboard_statistic.BaseDTO;
import dev.pack.modules.enums.FormPurchaseType;
import dev.pack.modules.registration_paths.RegistrationPaths;
import dev.pack.modules.student_logs.StudentLogs;
import dev.pack.modules.student_payments.StudentPayments;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Optional<Student> findByNisn(String nisn);

    @Query("SELECT COUNT(s) FROM Student s WHERE s.batch_id = :batchId")
    long countStudentsByBatchId(@Param("batchId") Integer batchId);

    @Query("SELECT s FROM Student s WHERE s.batch_id = :batchId AND s.lastInsertedNumber > :deletedRunningNumber")
    List<Student> findStudentsToAdjust(@Param("batchId") Integer batchId, @Param("deletedRunningNumber") String deletedRunningNumber);

    @Modifying
    @Query("UPDATE Student s SET s.batch_id = null, s.path_id = null, s.formulirId = NULL, s.lastInsertedNumber = NULL WHERE s.id = :studentId")
    void deleteStudentFromBatchByStudentId(@Param("studentId") Integer studentId);

    @Query("""
        SELECT s FROM Student s WHERE s.batch_id = :batchId
    """)
    List<Student> findAllStudentByBatchId(Integer batchId);

    @Query("""
        SELECT s FROM Student s WHERE s.path_id = :pathId
    """)
    List<Student> findAllStudentByPathId(Integer pathId);

    @Query("SELECT COUNT(s) FROM Student s WHERE s.batch_id = :batchId AND s.status = 'PEMBAYARAN_TERKONFIRMASI'")
    long countConfirmedPaymentStudentsByBatchId(@Param("batchId") Integer batchId);

    @Query("SELECT s FROM Student s WHERE s.grade = :grade ORDER BY s.id ASC")
    List<Student> findAllStudentByGrade(@Param("grade") String grade);

    @Query("SELECT s FROM StudentPayments s WHERE s.batch_id = :batchId AND s.student.id = :studentId ORDER BY s.id ASC")
    List<StudentPayments> findAllStudentPayments(
            @Param("batchId") Integer batchId,
            @Param("studentId") Integer studentid
    );

    @Query("SELECT COUNT(s) FROM Student s WHERE s.status = :status")
    Integer countStudentByStatus(@Param("status") String status);
}
