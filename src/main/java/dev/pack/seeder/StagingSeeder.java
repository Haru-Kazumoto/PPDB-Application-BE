package dev.pack.seeder;

import dev.pack.modules.enums.FormPurchaseType;
import dev.pack.modules.enums.Grade;
import dev.pack.modules.staging.Staging;
import dev.pack.modules.staging.StagingRepository;
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
@RequiredArgsConstructor
public class StagingSeeder implements ApplicationRunner {

    private final StagingRepository stagingRepository;

    private static final Logger log = LoggerFactory.getLogger(RoleSeeder.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(args.getOptionValues("seeder") != null){
            List<String> seeder = Arrays.asList(args.getOptionValues("seeder").get(0).split(","));
            if(seeder.contains("staging")) {
                seedStagings();
                log.info("Success run staging seeder");
            }
        }else{
            log.info("staging seeder skipped");
        }

    }
    private void seedStagings(){
        List<List<Object>> stagings = new ArrayList<>();

        stagings.add(List.of("Pilih Gelombang PPDB",1, FormPurchaseType.PEMBELIAN, Grade.SMK));
        stagings.add(List.of("Pembelian Formulir Pendaftaran",2, FormPurchaseType.PEMBELIAN,Grade.SMK));
        stagings.add(List.of("Pilih Jurusan",3, FormPurchaseType.PEMBELIAN,Grade.SMK));
        stagings.add(List.of("Cetak Formulir",4, FormPurchaseType.PEMBELIAN,Grade.SMK));

        stagings.add(List.of("Pilih Gelombang PPDB",1, FormPurchaseType.PEMBELIAN, Grade.SMP));
        stagings.add(List.of("Pembelian Formulir Pendaftaran",2, FormPurchaseType.PEMBELIAN,Grade.SMP));
        stagings.add(List.of("Cetak Formulir",3, FormPurchaseType.PEMBELIAN,Grade.SMP));

        stagings.add(List.of("Pilih Jalur PPDB",1, FormPurchaseType.PENGEMBALIAN,Grade.SMK));
        stagings.add(List.of("Transaksi Pengembalian",2, FormPurchaseType.PENGEMBALIAN,Grade.SMK));
        stagings.add(List.of("Isi Biodata",3, FormPurchaseType.PENGEMBALIAN,Grade.SMK));
        stagings.add(List.of("Pilih Jurusan",4,FormPurchaseType.PENGEMBALIAN,Grade.SMK));
        stagings.add(List.of("Cetak Formulir",5, FormPurchaseType.PENGEMBALIAN,Grade.SMK));

        stagings.add(List.of("Pilih Jalur PPDB",1, FormPurchaseType.PENGEMBALIAN,Grade.SMP));
        stagings.add(List.of("Transaksi Pengembalian",2, FormPurchaseType.PENGEMBALIAN,Grade.SMP));
        stagings.add(List.of("Isi Biodata",3, FormPurchaseType.PENGEMBALIAN,Grade.SMP));
        stagings.add(List.of("Cetak Formulir",4, FormPurchaseType.PENGEMBALIAN,Grade.SMP));


        var index = 0;
        for (var staging : stagings ) {

            this.stagingRepository.save(
                    Staging.builder()
                            .name((String)staging.get(0))
                            .index((Integer) staging.get(1))
                            .type((FormPurchaseType) staging.get(2))
                            .is_visible(true)
                            .grade((Grade) staging.get(3))
                            .build()
            );


            log.info("Success run RoleSeeder {}",stagings.get(index));


            index++;
        }
    }



}