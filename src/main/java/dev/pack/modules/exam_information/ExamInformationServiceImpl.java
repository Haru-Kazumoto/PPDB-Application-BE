package dev.pack.modules.exam_information;

import dev.pack.exception.DataNotFoundException;
import dev.pack.modules.lookup.Lookup;
import dev.pack.modules.lookup.LookupRepository;
import dev.pack.modules.registration_paths.RegistrationPaths;
import dev.pack.modules.registration_paths.RegistrationPathsRepository;
import dev.pack.utils.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamInformationServiceImpl implements ExamInformationService {

    private final ExamInformationRepository examInformationRepository;
    private final RegistrationPathsRepository registrationPathsRepository;
    private final LookupRepository lookupRepository;
    private final Validator validator;

    @Override
    public ExamInformation create(ExamInformation bodyCreate) {
        RegistrationPaths registrationPaths = this.registrationPathsRepository.findById(bodyCreate.getPathId())
                .orElseThrow(() -> new DataNotFoundException("Registration path id not found."));

        this.majorValidate(bodyCreate.getFor_major());

        this.validator.dateValidate(bodyCreate.getStartDate(), bodyCreate.getEndDate());

        Date present = new Date();
        bodyCreate.setIsOpen(false);

        if(bodyCreate.getEndDate().equals(present)){
            bodyCreate.setIsOpen(true);
        }

        return this.examInformationRepository.save(
                ExamInformation.builder()
                        .title(bodyCreate.getTitle())
                        .for_major(bodyCreate.getFor_major())
                        .link(bodyCreate.getLink())
                        .pathId(bodyCreate.getPathId())
                        .startDate(bodyCreate.getStartDate())
                        .endDate(bodyCreate.getEndDate())
                        .isOpen(bodyCreate.getIsOpen())
                        .registrationPaths(registrationPaths)
                        .build()
        );
    }

    @Override
    public List<ExamInformation> index(Integer pathId) {
//        this.examInformationRepository.findByPathId(pathId).orElseThrow(() -> new DataNotFoundException("Path id not found."));
        return this.examInformationRepository.findAllByPathId(pathId);
    }

    @Override
    public ExamInformation update(ExamInformation bodyUpdate, Integer id) {
        ExamInformation data = this.examInformationRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("ID not found."));

        this.majorValidate(bodyUpdate.getFor_major());

        this.validator.dateValidate(bodyUpdate.getStartDate(), bodyUpdate.getEndDate());

        Date present = new Date();
        bodyUpdate.setIsOpen(false);

        if(bodyUpdate.getEndDate().equals(present)){
            bodyUpdate.setIsOpen(true);
        }

        data.setTitle(bodyUpdate.getTitle());
        data.setFor_major(bodyUpdate.getFor_major());
        data.setLink(bodyUpdate.getLink());
        data.setStartDate(bodyUpdate.getStartDate());
        data.setEndDate(bodyUpdate.getEndDate());

        return this.examInformationRepository.save(data);
    }

    @Override
    public void delete(Integer id) {
        ExamInformation data = this.examInformationRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Id not found."));

        this.examInformationRepository.delete(data);
    }

    private Lookup majorValidate(String obj){
        return this.lookupRepository.findMajor(obj).orElseThrow(() -> new DataNotFoundException("Major not found"));
    }

}
