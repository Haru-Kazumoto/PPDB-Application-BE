package dev.pack.modules.action;

import dev.pack.exception.DataNotFoundException;
import dev.pack.exception.DuplicateDataException;
import dev.pack.modules.authorization.Role;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ActionServiceImpl implements ActionService{

    private final ActionRepository actionRepository;

    @Override
    public Action createAction(Action bodyCreate) {
        this.actionRepository
                .findByPath(bodyCreate.getPath())
                .ifPresent(path -> {
                    throw new DuplicateDataException("Path is already exists");
                });

        return this.actionRepository.save(bodyCreate);
    }

    @Override
    public List<Action> getAllAction() {
        return this.actionRepository.findAll();
    }

    @Override
    public List<Action> getAllActionByMethod(String method) {
        return this.actionRepository.findAllByMethod(method);
    }

    @Override
    public List<Action> getAllActionByRole(Role roleAccess) {
        return this.actionRepository.findAllByRoleAccess(roleAccess);
    }

    @Override
    public List<Action> getAllActionByBugStatus(Boolean isBug) {
        return this.actionRepository.findAllByIsBug(isBug);
    }

    @Override
    public Action updateAction(Integer id, Action bodyUpdate) {
        Action data = this.actionRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException("Id not found."));

        this.actionRepository.findByPath(bodyUpdate.getPath()).ifPresent(action -> {
            throw new DuplicateDataException("Path is already exists");
        });

        data.setPath(bodyUpdate.getPath());
        data.setMethod(bodyUpdate.getMethod());
        data.setDescriptionAction(bodyUpdate.getDescriptionAction());
        data.setRoleAccess(bodyUpdate.getRoleAccess());
        data.setIsBug(bodyUpdate.getIsBug());
        data.setBugDescription(bodyUpdate.getBugDescription());

        return data;
    }

    @Override
    public void deleteAction(Integer id) {
        this.actionRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Id not found."));

        this.actionRepository.deleteById(id);
    }
}
