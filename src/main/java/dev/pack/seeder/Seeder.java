package dev.pack.seeder;

import dev.pack.modules.alur_ppdb.AlurPpdbRepository;
import dev.pack.modules.biaya.BiayaRepository;
import dev.pack.modules.biaya_tambahan.BiayaTambahanRepository;
import dev.pack.modules.enums.Banks;
import dev.pack.modules.enums.FormPurchaseType;
import dev.pack.modules.alur_ppdb.AlurPpdb;
import dev.pack.modules.enums.MediaTest;
import dev.pack.modules.gelombang_ppdb.Gelombang;
import dev.pack.modules.gelombang_ppdb.GelombangRepository;
import dev.pack.modules.informasi_umum.InformasiUmumRepository;
import dev.pack.modules.jalur_pendaftaran.JalurPendaftaranRepository;
import dev.pack.modules.jalur_pendaftaran.JalurPendaftaran;
import dev.pack.modules.informasi_umum.InformasiUmum;
import dev.pack.modules.biaya_tambahan.BiayaTambahan;
import dev.pack.modules.biaya.Biaya;
import dev.pack.modules.kegiatan.Kegiatan;
import dev.pack.modules.kegiatan.KegiatanRepository;
import dev.pack.modules.keterangan.Keterangan;
import dev.pack.modules.keterangan.KeteranganRepository;
import dev.pack.modules.pengunguman.Pengunguman;
import dev.pack.modules.pengunguman.PengungumanRepository;
import dev.pack.modules.ujian_penerimaan.UjianPenerimaan;
import dev.pack.modules.ujian_penerimaan.UjianPenerimaanRepository;
import dev.pack.modules.user.User;
import dev.pack.modules.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.UnexpectedRollbackException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static dev.pack.modules.enums.Role.ADMIN;
import static dev.pack.modules.enums.Role.USER;

