package dev.pack.modules.exam_information;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExamInformationRepository extends JpaRepository<ExamInformation, Integer> {

    List<ExamInformation> findAllByBatchId(Integer batchId);

}
