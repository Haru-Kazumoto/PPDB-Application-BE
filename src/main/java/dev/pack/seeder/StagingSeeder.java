package dev.pack.seeder;

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
        List<String> stagings = new ArrayList<>();

        stagings.add("Pilih Gelombang PPDB");
        stagings.add("Transaksi Pembelian");
        stagings.add("Pilih Jurusan");
        stagings.add("Cetak Kartu Peserta");

        stagings.add("Pilih Jalur PPDB");
        stagings.add("Transaksi Pengembalian");
        stagings.add("Isi Biodata");
        stagings.add("Pilih Jurusan");
        stagings.add("Isi data prestasi");
        stagings.add("Cetak Kartu Peserta");


        var index = 0;
        for (var staging : stagings ) {

            this.stagingRepository.save(
                    Staging.builder().name(staging).index(index).is_visible(true).build()
            );

            log.info("Success run RoleSeeder {}",stagings.get(index));

            index++;
        }
    }



}