package dev.pack.modules.student;

import dev.pack.constraint.ErrorMessage;
import dev.pack.exception.DataNotFoundException;
import dev.pack.exception.DuplicateDataException;
import dev.pack.modules.auth.AuthenticationService;
import dev.pack.modules.enums.FormPurchaseType;
import dev.pack.modules.enums.PaymentMethod;
import dev.pack.modules.lookup.Lookup;
import dev.pack.modules.lookup.LookupRepository;
import dev.pack.modules.registration_batch.ChooseBatchDto;
import dev.pack.modules.registration_batch.GetStagingStatusDto;
import dev.pack.modules.registration_batch.RegistrationBatch;
import dev.pack.modules.registration_batch.RegistrationBatchRepository;
import dev.pack.modules.staging.Staging;
import dev.pack.modules.staging.StagingRepository;
import dev.pack.modules.student_logs.StudentLogs;
import dev.pack.modules.student_logs.StudentLogsRepository;
import dev.pack.modules.student_payments.StudentPaymentRepository;
import dev.pack.modules.student_payments.StudentPayments;
import dev.pack.modules.user.User;
import dev.pack.services.FilesStorageService;
import dev.pack.utils.Filenameutils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;
    private final LookupRepository lookupRepository;
    private final RegistrationBatchRepository registrationBatchRepo;
    private final StudentLogsRepository studentLogsRepository;
    private final StagingRepository stagingRepository;
    private final AuthenticationService authenticationService;
    private final StudentPaymentRepository studentPaymentRepository;
    private final FilesStorageService filesStorageService;

    @Override
    @Transactional
    public Student createStudent(Student bodyStudent, Integer idStudent) {
        checkIfNISNExists(bodyStudent.getNisn());

        Student existingStudent = studentRepository.findById(idStudent)
                .orElseThrow(() -> new DataNotFoundException(ErrorMessage.STUDENT_ID_NOT_FOUND));

        Lookup dataLookup = getLookupByType(bodyStudent.getMajor());

        existingStudent.setMajor(dataLookup.getType());

        return studentRepository.save(existingStudent);
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    @Transactional
    public RegistrationBatch chooseRegistrationBatch(ChooseBatchDto batchDto) {
        RegistrationBatch registrationBatch = this.registrationBatchRepo.findById(batchDto.getBatch_id()).orElseThrow(() -> new DataNotFoundException("Gelombang tidak ditemukan"));

        User user = this.authenticationService.decodeJwt();

        Staging staging = this.stagingRepository.findByName("Pilih Gelombang PPDB").orElseThrow(() -> new DataNotFoundException("Data yang diinput invalid"));

        Student student = this.studentRepository.findById(user.getStudent().getId()).orElseThrow(() -> new DataNotFoundException("Data not found"));
        student.setBatch_id(batchDto.getBatch_id());
        student.setPath_id(registrationBatch.getRegistrationPaths().getId());


        this.studentLogsRepository.save(
                StudentLogs.builder()
                        .registrationBatch(RegistrationBatch.builder().id(batchDto.getBatch_id()).build())
                        .path_id(registrationBatch.getRegistrationPaths().getId())
                        .remark("Melakukan Pendaftaran")
                        .staging(staging)
                        .status("REGISTERED")
                        .type(FormPurchaseType.PEMBELIAN)
                        .student(student)
                        .build()
        );

        this.studentRepository.save(student);

        return registrationBatch;
    }

    private void checkIfNISNExists(String nisn) {
        studentRepository.findByNisn(nisn)
                .ifPresent(nisnResult -> {
                    throw new DuplicateDataException(ErrorMessage.NISN_EXISTS);
                });
    }

    private Lookup getLookupByType(String major) {
        return lookupRepository.getLookupByType(major)
                .orElseThrow(() -> new DataNotFoundException(String.format(ErrorMessage.LOOOKUP_NOT_FOUND,major)));
    }

    private RegistrationBatch getRegistrationBatchById(Integer batchId) {
        return registrationBatchRepo.findById(batchId)
                .orElseThrow(() -> new DataNotFoundException(ErrorMessage.BATCH_ID_NOT_FOUND));
    }

    private Staging getStagingByName(String name) {
        return stagingRepository.findByName(name)
                .orElseThrow(() -> new DataNotFoundException(ErrorMessage.STAGING_NAME_NOT_FOUND));
    }

    @Override
    public Optional<StudentLogs> getCurrentRegistrationStatus(GetStagingStatusDto stagingStatusDto) {
        Staging staging = this.stagingRepository.findById(stagingStatusDto.getStagingId()).orElseThrow(() -> new DataNotFoundException("Data tidak ditemukan"));
        Student student = this.studentRepository.findById(stagingStatusDto.getStudentId()).orElseThrow(() -> new DataNotFoundException("Data tidak ditemukan"));

        return this.studentLogsRepository.findByStudentAndStaging(student,staging);
    }

    @Override
    @Transactional
    public StudentLogs uploadPayment(UploadPaymentDto uploadPaymentDto) {
        User user = this.authenticationService.decodeJwt();
        Staging staging = this.stagingRepository.findByName("Transaksi Pembelian").orElseThrow(() -> new DataNotFoundException("Data yang diinput invalid"));

        // upload file
        String extension = Filenameutils.getExtensionByStringHandling(uploadPaymentDto.file.getOriginalFilename()).get();
        String newFileName = Filenameutils.getRandomName() + "." + extension;
        this.filesStorageService.save(uploadPaymentDto.file,newFileName);


        this.studentPaymentRepository.save(
                StudentPayments.builder()
                        .batch_id(user.getStudent().getBatch_id())
                        .path_id(user.getStudent().getPath_id())
                        .status("WAITING_PAYMENT")
                        .method(PaymentMethod.valueOf(uploadPaymentDto.payment_method))
                        .image(newFileName)
                        .type(FormPurchaseType.PEMBELIAN)
                        .total(Double.valueOf(uploadPaymentDto.amount))
                        .build()
        );

        return this.studentLogsRepository.save(
                StudentLogs.builder()
                        .registrationBatch(RegistrationBatch.builder().id(user.getStudent().getBatch_id()).build())
                        .path_id(user.getStudent().getPath_id())
                        .remark("Mengupload bukti pembayaran")
                        .staging(staging)
                        .status("WAITING_PAYMENT")
                        .type(FormPurchaseType.PEMBELIAN)
                        .student(user.getStudent())
                        .build()
        );

    }
}
