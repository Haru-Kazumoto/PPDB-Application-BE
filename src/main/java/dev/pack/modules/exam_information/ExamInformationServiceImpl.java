package dev.pack.modules.exam_information;

import dev.pack.exception.DataNotFoundException;
import dev.pack.modules.lookup.Lookup;
import dev.pack.modules.lookup.LookupRepository;
import dev.pack.modules.registration_batch.RegistrationBatch;
import dev.pack.modules.registration_batch.RegistrationBatchRepository;
import dev.pack.utils.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamInformationServiceImpl implements ExamInformationService {

    private final ExamInformationRepository examInformationRepository;
    private final RegistrationBatchRepository registrationBatchRepository;
    private final LookupRepository lookupRepository;
    private final Validator validator;

    @Override
    public ExamInformation create(ExamInformation bodyCreate) {
        RegistrationBatch registrationBatch = this.registrationBatchRepository.findById(bodyCreate.getBatchId())
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
                        .batchId(bodyCreate.getBatchId())
                        .startDate(bodyCreate.getStartDate())
                        .endDate(bodyCreate.getEndDate())
                        .isOpen(bodyCreate.getIsOpen())
                        .registrationBatch(registrationBatch)
                        .build()
        );
    }

    @Override
    public List<ExamInformation> index(Integer batchId) {
//        this.examInformationRepository.findByPathId(batchId).orElseThrow(() -> new DataNotFoundException("Path id not found."));
        return this.examInformationRepository.findAllByBatchId(batchId);
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
