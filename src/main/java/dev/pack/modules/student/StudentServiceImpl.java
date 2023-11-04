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
import dev.pack.filestorage.FilesStorageService;
import dev.pack.utils.Filenameutils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Objects;

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

        assert dataLookup != null;
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
        RegistrationBatch registrationBatch = this.registrationBatchRepo.findById(batchDto.getBatch_id())
                .orElseThrow(() -> new DataNotFoundException("Gelombang tidak ditemukan"));

        User user = this.authenticationService.decodeJwt();

        Staging staging = this.stagingRepository.findByName("Pilih Jalur PPDB")
                .orElseThrow(() -> new DataNotFoundException("Data yang diinput invalid"));

        if(batchDto.getType() == FormPurchaseType.PEMBELIAN){
            staging = this.stagingRepository.findByName("Pilih Gelombang PPDB")
                    .orElseThrow(() -> new DataNotFoundException("Data yang diinput invalid"));;
        }
        
        Student student = this.studentRepository.findById(user.getStudent().getId())
                .orElseThrow(() -> new DataNotFoundException("Data not found"));

        student.setRegistrationDate(new Date());
        student.setBatch_id(batchDto.getBatch_id());
        student.setStatus("REGISTERED");
        student.setPath_id(registrationBatch.getRegistrationPaths().getId());

        this.studentRepository.save(student);

        this.studentLogsRepository.save(
                StudentLogs.builder()
                        .registrationBatch(RegistrationBatch.builder().id(batchDto.getBatch_id()).build())
                        .path_id(registrationBatch.getRegistrationPaths().getId())
                        .remark("Melakukan Pendaftaran Proses Pengembalian")
                        .staging(staging)
                        .status("REGISTERED")
                        .type(batchDto.getType())
                        .student(student)
                        .build()
        );

        return registrationBatch;
    }

    private void checkIfNISNExists(String nisn) {
        studentRepository.findByNisn(nisn)
                .ifPresent(nisnResult -> {
                    throw new DuplicateDataException(ErrorMessage.NISN_EXISTS);
                });
    }

    private Lookup getLookupByType(String major) {
//        return lookupRepository.getLookupByType(major);

        return null;
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
    public StudentOffsetResponse getCurrentRegistrationStatus(GetStagingStatusDto stagingStatusDto) {
        User user = this.authenticationService.decodeJwt();
        Student student = user.getStudent();

        if(user.getStudent() == null) {
            throw new DataNotFoundException("Data tidak ditemukan");
        }

        student.setPhone(user.getUsername());


        Staging staging = this.stagingRepository.findById(stagingStatusDto.getStagingId()).orElseThrow(() -> new DataNotFoundException("Data tidak ditemukan"));
        Lookup major = this.lookupRepository.getByValue(student.getMajor()).orElse(null);
        StudentLogs studentLogs =  this.studentLogsRepository.findByStudentAndStagingByType(student,staging,stagingStatusDto.getType()).orElse(null);
        StudentLogs currentState = this.studentLogsRepository.findCurrentStaging(student).orElse(null);

        StudentPayments paymentStatus = null;
        RegistrationBatch registrationBatch = null;
        if(currentState != null) {
            paymentStatus = this.studentPaymentRepository
                    .findStudentPaymentStatusByType(
                            student,
                            currentState.getType()
                    ).orElse(null);
            registrationBatch = currentState.getRegistrationBatch();
        }


        return StudentOffsetResponse.builder()
                .studentLogs(studentLogs)
                .currentState(currentState)
                .student(student)
                .major(major)
                .registrationBatch(registrationBatch)
                .studentPayments(paymentStatus)
                .build();
    }

    protected String saveFileToDisk(MultipartFile file) {
        String extension = Filenameutils.getExtensionByStringHandling(file.getOriginalFilename()).get();
        String newFileName = Filenameutils.getRandomName() + "." + extension;
        this.filesStorageService.save(file,newFileName);

        return newFileName;
    }

    @Override
    @Transactional
    public StudentLogs uploadPayment(UploadPaymentDto uploadPaymentDto) {
        User user = this.authenticationService.decodeJwt();

        // ganti disini kalo stagingnya berubah
        Staging staging = this.stagingRepository.findByName("Pembelian Formulir Pendaftaran")
                .orElseThrow(() -> new DataNotFoundException("Data yang diinput invalid"));

        if(uploadPaymentDto.getType() == FormPurchaseType.PENGEMBALIAN){
            staging = this.stagingRepository.findByName("Transaksi Pengembalian")
                    .orElseThrow(() -> new DataNotFoundException("Data yang diinput invalid"));
        }

        this.studentPaymentRepository.findStudentPaymentStatusByType(user.getStudent(),uploadPaymentDto.getType())
                .ifPresent((d) -> {
                    throw new DuplicateDataException("Anda sudah pernah upload data pembayaran");
                });

        // upload file
        String newFileName = this.saveFileToDisk(uploadPaymentDto.file);

        Student student = user.getStudent();
        student.setStatus("WAITING_PAYMENT");


        this.studentRepository.save(student);


        this.studentPaymentRepository.save(
                StudentPayments.builder()
                        .batch_id(user.getStudent().getBatch_id())
                        .path_id(user.getStudent().getPath_id())
                        .status("WAITING_PAYMENT")
                        .method(PaymentMethod.valueOf(uploadPaymentDto.payment_method))
                        .image(newFileName)
                        .bank_account(uploadPaymentDto.bank_account)
                        .bank_name(uploadPaymentDto.bank_name)
                        .bank_user(uploadPaymentDto.bank_user)
                        .student(user.getStudent())
                        .type(uploadPaymentDto.type)
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
                        .type(uploadPaymentDto.type)
                        .student(user.getStudent())
                        .build()
        );

    }

    public StudentLogs printCard(PrintCardDto printCardDto) {
        User user = this.authenticationService.decodeJwt();
        Staging staging = this.stagingRepository.findByNameAndStagingType("Cetak Kartu Peserta",printCardDto.getType()).orElseThrow(() -> new DataNotFoundException("Staging tidak ditemukan"));

        StudentLogs studentLogs = this.studentLogsRepository.findByStudentAndStagingByType(user.getStudent(),staging,printCardDto.getType()).orElse(null);

        if(studentLogs != null) {
            return studentLogs;
        }


        return this.studentLogsRepository.save(
                StudentLogs.builder()
                        .registrationBatch(RegistrationBatch.builder().id(user.getStudent().getBatch_id()).build())
                        .path_id(user.getStudent().getPath_id())
                        .remark("Cetak Kartu Peserta Proses Pembelian")
                        .staging(staging)
                        .type(printCardDto.getType())
                        .student(user.getStudent())
                        .build()
        );
    }

    public StudentLogs confirmPayment(ConfirmPaymentDto dto) {
        User user = this.authenticationService.decodeJwt();

        if(Objects.equals(user.getRole_id().getRole_name(), "User")){
            throw new BadCredentialsException("Konfirmasi hanya boleh dilakukan oleh admin");
        }

        Student student = this.studentRepository.findById(dto.getStudent_id()).orElseThrow(() -> new DataNotFoundException("Siswa tidak ditemukan"));
        student.setStatus("PAYMENT_CONFIRMED");

        StudentPayments studentPayments = this.studentPaymentRepository.findById(dto.getPayment_id()).orElseThrow(() -> new DataNotFoundException("Data tidak ditemukan"));

        var stagingName = "Pembelian Formulir Pendaftaran";
        if(studentPayments.getType() == FormPurchaseType.PENGEMBALIAN){
            stagingName = "Transaksi Pengembalian";
        }

        Staging staging = this.stagingRepository.findByNameAndStagingType(stagingName,studentPayments.getType()).orElseThrow(() -> new DataNotFoundException("Staging tidak ditemukan"));

        studentPayments.setStatus("PAYMENT_CONFIRMED");
        this.studentPaymentRepository.save(studentPayments);

        return this.studentLogsRepository.save(
                StudentLogs.builder()
                        .registrationBatch(RegistrationBatch.builder().id(student.getBatch_id()).build())
                        .path_id(student.getPath_id())
                        .remark("Pembayaran Terkonfirmasi Admin")
                        .staging(staging)
                        .status("PAYMENT_CONFIRMED")
                        .type(studentPayments.getType())
                        .student(student)
                        .build()
        );
    }

    @Override
    public Student getStudentById(Integer studentId) {
        return this.studentRepository.findById(studentId).orElseThrow(() -> new DataNotFoundException("Student id not found."));
    }

    @Override
    @Transactional
    public StudentLogs updateBio(UpdateBioDto updateBioDto) {
        User user = this.authenticationService.decodeJwt();
        Staging staging = this.stagingRepository.findByName("Isi Biodata")
                .orElseThrow(() -> new DataNotFoundException("Data yang diinput invalid"));

        String profile_pictire = this.saveFileToDisk(updateBioDto.getProfile_picture());
        String birth_card = this.saveFileToDisk(updateBioDto.getBirth_card());
        String family_card = this.saveFileToDisk(updateBioDto.getFamily_card());


        Student student = user.getStudent();
        if(student == null){
            throw new DataNotFoundException("Akses hanya diberikan untuk Siswa pendaftar");
        }

        student.setProfile_picture(profile_pictire);
        student.setFamily_card(family_card);
        student.setBirth_card(birth_card);
        student.setNisn(updateBioDto.getNisn());
        student.setName(updateBioDto.getName());
        student.setPhone(updateBioDto.getPhone());
        student.setSurname(updateBioDto.getSurname());
        student.setGender(updateBioDto.getGender());
        student.setReligion(updateBioDto.getReligion());
        student.setBirth_place(updateBioDto.getBirth_place());
        student.setBirth_date(updateBioDto.getBirth_date());
        student.setAddress(updateBioDto.getAddress());
        student.setProvince(updateBioDto.getProvince());
        student.setCity(updateBioDto.getCity());
        student.setDistrict(updateBioDto.getDistrict());
        student.setSub_district(updateBioDto.getSub_district());
        student.setPostal_code(updateBioDto.getPostal_code());
        student.setSchool_origin(updateBioDto.getSchool_origin());
        student.setDad_name(updateBioDto.getDad_name());
        student.setDad_phone(updateBioDto.getDad_phone());
        student.setDad_job(updateBioDto.getDad_job());
        student.setDad_address(updateBioDto.getDad_address());
        student.setMother_name(updateBioDto.getMother_name());
        student.setMother_phone(updateBioDto.getMother_phone());
        student.setMother_job(updateBioDto.getMother_job());
        student.setMother_address(updateBioDto.getMother_address());
        student.setStatus("FILLING_BIO");


        this.studentRepository.save(student);

        return this.studentLogsRepository.save(
                StudentLogs.builder()
                        .registrationBatch(RegistrationBatch.builder().id(student.getBatch_id()).build())
                        .path_id(student.getPath_id())
                        .remark("Pengisian Biodata")
                        .staging(staging)
                        .status("FILLING_BIO")
                        .type(FormPurchaseType.PENGEMBALIAN)
                        .student(student)
                        .build()
        );

    }

    @Override
    public StudentLogs chooseMajor(ChooseMajorDto majorDto) {
        User user = this.authenticationService.decodeJwt();
        Staging staging = this.stagingRepository
                .findByNameAndStagingType("Pilih Jurusan",majorDto.getType())
                .orElseThrow(() -> new DataNotFoundException("Data yang diinput invalid"));

        String status = "CHOOSING_FIRST_MAJORS";
        Student student = user.getStudent();
        if(majorDto.getType() == FormPurchaseType.PENGEMBALIAN){
            status = "CHOOSING_FIX_MAJOR";
        }

        student.setStatus(status);
        student.setMajor(majorDto.getMajor());
        this.studentRepository.save(student);


        return this.studentLogsRepository.save(
                StudentLogs.builder()
                        .registrationBatch(RegistrationBatch.builder().id(user.getStudent().getBatch_id()).build())
                        .path_id(user.getStudent().getPath_id())
                        .remark("Memilih Jurusan")
                        .majors(majorDto.getMajor())
                        .staging(staging)
                        .status(status)
                        .type(majorDto.getType())
                        .student(user.getStudent())
                        .build()
        );
    }

    @Override
    public void deleteById(Integer studentId) {
        Student data = this.studentRepository.findById(studentId).orElseThrow(() -> new DataNotFoundException("Id not found"));

        this.studentRepository.delete(data);
    }

    @Override
    public List<Student> getAllStudentByGrade(String grade) {
        List<Student> datas = this.studentRepository.findAllStudentByGrade(grade);
        if( !Objects.equals(grade, "SMK") && !Objects.equals(grade, "SMP") ) throw new DataNotFoundException("Grade is invalid");
        return datas;
    }
}
