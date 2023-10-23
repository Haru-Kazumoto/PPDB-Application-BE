package dev.pack.modules.registration_paths;

import java.util.List;

public interface RegistrationPathsService {

    RegistrationPaths createWithRegisBatch(RegistrationPaths bodyCreate);
    RegistrationPaths create(RegistrationPaths bodyCreate);
    List<RegistrationPaths> index();
    RegistrationPaths update(Integer id, RegistrationPaths bodyUpdate);
    void delete(Integer id);
}
