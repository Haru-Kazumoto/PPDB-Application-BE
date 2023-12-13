package dev.pack.modules.student_logs;

import dev.pack.modules.enums.FormPurchaseType;
import dev.pack.modules.staging.Staging;
import dev.pack.modules.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentLogsRepository extends JpaRepository<StudentLogs, Integer> {


    @Query(value = """
        select d from StudentLogs d
        where d.id = (
            select MAX(s.id) from StudentLogs s where s.student = :student and s.staging = :staging and s.type = :type
        )
""",nativeQuery = false)
    Optional<StudentLogs> findByStudentAndStagingByType(Student student, Staging staging, FormPurchaseType type);

    @Query("""
        select d from StudentLogs d
        where d.id = (
            select MAX(s.id) from StudentLogs s where s.student = :student
        )
""")
    Optional<StudentLogs> findCurrentStaging(Student student);

    @Modifying
    @Query("""
        DELETE FROM StudentLogs s\s
        WHERE s.student.id = :studentId\s
            AND EXISTS (
                SELECT 1 FROM Student st WHERE st.id = :studentId
            )
        AND s.type = :type
    """)
    void deleteStudentLogsByStudentId(@Param("studentId") Integer studentId, @Param("type") FormPurchaseType type);

    @Query("SELECT sl FROM StudentLogs sl WHERE sl.student.id = :studentId ORDER BY sl.createdAt DESC")
    List<StudentLogs> findAllLatestLogByStudentId(@Param("studentId") Integer studentId);

    @Query("SELECT sl FROM StudentLogs sl WHERE sl.student.id = :studentId ORDER BY sl.createdAt DESC")
    Optional<StudentLogs> findLatestLogByStudentId(@Param("studentId") Integer studentId);

}
