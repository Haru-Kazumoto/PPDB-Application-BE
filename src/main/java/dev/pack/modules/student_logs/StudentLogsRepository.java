package dev.pack.modules.student_logs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentLogsRepository extends JpaRepository<StudentLogs, Integer> {


}
