package dev.pack.modules.registration_paths;

import dev.pack.exception.DataNotFoundException;
import dev.pack.modules.enums.FormPurchaseType;
import dev.pack.modules.registration_batch.RegistrationBatch;
import dev.pack.modules.registration_batch.RegistrationBatchRepository;
import dev.pack.utils.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static dev.pack.constraint.ErrorMessage.*;

@Service
@RequiredArgsConstructor
public class RegistrationPathsServiceImpl implements RegistrationPathsService {

    private final RegistrationPathsRepository registrationPathsRepository;
    private final RegistrationBatchRepository registrationBatchRepository;
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
    public RegistrationPaths create(RegistrationPaths bodyCreate) {
        this.validate.dateValidate(
                bodyCreate.getStart_date(),
                bodyCreate.getEnd_date()
        );

        return this.registrationPathsRepository.save(bodyCreate);
    }

    @Override
    public List<RegistrationPaths> index() {
        return this.registrationPathsRepository.findAll();
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

        this.registrationPathsRepository.delete(registrationPathsData);
    }

    @Override
    public List<RegistrationPaths> indexByTypeRegistrationPaths(FormPurchaseType type) {
        return this.registrationPathsRepository.findAllByType(type);
    }

    @Override
    public List<GetAllRegistrationPaths> getPathWithTotalStudents() {
        return this.registrationPathsRepository.getPathWithTotalStudents();
    }
}
