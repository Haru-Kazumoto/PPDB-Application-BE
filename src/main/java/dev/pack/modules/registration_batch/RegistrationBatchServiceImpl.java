package dev.pack.modules.registration_batch;

import dev.pack.exception.DataNotFoundException;
import dev.pack.modules.registration_paths.RegistrationPaths;
import dev.pack.modules.registration_paths.RegistrationPathsRepository;
import dev.pack.utils.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistrationBatchServiceImpl implements RegistrationBatchService{

    private final RegistrationBatchRepository registrationBatchRepository;
    private final RegistrationPathsRepository registrationPathsRepository;
    private final Validator validate;

    @Override
    public RegistrationBatch store(RegistrationBatch bodyCreate, Integer regisId) {
        this.validate.dateValidate(
                bodyCreate.getStart_date(),
                bodyCreate.getEnd_date()
        );

        RegistrationPaths registrationPaths = registrationPathsRepository.findById(regisId)
                .orElseThrow(() -> new DataNotFoundException("RegistrationPaths not found"));

        bodyCreate.setRegistrationPaths(registrationPaths);

        return this.registrationBatchRepository.save(bodyCreate);
    }

    @Override
    public List<RegistrationBatch> index() {
        return this.registrationBatchRepository.findAll();
    }

    @Override
    public RegistrationBatch update(Integer id, RegistrationBatch bodyUpdate) {
        RegistrationBatch data = this.registrationBatchRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Id not found."));

        this.validate.dateValidate(
                bodyUpdate.getStart_date(),
                bodyUpdate.getEnd_date()
        );

        data.setIndex(bodyUpdate.getIndex());
        data.setMax_quota(bodyUpdate.getMax_quota());
        data.setStart_date(bodyUpdate.getStart_date());
        data.setEnd_date(bodyUpdate.getEnd_date());
        data.setBank_name(bodyUpdate.getBank_name());
        data.setBank_user(bodyUpdate.getBank_user());
        data.setPrice(bodyUpdate.getPrice());
        data.setBank_account(bodyUpdate.getBank_account());

        return this.registrationBatchRepository.save(data);
    }

    @Override
    public void delete(Integer id) {
        this.registrationBatchRepository.deleteById(id);
    }

    @Override
    public List<Object[]> getTotalPendaftarPerBatch() {
        return this.registrationBatchRepository.findTotalPendaftarPerBatch();
    }

    @Override
    public Long getTotalPendaftar(){
        List<Object[]> result = this.registrationBatchRepository.findTotalPendaftarPerBatch();
        Long totalPendaftar = 0L;
        for(Object[] row : result){
            Integer idBatch = (Integer) row[0];
            totalPendaftar = (Long) row[1];
        }

        return totalPendaftar;
    }
}
