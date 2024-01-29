package dev.pack.modules.registration_batch;

import dev.pack.exception.DataNotFoundException;
import dev.pack.modules.enums.FormPurchaseType;
import dev.pack.modules.enums.Grade;
import dev.pack.modules.registration_batch.interfaces.CountAllBatchStudents;
import dev.pack.modules.registration_batch.interfaces.CountPerBatch;
import dev.pack.modules.registration_batch.interfaces.GetAllRegistrationBatch;
import dev.pack.modules.registration_batch.interfaces.GetAllStudentForExport;
import dev.pack.modules.registration_batch.interfaces.GetAllStudentsByBatch;
import dev.pack.modules.registration_paths.RegistrationPaths;
import dev.pack.modules.registration_paths.RegistrationPathsRepository;
import dev.pack.modules.student.Student;
import dev.pack.modules.student.StudentRepository;
import dev.pack.modules.student_logs.StudentLogs;
import dev.pack.modules.student_logs.StudentLogsRepository;
import dev.pack.modules.student_payments.StudentPaymentRepository;
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
    private final StudentLogsRepository studentLogsRepository;
    private final StudentPaymentRepository studentPaymentRepository;
    private final StudentRepository studentRepository;

    private final Validator validate;

    @Override
    @Transactional
    public RegistrationBatch store(RegistrationBatch bodyCreate) {
        if(bodyCreate.getMax_quota() > 500){
            throw new IllegalArgumentException("Max quota only have 500 set");
        }

        RegistrationPaths registrationPaths = registrationPathsRepository.findById(bodyCreate.getPath_id())
                .orElseThrow(() -> new DataNotFoundException(REGISTRATION_PATHS_ID_NOT_FOUND));

        bodyCreate.setRegistrationPaths(registrationPaths);
        bodyCreate.setGrade(registrationPaths.getGrade());

        return  this.registrationBatchRepository.save(bodyCreate);
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
    public CountPerBatch countStudent(Integer batchId) {
        return this.studentRepository.countStudentDetailPerBatch(batchId);
    }

    @Override
    public List<CountAllBatchStudents> countAllStudentsPerBatch() {
        return this.registrationBatchRepository.countAllTotalPendaftarPerBatch();
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

        assert student.getBatch_id() != null;
        RegistrationBatch registrationBatch = this.registrationBatchRepository.findById(student.getBatch_id())
                .orElseThrow();

        RegistrationPaths registrationPaths = this.registrationPathsRepository.findById(student.getPath_id())
                .orElseThrow(() -> new DataNotFoundException("Jalur pendaftaran id tidak ditemukan"));

        this.studentLogsRepository.deleteStudentLogsByStudentId(studentId, registrationPaths.getType());
        this.studentPaymentRepository.deleteStudentPaymentsByStudentId(studentId, registrationPaths.getType());

        List<StudentLogs> logs = this.studentLogsRepository.findAllLatestLogByStudentId(studentId);
        List<GetAllStudentForExport> studentsData = this.registrationBatchRepository.findAllStudentByGrade(String.valueOf(student.getGrade()));

        if(registrationPaths.getType().equals(FormPurchaseType.PEMBELIAN)){
            this.studentRepository.deleteStudentFromBatchByStudentId(studentId);

            student.setBatch_id(null);
            student.setPath_id(null);
            student.setFormulirId(null);
            student.setLastInsertedNumber(null);
            student.setRegistrationDate(null);

//            this.adjustRunningNumberOnDelete(student.getBatch_id());
            for(GetAllStudentForExport studentData : studentsData){
                if(student.getId().equals(studentData.getId())){
                    String existingLastInsertedNumber = studentData.getFormulir_Id()
                            .substring(studentData.getFormulir_Id().lastIndexOf("-") + 1);
                    student.setFormulirId(studentData.getFormulir_Id());
                    student.setLastInsertedNumber(existingLastInsertedNumber);
                }
            }

            student.setIsPurchasingDone(false);
            student.setRegistrationDate(null);

        } else if (registrationPaths.getType().equals(FormPurchaseType.PENGEMBALIAN)){
            for(GetAllStudentForExport studentData : studentsData){
                if(student.getId().equals(studentData.getId())){
                    String existingLastInsertedNumber = studentData.getFormulir_Id()
                            .substring(studentData.getFormulir_Id().lastIndexOf("-") + 1);
                    student.setFormulirId(studentData.getFormulir_Id());
                    student.setLastInsertedNumber(existingLastInsertedNumber);
                }
            }
            StudentLogs latestLogs = logs.get(0);

            student.setPath_id(latestLogs.getPath_id());
            student.setBatch_id(latestLogs.getStudent().getBatch_id());
            student.setProfile_picture(null);
            student.setFamily_card(null);
            student.setBirth_card(null);
            student.setNisn(null);
            student.setSurname(null);
            student.setGender(null);
            student.setReligion(null);
            student.setBirth_place(null);
            student.setBirth_date(null);
            student.setProvince(null);
            student.setCity(null);
            student.setDistrict(null);
            student.setSub_district(null);
            student.setPostal_code(null);
            student.setDad_name(null);
            student.setDad_phone(null);
            student.setDad_job(null);
            student.setDad_address(null);
            student.setMother_name(null);
            student.setMother_phone(null);
            student.setMother_job(null);
            student.setMother_address(null);
            student.setPathName(registrationBatch.getName());
            student.setIsReturningDone(false);
        }

        response.put("status","SUCCESS");
        response.put("message","Siswa berhasil dihapus dari gelombang");

        return response;
    }
}
