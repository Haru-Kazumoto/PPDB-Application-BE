package dev.pack.modules.admin.kegiatan;

import java.util.List;

public interface KegiatanService {

    Kegiatan store(Kegiatan bodyCreate);
    List<Kegiatan> index(Integer id);
    Kegiatan update(Kegiatan bodyUpdate);

}
