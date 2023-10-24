package dev.pack.modules.registration_batch;

import dev.pack.exception.DataNotFoundException;
import dev.pack.modules.enums.FormPurchaseType;
import dev.pack.modules.registration_paths.RegistrationPaths;
import dev.pack.modules.registration_paths.RegistrationPathsRepository;
import dev.pack.utils.Validator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<RegistrationBatch> index(Integer regisPathsId) {
        this.registrationPathsRepository.findById(regisPathsId)
                .orElseThrow(() -> new DataNotFoundException("Id not found."));
        return this.registrationBatchRepository.findTotalPendaftarPerBatchModel(regisPathsId);
    }

    @Override
    public List<RegistrationBatch> getAllBatchByType(FormPurchaseType type) {
        return this.registrationBatchRepository.getAllByType(type);
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
        data.setName(bodyUpdate.getName());
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
    public Map<String, String> delete(Integer id) {
        Map<String, String> res = new HashMap<>();

        this.registrationBatchRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Id not found."));
        this.registrationBatchRepository.deleteById(id);

        res.put("status","SUCCESS");

        return res;
    }

    @Override
    public List<RegistrationBatch> countStudents(Integer regisPathsId) {
        List<RegistrationBatch> resultData = this.registrationBatchRepository.findTotalPendaftarPerBatchModel(regisPathsId);
        updateCountStudent(regisPathsId);
        return resultData;
    }

    @Transactional
    public void updateCountStudent(Integer regisPathsId){
        this.registrationBatchRepository.updateCountStudent(regisPathsId);
    }

    @Override
    public List<RegistrationBatch> getAllGelombangByPathsId(Integer pathsId) {
        this.registrationPathsRepository.findById(pathsId)
                .orElseThrow(() -> new DataNotFoundException("Id paths not found."));

        return this.registrationBatchRepository.findByRegistrationPathsId(pathsId);
    }
}
