package dev.pack.modules.registration_general_information;

import dev.pack.exception.DataNotFoundException;
import dev.pack.modules.registration_paths.RegistrationPaths;
import dev.pack.modules.registration_paths.RegistrationPathsRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RegistrationGeneralInformationServiceImpl implements RegistrationGeneralInformationService {

    private final RegistrationGeneralInformationRepository registrationGeneralInformationRepository;
    private final RegistrationPathsRepository registrationPathsRepository;

    @Override
    public RegistrationGeneralInformation create(RegistrationGeneralInformation bodyCreate) {
        RegistrationPaths dataRegistrationPaths = this.registrationPathsRepository.findById(bodyCreate.getPath_id())
                .orElseThrow(() -> new DataNotFoundException("Id registration path not found."));

        bodyCreate.setRegistrationPaths(dataRegistrationPaths);
        bodyCreate.setPath_id(dataRegistrationPaths.getId());

        return this.registrationGeneralInformationRepository.save(bodyCreate);
    }

    @Override
    public List<RegistrationGeneralInformation> index() {
        return null;
    }

    @Override
    public RegistrationGeneralInformation update(Integer id, RegistrationGeneralInformation bodyUpdate) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
