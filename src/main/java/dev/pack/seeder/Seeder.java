package dev.pack.seeder;

import dev.pack.modules.alur_ppdb.AlurPpdbRepository;
import dev.pack.modules.alur_ppdb.AlurPpdb;
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

import static dev.pack.modules.enums.Role.ADMIN;
import static dev.pack.modules.enums.Role.USER;

//@Configuration
@Transactional
@RequiredArgsConstructor
public class Seeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final AlurPpdbRepository alurPpdbRepository;

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
//            seedUserData();
            seedAdminData();
//            seedRegisterPath();
//            seedPpdbFlows();

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
                .build();

        alurPpdbRepository.save(alurPpdb);

        log.info("Seed data :: {} -> {}","PPDBFLOW", alurPpdbRepository.count());
    }

}
