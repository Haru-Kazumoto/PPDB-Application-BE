package dev.pack.modules.exam_information;

import java.util.List;

public interface ExamInformationService {

    ExamInformation create(ExamInformation bodyCreate);
    List<ExamInformation> index();
    ExamInformation update(ExamInformation bodyUpdate, Integer id);
    void delete(Integer id);
    List<ExamInformation> findAllExamByBatchId(Integer batchId);

}
