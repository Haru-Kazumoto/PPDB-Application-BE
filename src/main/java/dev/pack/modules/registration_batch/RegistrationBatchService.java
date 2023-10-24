package dev.pack.modules.registration_batch;

import java.util.List;
import java.util.Map;

public interface RegistrationBatchService {

    RegistrationBatch store(RegistrationBatch bodyCreate, Integer regisId);
    List<RegistrationBatch> index(Integer regisPathsId);

    RegistrationBatch update(Integer id, RegistrationBatch bodyUpdate);
    Map<String, String> delete(Integer id);
//    List<Object[]> getTotalPendaftarPerBatch(Integer batchId);
//    Long getTotalPendaftar(Integer batchId);
    List<RegistrationBatch> countStudents(Integer regisPathsId);
    List<RegistrationBatch> getAllGelombangByPathsId(Integer pathsId);
}
