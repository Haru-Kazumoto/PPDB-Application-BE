package dev.pack.modules.student;

import dev.pack.modules.dashboard_statistic.BaseDTO;
import dev.pack.modules.enums.FormPurchaseType;
import dev.pack.modules.registration_batch.CountPerBatch;
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

    //TESTING
//    @Query("SELECT COUNT(s) FROM Student s WHERE s.batch_id = :batchId")
    @Query("""
        SELECT COUNT(sl) FROM StudentLogs sl WHERE sl.registrationBatch.id = :batchId
    """)
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

    //TESTING
    // @Query("SELECT COUNT(s) FROM Student s WHERE s.batch_id = :batchId AND s.status = 'PRINT_CARD_PURCHASED'")
    // @Query("SELECT COUNT(s) FROM Student s JOIN StudentLogs sl WHERE s.batch_id = :batchId AND sl.status = 'PRINT_CARD_PURCHASED'")
    @Query("""
        SELECT COUNT(s) FROM Student s, StudentLogs sl WHERE s.batch_id = :batchId AND sl.status = 'PRINT_CARD_PURCHASED'
    """)
    long countConfirmedPaymentStudentsByBatchId(@Param("batchId") Integer batchId);

    @Query(
        value = """
          select
            count(*) as registered,
            count(
              case when student_logs.status = 'PRINT_CARD_PURCHASED'
              or
              student_logs.status = 'PRINT_CARD_RETURNING' then 1 end
            ) as diterima
          from
            students
            inner join student_logs on students.id = student_logs.student_id
          where
            student_logs.batch_id = :batchId
            and student_logs.id = (
              select
                max(f.id)
              from
                student_logs f
              where
                f.student_id = students.id
                and f.batch_id = student_logs.batch_id
            )
                """,
        nativeQuery = true
    )
    CountPerBatch countStudentDetailPerBatch(@Param("batchId") Integer batchId);

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
