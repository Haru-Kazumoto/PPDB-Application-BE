package dev.pack.modules.ppdbFlow;

import java.util.List;

public interface PpdbFlowService {

    PpdbFlow createFlow(PpdbFlow bodyCreate);
    List<PpdbFlow> getAllFlowByUserId(Integer userId);
    List<PpdbFlow> getAll();
    PpdbFlow updateFlow(Integer id, PpdbFlow bodyUpdate);

}
