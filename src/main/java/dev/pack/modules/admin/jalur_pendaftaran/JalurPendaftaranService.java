package dev.pack.modules.admin.jalur_pendaftaran;

import java.util.List;

public interface JalurPendaftaranService {

    JalurPendaftaran store(JalurPendaftaran bodyCreate);
    List<JalurPendaftaran> index(Integer id);
    JalurPendaftaran update(Integer id, JalurPendaftaran bodyUpdate);
    void softDelete(Integer id);
    List<JalurPendaftaran> indexDeleted(Integer id);
}
