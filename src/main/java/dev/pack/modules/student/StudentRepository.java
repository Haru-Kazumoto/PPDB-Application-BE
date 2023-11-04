package dev.pack.modules.student;

import org.springframework.data.jpa.repository.JpaRepository;
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

    @Query("SELECT COUNT(s) FROM Student s WHERE s.batch_id = :batchId AND s.status = 'PEMBAYARAN_TERKONFIRMASI'")
    long countConfirmedPaymentStudentsByBatchId(@Param("batchId") Integer batchId);

    @Query("SELECT s FROM Student s WHERE s.grade = :grade ORDER BY s.id ASC")
    List<Student> findAllStudentByGrade(@Param("grade") String grade);

}
