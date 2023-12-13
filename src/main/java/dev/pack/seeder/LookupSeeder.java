package dev.pack.seeder;

import dev.pack.modules.lookup.Lookup;
import dev.pack.modules.lookup.LookupRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@Transactional
@RequiredArgsConstructor
public class LookupSeeder implements ApplicationRunner {

    private final LookupRepository lookupRepository;


    private static final Logger log = LoggerFactory.getLogger(RoleSeeder.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(args.getOptionValues("seeder") != null){
            List<String> seeder = Arrays.asList(args.getOptionValues("seeder").get(0).split(","));
            if(seeder.contains("lookup")) {
                seedMajor();
                log.info("Success run lookup seeder");
            }
        }else{
            log.info("lookup seeder skipped");
        }
    }

    private void seedMajor(){
        List<List<String>> lookups = new ArrayList<>();

        lookups.add(List.of("Teknik Jaringan dan Komputer Telekomunikasi","TJKT","MAJOR"));
        lookups.add(List.of("Teknik Kendaraan Ringan","TKR","MAJOR"));
        lookups.add(List.of("Teknik Audio dan Video","TAV","MAJOR"));
        lookups.add(List.of("Akuntansi Keuangan Lembaga","AKL","MAJOR"));

        lookups.add(List.of("Islam","ISLAM","RELIGION"));
        lookups.add(List.of("Kristen","KRISTEN","RELIGION"));
        lookups.add(List.of("Buddha","BUDDHA","RELIGION"));
        lookups.add(List.of("Katolik","KATOLIK","RELIGION"));

        lookups.add(List.of("Terdaftar","REGISTERED","STATUS"));
        lookups.add(List.of("Menunggu Seleksi Berkas","FILE_SELECTION","STATUS"));
        lookups.add(List.of("Menunggu Konfirmasi Pembayaran","WAITING_PAYMENT","STATUS"));
        lookups.add(List.of("Menunggu Seleksi Ujian","TEST_SELECTION","STATUS"));
        lookups.add(List.of("Menunggu Hasil Pengumuman","WAITING_ANNOUNCEMENT","STATUS"));
        lookups.add(List.of("Memilih Peminatan/Jurusan Awal","CHOOSING_FIRST_MAJORS","STATUS"));
        lookups.add(List.of("Memilih Jurusan Tetap","CHOOSING_FIX_MAJOR","STATUS"));
        lookups.add(List.of("Mengisi Biodata","FILLING_BIO","STATUS"));
        lookups.add(List.of("Berkas Terkonfirmasi","FILE_CONFIRMED","STATUS"));
        lookups.add(List.of("Pembayaran Terkonfirmasi","PAYMENT_CONFIRMED","STATUS"));
        lookups.add(List.of("Cetak Formulir","PRINT_CARD_PURCHASED","STATUS"));
        lookups.add(List.of("Cetak Formulir","PRINT_CARD_RETURNING","STATUS"));
        lookups.add(List.of("Lulus Seleksi","SELECTION_PASSED","STATUS"));
        lookups.add(List.of("Tidak Lulus Seleksi","SELECTION_NOT_PASSED","STATUS"));




        for(var lookup : lookups) {
            this.lookupRepository.save(Lookup.builder()
                        .name(lookup.get(0))
                        .type(lookup.get(2))
                        .value(lookup.get(1))
                        .build()
            );
        }


        log.info("Success Run seeder lookup");


    }
}
