package dev.pack.modules.alur_ppdb;

import dev.pack.payloads.DeleteResponse;

import java.util.List;

public interface AlurPpdbService {

    AlurPpdb createFlow(AlurPpdb bodyCreate);
    List<AlurPpdb> getAllFlowByUserId(Integer userId);
    List<AlurPpdb> getAll();
    AlurPpdb updateFlow(Integer id, AlurPpdb bodyUpdate);
    DeleteResponse hardDeleteById(Integer id);
    DeleteResponse softDeleteById(Integer id);
    DeleteResponse deleteDataHasDeleted(); //Deleting data where 'deteledAt' is not null
}
