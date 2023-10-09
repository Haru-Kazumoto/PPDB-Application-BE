package dev.pack.modules.keterangan;

import java.util.List;

public interface KeteranganService {

    Keterangan store(Keterangan bodyCreate);
    List<Keterangan> index(Integer id);
    Keterangan update(Integer id, Keterangan bodyUpdate);
    void softDelete(Integer id);
    List<Keterangan> indexDeleted(Integer id);
    Keterangan restore(Integer id);

}
