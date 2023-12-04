package dev.pack.modules.exam_information;

import dev.pack.exception.DataNotFoundException;
import dev.pack.modules.auth.AuthenticationService;
import dev.pack.modules.registration_batch.RegistrationBatch;
import dev.pack.modules.registration_batch.RegistrationBatchRepository;
import dev.pack.modules.user.User;
import dev.pack.utils.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamInformationServiceImpl implements ExamInformationService {

    private final ExamInformationRepository examInformationRepository;
    private final RegistrationBatchRepository registrationBatchRepository;
    private final AuthenticationService authenticationService;
    private final Validator validator;

    @Override
    public ExamInformation create(ExamInformation bodyCreate) {
        RegistrationBatch registrationBatch = this.registrationBatchRepository.findById(bodyCreate.getBatchId())
                .orElseThrow(() -> new DataNotFoundException("Registration path id not found."));

        this.validator.dateValidate(bodyCreate.getStartDate(), bodyCreate.getEndDate());

        return this.examInformationRepository.save(
                ExamInformation.builder()
                        .title(bodyCreate.getTitle())
                        .link(bodyCreate.getLink())
                        .batchId(bodyCreate.getBatchId())
                        .startDate(bodyCreate.getStartDate())
                        .endDate(bodyCreate.getEndDate())
                        .registrationBatch(registrationBatch)
                        .build()
        );
    }

    @Override
    public List<ExamInformation> index(Integer batchId) {
        return this.examInformationRepository.findAllByBatchId(batchId);
    }

    @Override
    public List<ExamInformation> indexAll() {
        return this.examInformationRepository.findAll();
    }

    @Override
    public ExamInformation update(ExamInformation bodyUpdate, Integer id) {
        ExamInformation data = this.examInformationRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("ID not found."));

        this.validator.dateValidate(bodyUpdate.getStartDate(), bodyUpdate.getEndDate());

        data.setTitle(bodyUpdate.getTitle());
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

    @Override
    public List<ExamInformation> findAllExamByBatchId(Integer batchId) {
        User student = this.authenticationService.decodeJwt();



        return null;
    }
}
