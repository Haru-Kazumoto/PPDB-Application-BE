package dev.pack.modules.registration_batch;

import dev.pack.exception.DataNotFoundException;
import dev.pack.modules.enums.FormPurchaseType;
import dev.pack.modules.registration_paths.RegistrationPaths;
import dev.pack.modules.registration_paths.RegistrationPathsRepository;
import dev.pack.modules.student.CountStudents;
import dev.pack.modules.student.Student;
import dev.pack.modules.student.StudentRepository;
import dev.pack.utils.Validator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static dev.pack.constraint.ErrorMessage.*;

@Service
@RequiredArgsConstructor
public class RegistrationBatchServiceImpl implements RegistrationBatchService{

    private final RegistrationBatchRepository registrationBatchRepository;
    private final RegistrationPathsRepository registrationPathsRepository;
    private final StudentRepository studentRepository;

    private final Validator validate;

    @Override
    @Transactional
    public RegistrationBatch store(RegistrationBatch bodyCreate) {
        this.validate.dateValidate(
                bodyCreate.getStart_date(),
                bodyCreate.getEnd_date()
        );

        if(bodyCreate.getMax_quota() > 500){
            throw new IllegalArgumentException("Max quota only have 500 set");
        }

        RegistrationPaths registrationPaths = registrationPathsRepository.findById(bodyCreate.getPath_id())
                .orElseThrow(() -> new DataNotFoundException(REGISTRATION_PATHS_ID_NOT_FOUND));

        bodyCreate.setRegistrationPaths(registrationPaths);

        return this.registrationBatchRepository.save(bodyCreate);
    }

    @Override
    public List<RegistrationBatch> getAllGelombangWhereIsOpen(Boolean condition) {
        return this.registrationBatchRepository.findRegistrationBatchByIsOpen(condition);
    }

    @Override
    public List<GetAllRegistrationBatch> index(Integer regisPathsId) {
        return this.registrationBatchRepository.findAllRegistrationBatch(regisPathsId);
    }

    @Override
    public List<RegistrationBatch> getAllBatchByType(FormPurchaseType type) {
        return this.registrationBatchRepository.getAllByType(type);
    }

    @Override
    public RegistrationBatch update(Integer id, RegistrationBatch bodyUpdate) {
        RegistrationBatch data = this.registrationBatchRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(BATCH_ID_NOT_FOUND));

        this.validate.dateValidate(
                bodyUpdate.getStart_date(),
                bodyUpdate.getEnd_date()
        );

        data.setIndex(bodyUpdate.getIndex());
        data.setName(bodyUpdate.getName());
        data.setMax_quota(bodyUpdate.getMax_quota());
        data.setBatchCode(bodyUpdate.getBatchCode());
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
                .orElseThrow(() -> new DataNotFoundException(BATCH_ID_NOT_FOUND));
        this.registrationBatchRepository.deleteById(id);

        res.put("status","SUCCESS");

        return res;
    }

    @Override
    public List<RegistrationBatch> countStudents(Integer regisPathsId) {
//        List<RegistrationBatch> resultData = this.registrationBatchRepository.findTotalPendaftarPerBatchModel(regisPathsId);
//        updateCountStudent(regisPathsId);
        return null;
    }

    public void updateCountStudent(Integer regisPathsId){
        this.registrationBatchRepository.updateCountStudent(regisPathsId);
    }

    @Override
    public List<RegistrationBatch> getAllGelombangByPathsId(Integer pathsId) {
        this.registrationPathsRepository.findById(pathsId)
                .orElseThrow(() -> new DataNotFoundException(REGISTRATION_PATHS_ID_NOT_FOUND));

        return this.registrationBatchRepository.findByRegistrationPathsId(pathsId);
    }

    @Override
    public RegistrationBatch getById(Integer id) {
        return this.registrationBatchRepository.getRegistrationBatchById(id)
                .orElseThrow(() -> new DataNotFoundException(BATCH_ID_NOT_FOUND));
    }

    @Override
    public List<RegistrationBatch> getRegisBatchByType(FormPurchaseType type) {
        return this.registrationBatchRepository.findRegistrationBatchByPathType(type);
    }

    @Override
    public Page<GetAllStudentsByBatch> getStudentByBatchId(Integer batchId, Pageable pageable) {
        return this.registrationBatchRepository.findAllStudentByBatchId(batchId, pageable);
    }

    @Override
    public CountStudents.DetailBatch countStudent(Integer batchId) {
        RegistrationBatch data = this.registrationBatchRepository.findById(batchId)
                .orElseThrow(() -> new DataNotFoundException(BATCH_ID_NOT_FOUND));

        long total = this.studentRepository.countStudentsByBatchId(data.getId());
        long accepted = this.studentRepository.countConfirmedPaymentStudentsByBatchId(data.getId());

        return CountStudents.DetailBatch.builder()
                .totalStudents(total)
                .studentAccepted(accepted)
                .build();
    }

    @Override
    public List<CountStudents.InfoBatch> countAllStudents() {
        return null;
    }
}
