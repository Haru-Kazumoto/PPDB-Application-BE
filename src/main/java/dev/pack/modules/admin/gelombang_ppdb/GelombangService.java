package dev.pack.modules.admin.gelombang_ppdb;

import java.util.List;

public interface GelombangService {

    Gelombang store(Gelombang bodyCreate);
    List<Gelombang> index(Integer jalurPendaftaranId);
    Gelombang update(Integer id, Gelombang bodyUpdate);

}
