package dev.pack.modules.registration_paths;

import java.util.List;

public interface RegistrationPathsService {

    RegistrationPaths create(RegistrationPaths bodyCreate);
    List<RegistrationPaths> index();
    RegistrationPaths update(Integer id, RegistrationPaths bodyUpdate);
}
