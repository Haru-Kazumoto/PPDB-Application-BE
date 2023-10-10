package dev.pack.modules.biaya_tambahan;

import java.util.List;

public interface BiayaTambahanService {

    BiayaTambahan store(BiayaTambahan bodyCreate);
    List<BiayaTambahan> index(Integer biayaTambahanId);
    List<BiayaTambahan> indexDeleted();
    BiayaTambahan update(Integer id, BiayaTambahan bodyUpdate);
    void softDelete(Integer id);

}
;