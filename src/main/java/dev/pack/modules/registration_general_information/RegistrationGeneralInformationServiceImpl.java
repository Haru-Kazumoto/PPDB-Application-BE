package dev.pack.modules.registration_general_information;

import dev.pack.exception.DataNotFoundException;
import dev.pack.modules.registration_paths.RegistrationPaths;
import dev.pack.modules.registration_paths.RegistrationPathsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static dev.pack.constraint.ErrorMessage.*;

@RequiredArgsConstructor
@Service
public class RegistrationGeneralInformationServiceImpl implements RegistrationGeneralInformationService {

    private final RegistrationGeneralInformationRepository registrationGeneralInformationRepository;
    private final RegistrationPathsRepository registrationPathsRepository;

    @Override
    public RegistrationGeneralInformation create(RegistrationGeneralInformation bodyCreate) {
        RegistrationPaths dataPath = this.registrationPathsRepository
                .findById(bodyCreate.getPath_id())
                .orElseThrow(() -> new DataNotFoundException(REGISTRATION_PATHS_ID_NOT_FOUND));

        bodyCreate.setRegistrationPaths(dataPath);

        return this.registrationGeneralInformationRepository.save(bodyCreate);
    }

    @Override
    public List<RegistrationGeneralInformation> index(Integer regisPathsId) {
        this.registrationPathsRepository
                .findById(regisPathsId)
                .orElseThrow(() -> new DataNotFoundException(REGISTRATION_PATHS_ID_NOT_FOUND));

        return this.registrationGeneralInformationRepository.findAllByRegisPathId(regisPathsId);
    }

    @Override
    public RegistrationGeneralInformation update(Integer id, RegistrationGeneralInformation bodyUpdate) {

        RegistrationGeneralInformation dataNew = this.registrationGeneralInformationRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(GENERAL_INFORMATION_ID_NOT_FOUND));

        dataNew.setName(bodyUpdate.getName());
        dataNew.setDescription(bodyUpdate.getDescription());
        dataNew.setIndex(bodyUpdate.getIndex());

        return this.registrationGeneralInformationRepository.save(dataNew);
    }

    @Override
    public void delete(Integer id) {
        RegistrationGeneralInformation data = this.registrationGeneralInformationRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(GENERAL_INFORMATION_ID_NOT_FOUND));

        this.registrationGeneralInformationRepository.delete(data);
    }

    @Override
    public RegistrationGeneralInformation getGeneralInformationByPathId(Integer pathId) {

        return null;
    }
}
