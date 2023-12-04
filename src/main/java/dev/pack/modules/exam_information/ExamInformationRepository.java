package dev.pack.modules.exam_information;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamInformationRepository extends JpaRepository<ExamInformation, Integer> {

    List<ExamInformation> findAllByBatchId(Integer batchId);

}
