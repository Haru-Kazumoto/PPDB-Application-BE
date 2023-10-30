package dev.pack.modules.registration_batch;

import dev.pack.modules.enums.FormPurchaseType;
import dev.pack.modules.student.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationBatchRepository extends JpaRepository<RegistrationBatch, Integer> {

    @Query(value = """
            SELECT new dev.pack.modules.registration_batch.CountBatchRegistrar(rb.id, rb.name, COUNT(s))\s
            FROM RegistrationBatch rb\s
            LEFT JOIN rb.students s GROUP BY rb.id\s
    """)
    List<CountBatchRegistrar> findTotalPendaftarPerBatch();

    @Query("""
        select s from RegistrationBatch s
        inner join s.registrationPaths d
        where d.type = :type
    """)
    List<RegistrationBatch> getAllByType(FormPurchaseType type);

    @Query(value = """
            SELECT new dev.pack.modules.registration_batch.RegistrationBatch(
            rb.id,\s
            rb.name,
            rb.index,
            rb.max_quota,
            rb.start_date,
            rb.end_date,
            rb.bank_name,
            rb.bank_user,
            rb.price,
            rb.bank_account,\s
            COUNT(s)
            )\s
            FROM RegistrationBatch rb\s
            LEFT JOIN rb.students s\s
            WHERE rb.registrationPaths.id = :regisPathId
            GROUP BY rb.id,rb.name,
            rb.index,
            rb.max_quota,
            rb.start_date,
            rb.end_date,
            rb.bank_name,
            rb.bank_user,
            rb.price,
            rb.bank_account
    """)
    List<RegistrationBatch> findTotalPendaftarPerBatchModel(@Param("regisPathId") Integer regisPathId);

    @Transactional
    @Modifying
    @Query(value = """
        UPDATE RegistrationBatch rb\s
        SET rb.countStudent = (SELECT COUNT(s) FROM rb.students s WHERE rb.registrationPaths.id = :regisPathsId)
        WHERE rb.registrationPaths.id = :regisPathsId
    """)
    void updateCountStudent(@Param("regisPathsId") Integer regisPathsId);

    @Query("SELECT rb FROM RegistrationBatch rb WHERE rb.registrationPaths.id = :regisPathsId")
    List<RegistrationBatch> findByRegistrationPathsId(@Param("regisPathsId") Integer regisPathsId);

    @Query("SELECT rb FROM RegistrationBatch rb WHERE rb.isOpen = :condition")
    List<RegistrationBatch> findRegistrationBatchByIsOpen(@Param("condition") Boolean condition);

    @Query("""
        SELECT rb FROM RegistrationBatch rb WHERE rb.registrationPaths.type = :type
    """)
    List<RegistrationBatch> findRegistrationBatchByPathType(@Param("type") FormPurchaseType type);

    @Query("""
        SELECT s FROM Student s WHERE s.batch_id = :batchId
    """)
    List<Student> findAllStudentByBatchId(Integer batchId);
}
