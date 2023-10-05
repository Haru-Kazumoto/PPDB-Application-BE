package dev.pack.modules.alur_ppdb;

import java.util.List;

public interface AlurPpdbService {

    AlurPpdb createFlow(AlurPpdb bodyCreate);
    List<AlurPpdb> getAllFlowByUserId(Integer userId);
    List<AlurPpdb> getAll();
    AlurPpdb updateFlow(Integer id, AlurPpdb bodyUpdate);

}
