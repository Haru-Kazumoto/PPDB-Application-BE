package dev.pack.seeder;

import dev.pack.modules.lookup.Lookup;
import dev.pack.modules.lookup.LookupRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Transactional
@RequiredArgsConstructor
public class LookupSeeder implements CommandLineRunner {

    private final LookupRepository lookupRepository;


    private static final Logger log = LoggerFactory.getLogger(RoleSeeder.class);

    @Override
    public void run(String... args) throws Exception {
        seedMajor();
    }

    private void seedMajor(){
        List<List<String>> lookups = new ArrayList<>();

        lookups.add(List.of("Teknik Jaringan dan Komputer Telekomunikasi","TJKT","MAJOR"));
        lookups.add(List.of("Teknik Kendaraan Ringan","TKR","MAJOR"));
        lookups.add(List.of("Teknik Audio dan Video","TAV","MAJOR"));
        lookups.add(List.of("Akuntansi Keuangan Lembaga","AKL","MAJOR"));

        for(var lookup : lookups) {
            this.lookupRepository.save(Lookup.builder()
                        .name(lookup.get(0))
                        .value(lookup.get(1))
                        .type(lookup.get(2))
                        .build()
            );
        }
        log.info("Success Run seeder lookup");
    }
}
