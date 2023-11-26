package dev.pack.modules.registration_batch;

import dev.pack.modules.enums.FormPurchaseType;
import dev.pack.modules.student.Student;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    @Transactional
    @Modifying
    @Query("UPDATE RegistrationBatch rb SET rb.countStudent = (SELECT COUNT(s) FROM Student s WHERE s.batch_id = :batchId) WHERE rb.id = :batchId")
    void countStudentFromBatch(@Param("batchId") Integer batchId);

    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    @Query("SELECT COUNT(s) FROM Student s WHERE s.batch_id = :batchId")
    long countStudentsForRunningNumber(@Param("batchId") Integer batchId);

    @Query("SELECT p FROM RegistrationBatch p WHERE p.path_id = :pathId")
    List<RegistrationBatch> findAllByPathId(@Param("pathId") Integer pathId);

//    @Query(value = """
//            SELECT new dev.pack.modules.registration_batch.RegistrationBatch(
//            rb.id,\s
//            rb.name,
//            rb.index,
//            rb.max_quota,
//            rb.batchCode,
//            rb.start_date,
//            rb.end_date,
//            rb.bank_name,
//            rb.bank_user,
//            rb.price,
//            rb.bank_account,\s
//            COUNT(s)
//            )\s
//            FROM RegistrationBatch rb\s
//            LEFT JOIN rb.students s\s
//            WHERE rb.registrationPaths.id = :regisPathId
//            GROUP BY rb.id,rb.name,
//            rb.index,
//            rb.max_quota,
//            rb.batchCode,
//            rb.start_date,
//            rb.end_date,
//            rb.bank_name,
//            rb.bank_user,
//            rb.price,
//            rb.bank_account
//    """)
//    List<RegistrationBatch> findTotalPendaftarPerBatchModel(@Param("regisPathId") Integer regisPathId);
//    @Query(value = """
//            SELECT new dev.pack.modules.registration_batch.RegistrationBatch(
//            rb.id,\s
//            rb.name,
//            rb.index,
//            rb.max_quota,
//            rb.batchCode,
//            rb.start_date,
//            rb.end_date,
//            rb.bank_name,
//            rb.bank_user,
//            rb.price,
//            rb.bank_account,\s
//            COUNT(s)
//            )\s
//            FROM RegistrationBatch rb\s
//            LEFT JOIN rb.students s\s
//            WHERE rb.id = :registBatchId
//            GROUP BY rb.id,rb.name,
//            rb.index,
//            rb.max_quota,
//            rb.batchCode,
//            rb.start_date,
//            rb.end_date,
//            rb.bank_name,
//            rb.bank_user,
//            rb.price,
//            rb.bank_account
//    """)
//    Optional<RegistrationBatch> getRegistrationBatchById(@Param("registBatchId") Integer registBatchId);

    @Query("SELECT b FROM RegistrationBatch b WHERE b.id = :regisBatchId")
    Optional<RegistrationBatch> getRegistrationBatchById(@Param("regisBatchId") Integer regisBatchId);


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

    @Query(value = """
        select s.id,s.name,s.phone,s.registration_date,sl.status from students s
        left join student_logs sl on sl.student_id = s.id
        where sl.batch_id = :batchId and\s
        sl.id = (select max(id) from student_logs where student_id = s.id and batch_id = sl.batch_id)
        group by s.id,s.name,s.phone,s.registration_date,sl.status
        ORDER BY s.id \n-- #pageable
    
    """, countQuery = """
        select count(*)\s
        from students s
        left join student_logs sl on sl.student_id = s.id
        where sl.batch_id = :batchId and\s
        sl.id = (select max(id) from student_logs where student_id = s.id and batch_id = sl.batch_id)
        group by s.id,s.name,s.phone,s.registration_date,sl.status
    """,
            nativeQuery = true)
    Page<GetAllStudentsByBatch> findAllStudentByBatchId(Integer batchId,Pageable pageable);

    @Query(value = """
        SELECT\s
            rb.id,rb.name,\s
            rb.start_date,\s
            rb.end_date,
            rb.max_quota,
            rb.batch_code,
            rb.bank_name,
            rb.bank_user,
            rb.price,
            rb.bank_account,
            rb.path_id,\s
            count(distinct sl.student_id) as countStudent
        from registration_batch rb
        left join student_logs sl on sl.batch_id = rb.id
        group by\s
            rb.id,
            rb.name,
            rb.start_date,
            rb.end_date,
            rb.max_quota,
            rb.batch_code,
            rb.bank_name,
            rb.bank_user,
            rb.price,
            rb.bank_account,
            rb.path_id
    """,
            nativeQuery = true)
    List<GetAllRegistrationBatch> findAllRegistrationBatch();
}
