package dev.pack.modules.registration_general_information;

import java.util.List;

public interface RegistrationGeneralInformationService {

    RegistrationGeneralInformation create(RegistrationGeneralInformation bodyCreate);
    List<RegistrationGeneralInformation> index(Integer regisPathsId);
    RegistrationGeneralInformation update(Integer id, RegistrationGeneralInformation bodyUpdate);
    void delete(Integer id);

}
