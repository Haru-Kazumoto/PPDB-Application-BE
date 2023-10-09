package dev.pack.modules.jalur_pendaftaran;

import dev.pack.payloads.DeleteResponse;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Map;

public interface JalurPendaftaranService {

    JalurPendaftaran store(JalurPendaftaran bodyCreate);
    List<JalurPendaftaran> index(Integer id);
    JalurPendaftaran update(Integer id, JalurPendaftaran bodyUpdate);
    void softDelete(Integer id);
    List<JalurPendaftaran> indexDeleted();
}
