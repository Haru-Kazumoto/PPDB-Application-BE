package dev.pack.modules.registration_paths;

import dev.pack.exception.DataNotFoundException;
import dev.pack.modules.registration_batch.RegistrationBatch;
import dev.pack.modules.registration_batch.RegistrationBatchRepository;
import dev.pack.utils.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
                .orElseThrow(() -> new DataNotFoundException("Id registration path not found."));

        data.setName(bodyUpdate.getName());
        data.setType(bodyUpdate.getType());

        return this.registrationPathsRepository.save(data);
    }

    @Override
    public void delete(Integer id) {
        this.registrationPathsRepository.deleteById(id);
    }
}
