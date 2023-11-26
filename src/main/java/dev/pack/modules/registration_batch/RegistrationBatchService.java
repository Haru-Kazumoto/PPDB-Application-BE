package dev.pack.modules.registration_batch;

import dev.pack.modules.enums.FormPurchaseType;
import dev.pack.modules.student.CountStudents;
import dev.pack.modules.student.Student;
import jakarta.servlet.Registration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface RegistrationBatchService {

//    RegistrationBatch store(RegistrationBatch bodyCreate, Integer regisId);
    RegistrationBatch store(RegistrationBatch bodyCreate);

    List<GetAllRegistrationBatch> index(Integer regisPathsId);

    List<RegistrationBatch> getAllBatchByType(FormPurchaseType type);

    RegistrationBatch getById(Integer id);

    RegistrationBatch update(Integer id, RegistrationBatch bodyUpdate);

    Map<String, String> delete(Integer id);

    List<RegistrationBatch> getAllGelombangWhereIsOpen(Boolean condition);

    List<RegistrationBatch> countStudents(Integer regisPathsId);

    List<RegistrationBatch> getAllGelombangByPathsId(Integer pathsId);

    List<RegistrationBatch> getRegisBatchByType(FormPurchaseType type);

    Page<GetAllStudentsByBatch> getStudentByBatchId(Integer batchId, Pageable pageable);

    CountStudents.DetailBatch countStudent(Integer batchId);

    List<CountStudents.InfoBatch> countAllStudents();
}
