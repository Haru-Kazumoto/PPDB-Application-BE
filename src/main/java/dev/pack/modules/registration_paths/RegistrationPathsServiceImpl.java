package dev.pack.modules.registration_paths;

import dev.pack.exception.DataNotFoundException;
import dev.pack.modules.additional_prices.AdditionalPrices;
import dev.pack.modules.additional_prices.ResponseAdditionalPriceDto;
import dev.pack.modules.auth.AuthenticationService;
import dev.pack.modules.enums.FormPurchaseType;
import dev.pack.modules.enums.Grade;
import dev.pack.modules.prices.PriceDetails;
import dev.pack.modules.prices.ResponsePriceDetailsDto;
import dev.pack.modules.registration_batch.RegistrationBatch;
import dev.pack.modules.registration_batch.RegistrationBatchRepository;
import dev.pack.modules.registration_batch.ResponseRegistrationBatchDto;
import dev.pack.modules.registration_general_information.RegistrationGeneralInformation;
import dev.pack.modules.registration_general_information.ResponseGeneralInformationDto;
import dev.pack.modules.student.Student;
import dev.pack.modules.student.StudentRepository;
import dev.pack.modules.student_logs.StudentLogsRepository;
import dev.pack.modules.student_payments.StudentPaymentRepository;
import dev.pack.modules.user.User;
import dev.pack.utils.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static dev.pack.constraint.ErrorMessage.*;

@Service
@RequiredArgsConstructor
public class RegistrationPathsServiceImpl implements RegistrationPathsService {

    private final RegistrationPathsRepository registrationPathsRepository;
    private final RegistrationBatchRepository registrationBatchRepository;
    private final StudentLogsRepository studentLogsRepository;
    private final StudentRepository studentRepository;
    private final StudentPaymentRepository studentPaymentRepository;
    private final AuthenticationService authenticationService;
    private final Validator validate;

    @Override
    public RegistrationPaths createWithRegisBatch(RegistrationPaths bodyCreate) {
        List<RegistrationBatch> registrationBatchList = new ArrayList<>();

        //Validating date time
        this.validate.dateValidate(
                bodyCreate.getStart_date(),
                bodyCreate.getEnd_date()
        );

        // Simpan RegistrationPaths terlebih dahulu
        RegistrationPaths savedRegistrationPaths = registrationPathsRepository.save(bodyCreate);

        for (RegistrationBatch data : bodyCreate.getRegistrationBatches()) {
            RegistrationBatch registrationBatch = RegistrationBatch.builder()
                    .index(data.getIndex())
                    .max_quota(data.getMax_quota())
                    .start_date(data.getStart_date())
                    .end_date(data.getEnd_date())
                    .bank_name(data.getBank_name())
                    .bank_account(data.getBank_account())
                    .bank_user(data.getBank_user())
                    .price(data.getPrice())
                    .build();

            // Hubungkan RegistrationBatch dengan RegistrationPaths yang sudah disimpan
            registrationBatch.setRegistrationPaths(savedRegistrationPaths);

            registrationBatchList.add(registrationBatch);
        }

        var savedRegistrationBatches = registrationBatchRepository.saveAll(registrationBatchList);

        savedRegistrationPaths.setRegistrationBatches(savedRegistrationBatches);

        return savedRegistrationPaths;
    }

    @Override
    public List<ResponseRegistrationPathsDto> indexAllWithRecursion() {
        List<RegistrationPaths> registrationPaths = registrationPathsRepository.findAll();

        return registrationPaths.stream()
                .map(this::mapToCustomResponse)
                .collect(Collectors.toList());
    }

    private ResponseRegistrationPathsDto mapToCustomResponse(RegistrationPaths registrationPaths) {
        return ResponseRegistrationPathsDto.builder()
                .id(registrationPaths.getId())
                .name(registrationPaths.getName())
                .grade(registrationPaths.getGrade())
                .registrationBatches(registrationPaths.getRegistrationBatches().stream()
                        .map(this::mapToCustomResponseRegistrationBatch)
                        .collect(Collectors.toList()))
                .generalInformations(registrationPaths.getRegistrationGeneralInformations().stream()
                        .map(this::mapToCustomResponseGeneralInformation)
                        .collect(Collectors.toList()))
                .additionalPrices(registrationPaths.getAdditionalPrices().stream()
                        .map(this::mapToCustomResponseAdditionalPrice)
                        .collect(Collectors.toList()))
                .build();
    }

