package dev.pack.modules.registration_batch;

import java.util.List;

public interface RegistrationBatchService {

    RegistrationBatch store(RegistrationBatch bodyCreate, Integer regisId);
    List<RegistrationBatch> index();
    RegistrationBatch update(Integer id, RegistrationBatch bodyUpdate);
    void delete(Integer id);
    List<Object[]> getTotalPendaftarPerBatch();
    Long getTotalPendaftar();
}
