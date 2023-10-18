package dev.pack.modules.admin.action;

import dev.pack.modules.admin.enums.Role;

import java.util.List;

public interface ActionService {
    Action createAction(Action bodyCreate);
    List<Action> getAllAction();
    List<Action> getAllActionByMethod(String method);
    List<Action> getAllActionByRole(Role roleAccess);
    List<Action> getAllActionByBugStatus(Boolean isBug);
    Action updateAction(Integer id, Action bodyUpdate);
    void deleteAction(Integer id);
}
