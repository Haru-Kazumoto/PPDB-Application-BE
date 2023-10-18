package dev.pack.modules.admin.pengunguman;

import java.util.List;

public interface PengungumanService {

    Pengunguman store(Pengunguman bodyCreate);
    List<Pengunguman> index(Integer gelombangId);

}
