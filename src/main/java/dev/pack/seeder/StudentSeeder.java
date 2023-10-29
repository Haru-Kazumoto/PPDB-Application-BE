package dev.pack.seeder;

import com.github.javafaker.Faker;
import dev.pack.modules.auth.AuthenticationService;
import dev.pack.modules.auth.RegisterRequest;
import dev.pack.modules.enums.Role;
import dev.pack.modules.student.StudentDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class StudentSeeder implements ApplicationRunner {

    private final AuthenticationService authenticationService;
    private static final Logger log = LoggerFactory.getLogger(RoleSeeder.class);


    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(args.getOptionValues("seeder") != null && Objects.equals(args.getOptionValues("seeder").get(0), "student")){
            this.seedStudents();
            log.info("Success run student seeder");
        }else{
            log.info("Student seeder skipped");
        }
    }

    private void seedStudents() {
        Faker faker = new Faker();

        for (int i = 0; i < 1000; i++) {
            String name = faker.name().fullName();
            String phonenumber = faker.phoneNumber().phoneNumber().replace("-","").replace("(","").replace(")","");
            String address = faker.address().fullAddress();

            this.authenticationService.registerStudent(
                    RegisterRequest.User.builder()
                            .username(phonenumber)
                            .password("123456")
                            .role(Role.USER)
                            .studentData(StudentDto.OnRegister.builder()
                                    .address(address)
                                    .school_origin("YATINDO")
                                    .name(name)
                                    .build())
                            .build()
            );
        }
    }
}