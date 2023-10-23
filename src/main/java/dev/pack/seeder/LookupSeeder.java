package dev.pack.seeder;

import dev.pack.modules.lookup.LookupRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@Transactional
@RequiredArgsConstructor
public class LookupSeeder implements CommandLineRunner {

    private final LookupRepository lookupRepository;

    @Override
    public void run(String... args) throws Exception {

    }

    private void seedMajor(){

    }
}
