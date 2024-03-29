package dev.pack.modules.registration_batch;

import dev.pack.modules.enums.FormPurchaseType;
import dev.pack.modules.enums.Grade;
import dev.pack.modules.registration_batch.interfaces.CountAllBatchStudents;
import dev.pack.modules.registration_batch.interfaces.CountPerBatch;
import dev.pack.modules.registration_batch.interfaces.GetAllRegistrationBatch;
import dev.pack.modules.registration_batch.interfaces.GetAllStudentsByBatch;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface RegistrationBatchService {

    RegistrationBatch store(RegistrationBatch bodyCreate);

    List<GetAllRegistrationBatch> index(Integer regisPathsId);

    List<RegistrationBatch> getAllBatchByType(FormPurchaseType type);

    RegistrationBatch getById(Integer id);

    RegistrationBatch update(Integer id, RegistrationBatch bodyUpdate);

    Map<String, String> delete(Integer id);

    List<RegistrationBatch> getAllGelombangByPathsId(Integer pathsId);

    Page<GetAllStudentsByBatch> getStudentByBatchId(Integer batchId, Pageable pageable);

    CountPerBatch countStudent(Integer batchId);

    List<CountAllBatchStudents> countAllStudentsPerBatch();

    List<RegistrationBatch> getAllBatchByGrade(Grade grade);

    Map<String, String> deleteStudentFromBatch(Integer studentId);

}
