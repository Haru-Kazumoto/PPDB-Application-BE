package dev.pack.seeder;

import dev.pack.modules.enums.FormPurchaseType;
import dev.pack.modules.staging.Staging;
import dev.pack.modules.staging.StagingRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

//@Configuration
@RequiredArgsConstructor
public class StagingSeeder implements CommandLineRunner {

    private final StagingRepository stagingRepository;


    private static final Logger log = LoggerFactory.getLogger(RoleSeeder.class);

    @Override
    public void run(String... args) throws Exception {
        seedStagings();
    }
    private void seedStagings(){
        List<List<Object>> stagings = new ArrayList<>();

        stagings.add(List.of("Pilih Gelombang PPDB",1, FormPurchaseType.PEMBELIAN));
        stagings.add(List.of("Transaksi Pembelian",2, FormPurchaseType.PEMBELIAN));
        stagings.add(List.of("Pilih Jurusan",3, FormPurchaseType.PEMBELIAN));
        stagings.add(List.of("Cetak Kartu Peserta",4, FormPurchaseType.PEMBELIAN));

        stagings.add(List.of("Pilih Jalur PPDB",1, FormPurchaseType.PENGEMBALIAN));
        stagings.add(List.of("Transaksi Pengembalian",2, FormPurchaseType.PENGEMBALIAN));
        stagings.add(List.of("Isi Biodata",3, FormPurchaseType.PENGEMBALIAN));
        stagings.add(List.of("Pilih Jurusan",4, FormPurchaseType.PENGEMBALIAN));
        stagings.add(List.of("Isi data prestasi",5, FormPurchaseType.PENGEMBALIAN));
        stagings.add(List.of("Cetak Kartu Peserta",6, FormPurchaseType.PENGEMBALIAN));


        var index = 0;
        for (var staging : stagings ) {

            this.stagingRepository.save(
                    Staging.builder()
                            .name((String)staging.get(0))
                            .index((Integer) staging.get(1))
                            .type((FormPurchaseType) staging.get(2))
                            .is_visible(true)
                            .build()
            );


            log.info("Success run StagingSeeder {}",stagings.get(index));


            index++;
        }
    }



}