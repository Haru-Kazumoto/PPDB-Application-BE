package dev.pack.modules.staging;

import dev.pack.modules.enums.FormPurchaseType;
import dev.pack.modules.enums.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StagingRepository extends JpaRepository<Staging, Integer> {

    @Query("""
                    select s from Staging s where s.name like CONCAT('%', CONCAT(:name, '%')) and s.grade = :grade
            """)
    Optional<Staging> findByName(String name, Grade grade);

    @Query("""
                    select s from Staging s where s.name like CONCAT('%', CONCAT(:name, '%')) and s.type = :type and s.grade = :grade
            """)
    Optional<Staging> findByNameAndStagingType(String name, FormPurchaseType type, Grade grade);

    @Query(value = """
                select
                    s.id as id,
                    s.name as name,
                    s.index as index,
                    (case when
                        (
                            select max(d.id) from student_logs d where d.student_id = :student_id and d.staging_id = s.id
                        )  is null
                    then 0
                    else 1
                    end) as is_done,
                    s.grade as grade
                from staging s
                where s.type = :type
                order by s.index asc
            """, nativeQuery = true)
    List<GetAllStaging> findStudentOffset(Integer student_id, Integer type);

}
