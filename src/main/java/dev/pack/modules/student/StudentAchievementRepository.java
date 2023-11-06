package dev.pack.modules.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentAchievementRepository extends JpaRepository<StudentAchievement, Integer> {

    @Query("SELECT s FROM StudentAchievement s WHERE s.type = :type and s.student = :student")
    List<StudentAchievement> getStudentAchievementByStudentAndType(
            @Param("student") Student student,
            @Param("type") String type
    );
}
