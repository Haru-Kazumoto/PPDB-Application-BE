package dev.pack.modules.admin.alur_ppdb;

import java.util.List;

public interface AlurPpdbService {

    AlurPpdb createFlow(AlurPpdb bodyCreate);
    List<AlurPpdb> getAllFlowByUserId(Integer userId);
    List<AlurPpdb> getAll();
    AlurPpdb updateFlow(Integer id, AlurPpdb bodyUpdate);
    void hardDeleteById(Integer id);
    void softDeleteById(Integer id);
    void deleteDataHasDeleted(); //Deleting data where 'deteledAt' is not null
}