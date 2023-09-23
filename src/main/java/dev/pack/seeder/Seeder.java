package dev.pack.seeder;

import dev.pack.modules.user.User;
import dev.pack.modules.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import static dev.pack.modules.authorization.Role.ADMIN;
import static dev.pack.modules.authorization.Role.USER;

@Configuration
@Transactional
@RequiredArgsConstructor
public class Seeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private static final Logger log = LoggerFactory.getLogger(Seeder.class);

    @Override
    public void run(String... args) throws Exception {
        try{
            log.info("Seeding data . . . ");

            /**
             * Put your seed function here
             */
            seedUserData();
            seedAdminData();

        } catch (Exception ex){
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

            log.info("Seed data :: {} -> {}",user.getRole(),userRepository.countByRole(USER));
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

            log.info("Seed data :: {} -> {}",admin.getRole(),userRepository.countByRole(ADMIN));
        }
    }
}