    private ResponseRegistrationBatchDto mapToCustomResponseRegistrationBatch(RegistrationBatch registrationBatch) {
        return ResponseRegistrationBatchDto.builder()
                .id(registrationBatch.getId())
                .name(registrationBatch.getName())
                .index(registrationBatch.getIndex())
                .max_quota(registrationBatch.getMax_quota())
                .batchCode(registrationBatch.getBatchCode())
                .start_date(registrationBatch.getStart_date())
                .end_date(registrationBatch.getEnd_date())
                .bank_name(registrationBatch.getBank_name())
                .bank_user(registrationBatch.getBank_user())
                .price(registrationBatch.getPrice())
                .bank_account(registrationBatch.getBank_account())
                .build();
    }

    private ResponseGeneralInformationDto mapToCustomResponseGeneralInformation(RegistrationGeneralInformation generalInformation) {
        return ResponseGeneralInformationDto.builder()
                .id(generalInformation.getId())
                .name(generalInformation.getName())
                .description(generalInformation.getDescription())
                .build();
    }

    private ResponseAdditionalPriceDto mapToCustomResponseAdditionalPrice(AdditionalPrices additionalPrice) {
        return ResponseAdditionalPriceDto.builder()
                .id(additionalPrice.getId())
                .namePrice(additionalPrice.getNamePrice())
                .priceDetails(additionalPrice.getPriceDetails().stream()
                        .map(this::mapToCustomResponsePriceDetails)
                        .collect(Collectors.toList()))
                .build();
    }

    private ResponsePriceDetailsDto mapToCustomResponsePriceDetails(PriceDetails priceDetails) {
        return ResponsePriceDetailsDto.builder()
                .id(priceDetails.getId())
                .subTitle(priceDetails.getSubTitle())
                .price(priceDetails.getPrice())
                .build();
    }

    @Override
    public RegistrationPaths create(RegistrationPaths bodyCreate) {
        this.validate.dateValidate(
                bodyCreate.getStart_date(),
                bodyCreate.getEnd_date()
        );

        return this.registrationPathsRepository.save(bodyCreate);
    }

    @Override
    public RegistrationPaths getPathByStudentSession() {
        User user = this.authenticationService.decodeJwt();
        return this.registrationPathsRepository.findById(user.getStudent().getPath_id()).orElseThrow();
    }

    @Override
    public List<RegistrationPaths> index(Grade grade) {
        return this.registrationPathsRepository.findAllByGrade(grade);
    }

    @Override
    public RegistrationPaths update(Integer id, RegistrationPaths bodyUpdate) {
        RegistrationPaths data = this.registrationPathsRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(REGISTRATION_PATHS_ID_NOT_FOUND));

        data.setName(bodyUpdate.getName());
        data.setType(bodyUpdate.getType());
        data.setStart_date(bodyUpdate.getStart_date());
        data.setEnd_date(bodyUpdate.getEnd_date());
        data.setPrice(bodyUpdate.getPrice());

        return this.registrationPathsRepository.save(data);
    }

    @Override
    public void delete(Integer id) {
        RegistrationPaths registrationPathsData = this.registrationPathsRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(REGISTRATION_PATHS_ID_NOT_FOUND));

        var students = this.studentRepository.findAllStudentByPathId(id);

        for(Student student : students){
            this.studentPaymentRepository.deleteStudentPaymentsByStudentId(student.getId(), registrationPathsData.getType());
            this.studentLogsRepository.deleteStudentLogsByStudentId(student.getId(), registrationPathsData.getType());
            this.studentRepository.deleteStudentFromBatchByStudentId(student.getId());
        }

        this.registrationPathsRepository.delete(registrationPathsData);
    }

    @Override
    public List<GetAllRegistrationPaths> indexByTypeRegistrationPaths(FormPurchaseType type) {
        return this.registrationPathsRepository.getPathWithTotalStudentsByType(type.name());
    }

    @Override
    public List<GetAllRegistrationPaths> getPathWithTotalStudents() {
        return this.registrationPathsRepository.getPathWithTotalStudents();
    }
}
