package dev.pack.modules.registration_batch;

import dev.pack.modules.enums.FormPurchaseType;
import dev.pack.modules.enums.Grade;
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

    // @Query(
    //     value = """
    //         select 
    //             rb.id, 
    //             rb."name", 
    //             rb.grade,
    //             rp."type",
    //             count(sl.*) as total 
    //         from 
    //             registration_batch rb 
    //             inner join student_logs sl on sl.batch_id = rb.id 
    //             inner join students s on s.id = sl.student_id 
    //             inner join registration_paths rp on rp.id = rb.registration_paths_id
    //         where 
    //             sl.id = (
    //                 select 
    //                 max(sl2.id) 
    //                 from 
    //                 student_logs sl2 
    //                 where 
    //                 sl2.student_id = s.id 
    //                 and sl2.batch_id = sl.batch_id
    //             ) 
    //         group by 
    //             rb.id, 
    //             rb."name",
    //             rb.grade,
    //             rp."type"
    //         """,
    //     nativeQuery = true
    // )
    @Query(
        value = """
            select 
                rb.id, 
                rb."name", 
                rb.grade,
                rp."type",
                (
                    select count(distinct sl.student_id)
                    from student_logs sl 
                    where sl.batch_id = rb.id
                ) as total 
            from 
                registration_batch rb 
                inner join registration_paths rp on rp.id = rb.registration_paths_id
            group by 
            rb.id,
            rb."name",
            rb.grade,
            rp."type" 
        """,
        nativeQuery = true
    )
    List<CountAllBatchStudents> countAllTotalPendaftarPerBatch();
    

    @Query("""
        select s from RegistrationBatch s
        inner join s.registrationPaths d
        where d.type = :type
    """)
    List<RegistrationBatch> getAllByType(FormPurchaseType type);

    @Query("SELECT b FROM RegistrationBatch b WHERE b.grade = :grade")
    List<RegistrationBatch> getAllBatchByGrade(Grade grade);

    @Transactional
    @Modifying
    @Query("UPDATE RegistrationBatch rb SET rb.countStudent = (SELECT COUNT(s) FROM Student s WHERE s.batch_id = :batchId) WHERE rb.id = :batchId")
    void countStudentFromBatch(@Param("batchId") Integer batchId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT COUNT(s) FROM Student s WHERE s.batch_id = :batchId")
    long countStudentsForRunningNumber(@Param("batchId") Integer batchId);

    @Query("SELECT p FROM RegistrationBatch p WHERE p.path_id = :pathId")
    List<RegistrationBatch> findAllByPathId(@Param("pathId") Integer pathId);

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

    @Query("SELECT rb FROM RegistrationBatch rb WHERE rb.registrationPaths.type = :type")
    List<RegistrationBatch> findRegistrationBatchByPathType(@Param("type") FormPurchaseType type);

    //CHANGE SELECTING DATA TO ASC
    @Query(
            value = """
                SELECT 
                s.id, 
                s.name, 
                s.phone, 
                s.registration_date, 
                sl.status 
              FROM 
                students s 
                LEFT JOIN student_logs sl ON sl.student_id = s.id 
              WHERE 
                sl.batch_id = :batchId 
                AND sl.id = (
                  SELECT 
                    MAX(id) 
                  FROM 
                    student_logs 
                  WHERE 
                    student_id = s.id 
                    AND batch_id = sl.batch_id
                ) 
              GROUP BY 
                s.id, 
                s.name, 
                s.phone, 
                s.registration_date, 
                sl.status 
              ORDER BY 
                s.registration_date ASC
            """,
            countQuery = """
                SELECT 
                COUNT(*) 
              FROM 
                students s 
                LEFT JOIN student_logs sl ON sl.student_id = s.id 
              WHERE 
                sl.batch_id = :batchId 
                AND sl.id = (
                  SELECT 
                    MAX(id) 
                  FROM 
                    student_logs 
                  WHERE 
                    student_id = s.id 
                    AND batch_id = sl.batch_id
                ) 
              GROUP BY 
                s.id, 
                s.name, 
                s.phone, 
                s.registration_date, 
                sl.status
              
            """,
            nativeQuery = true)
    Page<GetAllStudentsByBatch> findAllStudentByBatchId(@Param("batchId") Integer batchId, Pageable pageable);

    //FOR EXPORTING STUDENT TO EXCEL
    @Query(
        value = """
                SELECT\s
                  s.id,\s
                  s.formulir_id,\s
                  s.last_inserted_number,\s
                  s.name,\s
                  s.batch_id,
                  s.religion,\s
                  s.phone,\s
                  s.address,\s
                  s.gender,\s
                  s.school_origin,\s
                  s.major,\s
                  s.registration_date\s
                FROM\s
                  students s\s
                  LEFT JOIN student_logs sl ON sl.student_id = s.id\s
                WHERE\s
                  sl.batch_id = :batchId
                  AND sl.id = (
                    SELECT\s
                      MAX(id)\s
                    FROM\s
                      student_logs\s
                    WHERE\s
                      student_id = s.id\s
                    AND batch_id = sl.batch_id
                    order by s.registration_date asc
                  )\s
                GROUP BY\s
                  s.id,\s
                  s.formulir_id,\s
                  s.last_inserted_number,\s
                  s.name,\s
                  s.gender,
                  s.batch_id,
                  s.phone,\s
                  s.address,\s
                  s.gender,\s
                  s.school_origin,\s
                  s.major,\s
                  s.registration_date\s
                ORDER BY\s
                  s.registration_date asc
                """,
        nativeQuery = true
    )
    List<GetAllStudentForExport> findAllStudentByBatchId(@Param("batchId") Integer batchId);


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
        where rb.id = :batchId
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
    List<GetAllRegistrationBatch> findAllRegistrationBatch(Integer batchId);
}
