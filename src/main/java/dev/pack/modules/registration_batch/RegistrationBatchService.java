package dev.pack.modules.registration_batch;

import dev.pack.modules.enums.FormPurchaseType;

import java.util.List;
import java.util.Map;

public interface RegistrationBatchService {

    RegistrationBatch store(RegistrationBatch bodyCreate, Integer regisId);

    List<RegistrationBatch> index(Integer regisPathsId);

    List<RegistrationBatch> getAllBatchByType(FormPurchaseType type);

    RegistrationBatch getById(Integer id);

    RegistrationBatch update(Integer id, RegistrationBatch bodyUpdate);

    Map<String, String> delete(Integer id);

    List<RegistrationBatch> getAllGelombangWhereIsOpen(Boolean condition);

    List<RegistrationBatch> countStudents(Integer regisPathsId);

    List<RegistrationBatch> getAllGelombangByPathsId(Integer pathsId);

}
