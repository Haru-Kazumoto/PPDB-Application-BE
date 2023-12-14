package dev.pack.modules.registration_batch;

import dev.pack.exception.DataNotFoundException;
import dev.pack.modules.enums.FormPurchaseType;
import dev.pack.modules.enums.Grade;
import dev.pack.modules.registration_paths.RegistrationPaths;
import dev.pack.modules.registration_paths.RegistrationPathsRepository;
import dev.pack.modules.student.CountStudents;
import dev.pack.modules.student.Student;
import dev.pack.modules.student.StudentRepository;
import dev.pack.modules.student_logs.StudentLogs;
import dev.pack.modules.student_logs.StudentLogsRepository;
import dev.pack.modules.student_payments.StudentPaymentRepository;
import dev.pack.modules.student_payments.StudentPayments;
import dev.pack.utils.StudentUtils;
import dev.pack.utils.Validator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static dev.pack.constraint.ErrorMessage.*;

@Service
@RequiredArgsConstructor
public class RegistrationBatchServiceImpl implements RegistrationBatchService{

    private final RegistrationBatchRepository registrationBatchRepository;
    private final RegistrationPathsRepository registrationPathsRepository;
    private final StudentLogsRepository studentLogsRepository;
    private final StudentPaymentRepository studentPaymentRepository;
    private final StudentRepository studentRepository;

    private final Validator validate;
    private final StudentUtils studentUtils;

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
        bodyCreate.setGrade(registrationPaths.getGrade());

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
    public List<RegistrationBatch> getAllBatchByGrade(Grade grade) {
        return this.registrationBatchRepository.getAllBatchByGrade(grade);
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
    @Transactional
    public Map<String, String> delete(Integer id) {
        Map<String, String> res = new HashMap<>();

        RegistrationBatch registrationBatch = this.registrationBatchRepository.findById(id).orElseThrow(() -> new DataNotFoundException(BATCH_ID_NOT_FOUND));
        FormPurchaseType type = registrationBatch.getRegistrationPaths().getType();
        List<Student> students = this.studentRepository.findAllStudentByBatchId(id);

        for(Student student : students){
            this.studentLogsRepository.deleteStudentLogsByStudentId(student.getId(), type);
            this.studentPaymentRepository.deleteStudentPaymentsByStudentId(student.getId(), type);
            this.studentRepository.deleteStudentFromBatchByStudentId(student.getId());
        }

        this.registrationBatchRepository.delete(registrationBatch);
        res.put("status","SUCCESS");

        return res;
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
    @Transactional
    public Map<String, String> deleteStudentFromBatch(Integer studentId) {
        Map<String, String> response = new HashMap<>();

        Student student = this.studentRepository.findById(studentId).orElseThrow(
                () -> new DataNotFoundException("Id siswa tidak ditemukan")
        );

        if(student.getBatch_id() == null && student.getPath_id() == null) {
            throw new DataNotFoundException("Siswa tidak atau belum terdaftar di gelombang manapun");
        }

        RegistrationPaths registrationPaths = this.registrationPathsRepository.findById(student.getPath_id())
                .orElseThrow(() -> new DataNotFoundException("Jalur pendaftaran id tidak ditemukan"));

        this.studentLogsRepository.deleteStudentLogsByStudentId(studentId, registrationPaths.getType());
        this.studentPaymentRepository.deleteStudentPaymentsByStudentId(studentId, registrationPaths.getType());

        List<StudentLogs> logs = this.studentLogsRepository.findAllLatestLogByStudentId(studentId);

        if(registrationPaths.getType().equals(FormPurchaseType.PEMBELIAN)){
            this.studentRepository.deleteStudentFromBatchByStudentId(studentId);
            this.adjustRunningNumberOnDelete(student.getBatch_id(), student.getLastInsertedNumber());

        } else if (registrationPaths.getType().equals(FormPurchaseType.PENGEMBALIAN)){
            this.adjustRunningNumberOnDelete(student.getBatch_id(), student.getLastInsertedNumber());
            StudentLogs latestLogs = logs.get(0);

            student.setPath_id(latestLogs.getPath_id());
            student.setBatch_id(latestLogs.getStudent().getBatch_id());
        }

        response.put("status","SUCCESS");
        response.put("message","Siswa berhasil dihapus dari gelombang");

        return response;
    }

    private void adjustRunningNumberOnDelete(Integer batchId, String deletedRunningNumber){
        List<Student> studentsToAdjusts = this.studentRepository.findStudentsToAdjust(batchId, deletedRunningNumber);
        RegistrationBatch registrationBatch = this.registrationBatchRepository.findById(batchId).orElseThrow();

        for(Student studentToAdjust : studentsToAdjusts){
            long newRunningNumber = Long.parseLong(studentToAdjust.getLastInsertedNumber()) - 1;
            String formattedRunningNumber = String.format("%03d", newRunningNumber);
            String newFormulirId = this.studentUtils.generateIdStudent(formattedRunningNumber, registrationBatch.getBatchCode());

            studentToAdjust.setLastInsertedNumber(String.valueOf(newRunningNumber));
            studentToAdjust.setFormulirId(newFormulirId);
        }

        this.studentRepository.saveAll(studentsToAdjusts);
    }

}
