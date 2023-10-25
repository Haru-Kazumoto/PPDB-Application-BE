package dev.pack.modules.registration_general_information;

import dev.pack.exception.DataNotFoundException;
import dev.pack.modules.registration_paths.RegistrationPaths;
import dev.pack.modules.registration_paths.RegistrationPathsRepository;
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
        RegistrationPaths dataPath = this.registrationPathsRepository
                .findById(bodyCreate.getPath_id())
                .orElseThrow(() -> new DataNotFoundException("Id registration paths is not found."));

        bodyCreate.setRegistrationPaths(dataPath);

        return this.registrationGeneralInformationRepository.save(bodyCreate);
    }

    @Override
    public List<RegistrationGeneralInformation> index(Integer regisPathsId) {
        this.registrationPathsRepository
                .findById(regisPathsId)
                .orElseThrow(() -> new DataNotFoundException("Id regis paths not found."));

        return this.registrationGeneralInformationRepository.findAllByRegisPathId(regisPathsId);
    }

    @Override
    public RegistrationGeneralInformation update(Integer id, RegistrationGeneralInformation bodyUpdate) {

        RegistrationGeneralInformation dataNew = this.registrationGeneralInformationRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Id general information not found."));

        dataNew.setName(bodyUpdate.getName());
        dataNew.setDescription(bodyUpdate.getDescription());
        dataNew.setIndex(bodyUpdate.getIndex());

        return this.registrationGeneralInformationRepository.save(dataNew);
    }

    @Override
    public void delete(Integer id) {
        this.registrationGeneralInformationRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Id general information not found."));
        this.registrationGeneralInformationRepository.deleteById(id);

    }
}
