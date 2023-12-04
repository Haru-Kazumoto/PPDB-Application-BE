package dev.pack.modules.registration_paths;

import dev.pack.modules.enums.FormPurchaseType;
import dev.pack.modules.enums.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationPathsRepository extends JpaRepository<RegistrationPaths, Integer> {

    @Query("SELECT t FROM RegistrationPaths t WHERE t.type = :type")
    List<RegistrationPaths> findAllByType(@Param("type") FormPurchaseType type);

    @Query("SELECT n FROM RegistrationPaths n WHERE n.name = :name")
    RegistrationPaths findByName(@Param("name") String name);

    @Query("SELECT g FROM RegistrationPaths g WHERE g.grade = :grade")
    List<RegistrationPaths> findAllByGrade(Grade grade);

//    List<RegistrationPaths> findAllBy

//    @Query(value = """
//        SELECT new dev.pack.modules.registration_paths.RegistrationPaths(
//            rp.id,
//            rp.name,
//            rp.type,
//            rp.start_date,
//            rp.end_date,
//            rp.price,
//            COUNT(rb.id)
//        )
//        FROM RegistrationPaths rp
//        LEFT JOIN RegistrationBatch rb ON rp.id = rb.registrationPaths.id
//        GROUP BY rp.id, rp.name, rp.type, rp.start_date, rp.end_date, rp.price
//    """)
//    List<RegistrationPaths> calculateTotalStudentInPaths();

    @Query(value = """
        select\s
            s.id,
            s.name,
            s.type,
            s.start_date,
            s.end_date,
            s.price,
            count(l.*) as total_pendaftar
        from registration_paths s
        left join (
            select path_id,student_id from student_logs
            group by path_id,student_id
        ) l on l.path_id = s.id
        group by  s.id,
            s.name,
            s.type,
            s.start_date,
            s.end_date,
            s.price
    """, nativeQuery = true)
    List<GetAllRegistrationPaths> getPathWithTotalStudents();

    @Query(value = """
        select 
            s.id,
            s.name,
            s.type,
            s.start_date,
            s.end_date,
            s.price,
            count(l.*) as total_pendaftar
        from registration_paths s
        left join (
            select path_id,student_id from student_logs
            group by path_id,student_id
        ) l on l.path_id = s.id
        where s.type = :type
        group by  s.id,
            s.name,
            s.type,
            s.start_date,
            s.end_date,
            s.price
    """, nativeQuery = true)
    List<GetAllRegistrationPaths> getPathWithTotalStudentsByType(String type);
}
