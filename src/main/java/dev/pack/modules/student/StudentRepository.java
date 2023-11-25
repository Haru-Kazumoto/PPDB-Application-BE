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

    @Query("SELECT COUNT(s) FROM Student s ORDER BY s.id ASC")
    long countAllStudents();

//    @Query("SELECT r.id, COUNT(s) FROM Student s JOIN s.registrationPaths r WHERE r.id = :pathId GROUP BY r.id")
//    Integer countStudentsByRegistrationPath(@Param("pathId") Integer pathId);

    @Query("SELECT r.type, COUNT(s) FROM Student s JOIN s.registrationPaths r WHERE r.type = :type GROUP BY r.type")
    List<RegistrationPaths> countStudentsByRegistrationPathAndType(@Param("type") FormPurchaseType type);


    @Query("SELECT COUNT(s) FROM Student s WHERE s.batch_id = :batchId AND s.status = 'PEMBAYARAN_TERKONFIRMASI'")
    long countConfirmedPaymentStudentsByBatchId(@Param("batchId") Integer batchId);

    @Query("SELECT s FROM Student s WHERE s.grade = :grade ORDER BY s.id ASC")
    List<Student> findAllStudentByGrade(@Param("grade") String grade);

    @Query("SELECT s FROM StudentPayments s WHERE s.batch_id = :batchId AND s.student.id = :studentId ORDER BY s.id ASC")
    List<StudentPayments> findAllStudentPayments(
            @Param("batchId") Integer batchId,
            @Param("studentId") Integer studentid
    );

    @Query("SELECT s FROM Student s WHERE s.id = :studentId AND s.batch_id = :batchId ORDER BY s.id ASC")
    Student findStudentByIdAnAndBAndBatch_id(
            @Param("studentId") Integer studentId,
            @Param("batchId") Integer batchId
    );

    @Query("SELECT s FROM Student s WHERE s.status = :status ORDER BY s.id ASC")
    List<Student> findAllStudentByStatus(@Param("status") String status);

    @Query("SELECT COUNT(s) FROM Student s WHERE s.status = :status")
    Integer countStudentByStatus(@Param("status") String status);

//    @Query("SELECT s FROM Student s " +
//            "JOIN StudentLogs sl ON s.id = sl.student.id " +
//            "JOIN RegistrationPaths rp ON rp.id = sl.path_id " +
//            "WHERE sl.id = (SELECT MAX(sl2.id) FROM StudentLogs sl2 WHERE sl2.student.id = s.id) " +
//            "AND rp. = :batchId")
//    List<Student> findAllStudentHasRegistered(@Param("batchId") Integer batchId);
}
