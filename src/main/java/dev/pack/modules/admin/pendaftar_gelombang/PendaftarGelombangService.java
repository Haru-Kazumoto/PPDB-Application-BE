package dev.pack.modules.admin.pendaftar_gelombang;

import java.util.List;

public interface PendaftarGelombangService {

    PendaftarGelombang store(PendaftarGelombang bodyCreate);
    List<PendaftarGelombang> index(Integer modelId);

}
