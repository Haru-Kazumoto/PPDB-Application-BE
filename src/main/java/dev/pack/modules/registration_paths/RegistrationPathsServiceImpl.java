package dev.pack.modules.registration_paths;

import dev.pack.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistrationPathsServiceImpl implements RegistrationPathsService {

    private final RegistrationPathsRepository registrationPathsRepository;

    @Override
    public RegistrationPaths create(RegistrationPaths bodyCreate) {
        return this.registrationPathsRepository.save(bodyCreate);
    }

    @Override
    public List<RegistrationPaths> index() {
        return this.registrationPathsRepository.findAll();
    }

    @Override
    public RegistrationPaths update(Integer id, RegistrationPaths bodyUpdate) {
        RegistrationPaths data = this.registrationPathsRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Id registration path not found."));

        data.setName(bodyUpdate.getName());
        data.setType(bodyUpdate.getType());

        return this.registrationPathsRepository.save(data);
    }

}
