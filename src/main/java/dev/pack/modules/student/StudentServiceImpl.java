package dev.pack.modules.student;

import dev.pack.constraint.ErrorMessage;
import dev.pack.exception.DataNotFoundException;
import dev.pack.exception.DuplicateDataException;
import dev.pack.exception.MaxQuotaReachedException;
import dev.pack.filestorage.FilesStorageService;
import dev.pack.modules.auth.AuthenticationService;
import dev.pack.modules.enums.FormPurchaseType;
import dev.pack.modules.enums.Grade;
import dev.pack.modules.enums.PaymentMethod;
import dev.pack.modules.lookup.Lookup;
import dev.pack.modules.lookup.LookupRepository;
import dev.pack.modules.registration_batch.ChooseBatchDto;
import dev.pack.modules.registration_batch.GetStagingStatusDto;
import dev.pack.modules.registration_batch.RegistrationBatch;
import dev.pack.modules.registration_batch.RegistrationBatchRepository;
import dev.pack.modules.registration_paths.RegistrationPaths;
import dev.pack.modules.registration_paths.RegistrationPathsRepository;
import dev.pack.modules.staging.Staging;
import dev.pack.modules.staging.StagingRepository;
import dev.pack.modules.student_logs.StudentLogs;
import dev.pack.modules.student_logs.StudentLogsRepository;
import dev.pack.modules.student_payments.StudentPaymentRepository;
import dev.pack.modules.student_payments.StudentPayments;
import dev.pack.modules.user.User;
import dev.pack.utils.Filenameutils;
import dev.pack.utils.StudentUtils;
import dev.pack.utils.excel.ExcelService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Year;
import java.util.*;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;
    private final LookupRepository lookupRepository;
    private final RegistrationBatchRepository registrationBatchRepo;
    private final RegistrationPathsRepository registrationPathsRepository;
    private final StudentLogsRepository studentLogsRepository;
    private final StagingRepository stagingRepository;
    private final AuthenticationService authenticationService;
    private final StudentPaymentRepository studentPaymentRepository;
    private final FilesStorageService filesStorageService;
    private final StudentUtils studentUtils;
    private final StudentAchievementRepository studentAchievementRepository;
    private final ExcelService excelService;

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
    public ResponseStudentDto getDetailStudentPembelian(Integer studentId) {
        Student dataStudent = this.studentRepository.findById(studentId).orElseThrow(() -> new DataNotFoundException("Id siswa tidak ditemukan."));

        return ResponseStudentDto.builder()
                .id(dataStudent.getId())
                .phone(dataStudent.getPhone())
                .name(dataStudent.getName())
                .address(dataStudent.getAddress())
                .school_origin(dataStudent.getSchool_origin())
                .build();
    }

    @Override
    public void exportExcelDataStudent(HttpServletResponse response, Integer batchId) throws IOException {
        try{
            this.registrationBatchRepo.findById(batchId)
                    .orElseThrow(() -> new DataNotFoundException("Id gelombang tidak ditemukan"));

            List<Student> students = this.registrationBatchRepo.findAllStudentByBatchId(batchId);

            List<String> headers = Arrays.asList(
                    "Id",
                    "Formulir-id",
                    "Pendaftar ke",
                    "Nama siswa",
                    "Nomor telepon",
                    "Alamat",
                    "Jenjang",
                    "Jenis kelamin",
                    "Agama",
                    "Asal sekolah",
                    "Jurusan",
                    "Tanggal mendaftar"
            );

            List<List<Object>> data = new ArrayList<>();
            Year year = Year.now();

            for(Student student : students){
                List<Object> rowData = new ArrayList<>();

                rowData.add(student.getId());
                rowData.add(student.getFormulirId());
                rowData.add(student.getLastInsertedNumber());
                rowData.add(student.getName());
                rowData.add(student.getPhone());
                rowData.add(student.getAddress());
                rowData.add(student.getGrade());
                rowData.add(student.getGender());
                rowData.add(student.getReligion());
                rowData.add(student.getSchool_origin());
                rowData.add(student.getMajor());
                rowData.add(student.getRegistrationDate());

                data.add(rowData);
            }

            excelService.generateExcelCustomHeader(response,String.format("Data siswa %s",year), headers, data);
        } catch (Exception err){
            throw new IOException(err);
        }
    }

    @Override
    public Student getDetailStudentPengembalian(Integer studentId) {
        return this.studentRepository.findById(studentId).orElseThrow(() -> new DataNotFoundException("Id siswa tidak ditemukan."));
    }

    @Override
    @Transactional
    public RegistrationBatch chooseRegistrationBatch(ChooseBatchDto batchDto) {
        RegistrationBatch registrationBatch = this.registrationBatchRepo.findById(batchDto.getBatch_id())
                .orElseThrow(() -> new DataNotFoundException("Gelombang tidak ditemukan"));

        RegistrationPaths registrationPaths = this.registrationPathsRepository.findById(registrationBatch.getPath_id())
                .orElseThrow();

        User user = this.authenticationService.decodeJwt();

        Staging staging = this.stagingRepository.findByName("Pilih Jalur PPDB", batchDto.getGrade())
                .orElseThrow(() -> new DataNotFoundException("Data yang diinput invalid"));

        if(batchDto.getType() == FormPurchaseType.PEMBELIAN){
            staging = this.stagingRepository.findByName("Pilih Gelombang PPDB", batchDto.getGrade())
                    .orElseThrow(() -> new DataNotFoundException("Data yang diinput invalid"));;
        }

        long lastInsertedCount = this.registrationBatchRepo.countStudentsForRunningNumber(batchDto.getBatch_id());
        if(lastInsertedCount+1 > registrationBatch.getMax_quota()){
            throw new MaxQuotaReachedException("Kuota gelombang sudah penuh, harap pilih gelombang lain.");
        }

        Student student = this.studentRepository.findById(user.getStudent().getId())
                .orElseThrow(() -> new DataNotFoundException("Data not found"));

        long runningNumber = lastInsertedCount+1;

        String formulirId = studentUtils.generateIdStudent(runningNumber,registrationBatch.getBatchCode());

        student.setFormulirId(formulirId);
        student.setLastInsertedNumber(String.valueOf(runningNumber));
        student.setRegistrationDate(new Date());
        student.setBatch_id(batchDto.getBatch_id());
        student.setStatus("REGISTERED");
        student.setPath_id(registrationBatch.getRegistrationPaths().getId());

        Integer runningCount = registrationBatch.getCountStudent() + 1;

        registrationBatch.setCountStudent(runningCount);
        registrationPaths.setCountStudent(runningCount);

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

//    private Staging getStagingByName(String name) {
//        return stagingRepository.findByName(name, )
//                .orElseThrow(() -> new DataNotFoundException(ErrorMessage.STAGING_NAME_NOT_FOUND));
//    }

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
        Staging staging = this.stagingRepository.findByName("Pembelian Formulir Pendaftaran", user.getStudent().getGrade())
                .orElseThrow(() -> new DataNotFoundException("Data yang diinput invalid"));

        if(uploadPaymentDto.getType() == FormPurchaseType.PENGEMBALIAN){
            staging = this.stagingRepository.findByName("Transaksi Pengembalian", user.getStudent().getGrade())
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

        //TODO : CREATE STAGING FOR SMP

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

    @Override
    public List<StudentAchievement> getStudentAchievement(GetStudentAchievementDto dto) {
        Student student = this.studentRepository.findById(dto.getStudentId()).orElseThrow(() -> new DataNotFoundException("Data siswa tidak ditemukan"));
        return this.studentAchievementRepository.getStudentAchievementByStudentAndType(student,dto.getType());
    }

    public StudentLogs printCard(PrintCardDto printCardDto) {
        User user = this.authenticationService.decodeJwt();

        Staging staging = this.stagingRepository.findByNameAndStagingType("Cetak Formulir",printCardDto.getType(), user.getStudent().getGrade())
                .orElseThrow(() -> new DataNotFoundException("Staging tidak ditemukan"));

        StudentLogs studentLogs = this.studentLogsRepository.findByStudentAndStagingByType(user.getStudent(),staging,printCardDto.getType())
                .orElse(null);

        if(studentLogs != null) {
            return studentLogs;
        }
        String remark = "Cetak Formulir Proses Pembelian";
        String status = "PRINT_CARD_PURCHASED";

        if(printCardDto.getType() == FormPurchaseType.PENGEMBALIAN) {
            remark = "Cetak Formulir Proses Pengembalian";
            status = "PRINT_CARD_RETURNING";
        }


        return this.studentLogsRepository.save(
                StudentLogs.builder()
                        .registrationBatch(
                                RegistrationBatch.builder()
                                        .id(user.getStudent().getBatch_id())
                                        .build())
                        .path_id(user.getStudent().getPath_id())
                        .remark(remark)
                        .staging(staging)
                        .status(status)
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

        Staging staging = this.stagingRepository.findByNameAndStagingType(stagingName,studentPayments.getType(), student.getGrade()).orElseThrow(() -> new DataNotFoundException("Staging tidak ditemukan"));

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
//        this.registrationBatchRepo.findById(batchId)
//                .orElseThrow(() -> new DataNotFoundException("Id gelombang tidak ditemukan."));
//
//        this.studentRepository.findById(studentId).orElseThrow(() -> new DataNotFoundException("Id student tidak ditemukan"));
//
//        return this.studentRepository.findStudentByIdAnAndBAndBatch_id(studentId, batchId);

        return this.studentRepository.findById(studentId).orElseThrow(() -> new DataNotFoundException("Id tidka ditemukan"));
    }

    @Override
    @Transactional
    public StudentLogs updateBio(UpdateBioDto updateBioDto) {
        User user = this.authenticationService.decodeJwt();
        Staging staging = this.stagingRepository.findByName("Isi Biodata", user.getStudent().getGrade())
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
                .findByNameAndStagingType("Pilih Jurusan",majorDto.getType(), Grade.SMK)
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

    @Override
    public List<StudentPayments> getAllStudentPayments(Integer batchId, Integer studentId) {
        this.registrationBatchRepo.findById(batchId).orElseThrow(() -> new DataNotFoundException("Registration batch id not found."));
        this.studentRepository.findById(studentId).orElseThrow(() -> new DataNotFoundException("Student id not found."));

        return this.studentRepository.findAllStudentPayments(batchId,studentId);
    }
}