@Configuration
@Transactional
@RequiredArgsConstructor
public class Seeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final JalurPendaftaranRepository jalurPendaftaranRepository;
    private final InformasiUmumRepository informasiUmumRepository;
    private final KeteranganRepository keteranganRepository;
    private final BiayaTambahanRepository biayaTambahanRepository;
    private final BiayaRepository biayaRepository;
    private final AlurPpdbRepository alurPpdbRepository;
    private final GelombangRepository gelombangRepository;
    private final UjianPenerimaanRepository ujianPenerimaanRepository;
    private final PengungumanRepository pengungumanRepository;
    private final KegiatanRepository kegiatanRepository;

    private final PasswordEncoder passwordEncoder;

    private static final Logger log = LoggerFactory.getLogger(Seeder.class);

    @Override
    public void run(String... args) throws Exception {
        try{
            log.info("Seeding data . . . ");
            long userData = userRepository.count();
            /*
             * Put your seed function here
             */
            seedUserData();
            seedAdminData();
            seedRegisterPath();
            seedPpdbFlows();

            //Put logic repo here.

            if(userData > 0){
                log.info("Data enough, seed not running.");
            }

        } catch (UnexpectedRollbackException ex){
            log.error("Seed data failed, due to error : {}", ex.getMessage());
        }
    }

    public void seedUserData(){
        Integer countDataByRole = userRepository.countByRole(USER);
        if(countDataByRole == 0){
            User user = User.builder()
                    .username("student")
                    .email("Student@gmail.com")
                    .password(passwordEncoder.encode("1234"))
                    .role(USER)
                    .build();

            userRepository.save(user);

            log.info("Seed data :: {} -> {}",user.getRole(), userRepository.countByRole(USER));
        }
    }

    public void seedAdminData(){
        Integer countDataByRole = userRepository.countByRole(ADMIN);
        if(countDataByRole == 0){
            User admin = User.builder()
                    .username("admin")
                    .email("Admin@gmail.com")
                    .password(passwordEncoder.encode("1234"))
                    .role(ADMIN)
                    .build();

            userRepository.save(admin);

            log.info("Seed data :: {} -> {}",admin.getRole(), userRepository.countByRole(ADMIN));
        }
    }

    public void seedPpdbFlows(){
        AlurPpdb alurPpdb = AlurPpdb.builder()
                .title("PPDBFLOW TITLE")
                .content("PPDB FLOW CONTENT")
                .userId(userRepository.getReferenceById(2))
                .build();

        alurPpdbRepository.save(alurPpdb);

        log.info("Seed data :: {} -> {}","PPDBFLOW", alurPpdbRepository.count());
    }

    public void seedRegisterPath(){
        long countData = jalurPendaftaranRepository.count();
        if(countData == 0) {
            List<BiayaTambahan> biayaTambahans = new ArrayList<>();
            InformasiUmum informasiUmum = null;

            JalurPendaftaran jalurPendaftaran = JalurPendaftaran.builder()
                    .tipePembelian(FormPurchaseType.PEMBELIAN_FORMULIR)
                    .namaJalurPendaftaran("PEMBELIAN FORMULIR")
                    .waktuDibuka(new Date())
                    .waktuDitutup(new Date())
                    .biayaPendaftaran(200.00)
                    .informasiUmum(informasiUmum)
                    .userId(userRepository.getReferenceById(2))
                    .build();

            Keterangan keterangan = Keterangan.builder()
                    .namaKeterangan("Pembelian Formulir")
                    .deskripsiKeterangan("Deskripsi pembelian Formulir")
                    .informasiUmumId(informasiUmumRepository.getReferenceById(1))
                    .build();

            informasiUmum = InformasiUmum.builder()
                    .keterangan(keterangan)
                    .biayaTambahan(biayaTambahans)
                    .jalurPendaftaranId(jalurPendaftaranRepository.getReferenceById(1))
                    .build();

            BiayaTambahan biayaTambahan = BiayaTambahan.builder()
                    .judulBiaya("Biaya tambahan gedung")
                    .informasiUmumId(informasiUmumRepository.getReferenceById(1))
                    .build();

            Biaya biaya = Biaya.builder()
                    .biayaTambahanId(biayaTambahanRepository.getReferenceById(1))
                    .namaBiayaTambahan("Biaya tambahan")
                    .jumlahBiayaTambahan(100.00)
                    .build();

            Gelombang gelombang = Gelombang.builder()
                    .namaGelombang("PENGEMBALIAN FORMULIR REGULER GEL.1")
                    .diskonGelombang(50.0)
                    .jumlahPenerimaan(200L)
                    .waktuPendaftaranDibuka(new Date())
                    .waktuPendaftaranDitutup(new Date())
                    .namaBank(Banks.BCA)
                    .nomorRekening("31293123971283")
                    .namaPemilikRekening("USER 1")
                    .biayaPendaftaran(100.00)
                    .jalurPendaftaranId(this.jalurPendaftaranRepository.getReferenceById(1))
//                    .ujianPenerimaanList()
//                    .pengungumanList()
//                    .kegiatanList()
                    .build();

            UjianPenerimaan ujianPenerimaan = UjianPenerimaan.builder()
                    .namaUjianPenerimaan("TEST AKADEMIK")
                    .mediaTest(MediaTest.TEST_ONLINE)
                    .waktuDibuka(new Date())
                    .waktuDitutup(new Date())
                    .lokasiTest("ONLINE")
                    .kkm(90)
                    .gelombangId(this.gelombangRepository.getReferenceById(1))
                    .build();

            Pengunguman pengunguman = Pengunguman.builder()
                    .namaPengunguman("BATAS WAKTU UJIAN")
                    .tanggalPengunguman(new Date())
                    .gelombangId(gelombangRepository.getReferenceById(1))
                    .build();

            Kegiatan kegiatan = Kegiatan.builder()
                    .namaKegiatan("TEST AKADEMIK")
                    .waktuDibuka(new Date())
                    .waktuDitutup(new Date())
                    .gelombangId(gelombangRepository.getReferenceById(1))
                    .build();

            jalurPendaftaranRepository.save(jalurPendaftaran);
            informasiUmumRepository.save(informasiUmum);
            keteranganRepository.save(keterangan);
            biayaTambahanRepository.save(biayaTambahan);
            biayaRepository.save(biaya);
            gelombangRepository.save(gelombang);
            ujianPenerimaanRepository.save(ujianPenerimaan);
            pengungumanRepository.save(pengunguman);
            kegiatanRepository.save(kegiatan);
        }

        log.info("Seed data :: {} -> {}", "JALUR_PENDAFTARAN", jalurPendaftaranRepository.count());
        log.info("Seed data :: {} -> {}", "BIAYA_TAMBAHAN", biayaTambahanRepository.count());
        log.info("Seed data :: {} -> {}", "INFORMASI_UMUM", informasiUmumRepository.count());
        log.info("Seed data :: {} -> {}", "KETERANGAN", keteranganRepository.count());
        log.info("Seed data :: {} -> {}", "BIAYA", biayaRepository.count());
        log.info("Seed data :: {} -> {}", "GELOMBANG", gelombangRepository.count());;
        log.info("Seed data :: {} -> {}", "UJIAN_PENERIMAAN", ujianPenerimaanRepository.count());
        log.info("Seed data :: {} -> {}", "PENGUNGUMAN", pengungumanRepository.count());
        log.info("Seed data :: {} -> {}", "KEGIATAN", kegiatanRepository.count());
    }
}
