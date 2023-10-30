package dev.pack.modules.student_logs;

import dev.pack.modules.enums.FormPurchaseType;
import dev.pack.modules.staging.Staging;
import dev.pack.modules.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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

}
