package dev.pack.modules.registration_paths;

import dev.pack.modules.enums.FormPurchaseType;

import java.util.List;

public interface RegistrationPathsService {

    RegistrationPaths createWithRegisBatch(RegistrationPaths bodyCreate);
    RegistrationPaths create(RegistrationPaths bodyCreate);
    List<RegistrationPaths> index();
    List<GetAllRegistrationPaths> indexByTypeRegistrationPaths(FormPurchaseType type);
    RegistrationPaths update(Integer id, RegistrationPaths bodyUpdate);
    void delete(Integer id);
    List<GetAllRegistrationPaths> getPathWithTotalStudents();
}
