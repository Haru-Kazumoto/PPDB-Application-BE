package dev.pack.modules.admin.biaya;

import java.util.List;

public interface BiayaService {

    Biaya store(Biaya bodyCreate);
    List<Biaya> index(Integer biayaTambahanId);
    Biaya update(Integer id, Biaya bodyUpdate);
}
