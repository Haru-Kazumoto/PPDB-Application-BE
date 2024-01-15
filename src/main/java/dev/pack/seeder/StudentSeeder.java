package dev.pack.seeder;

import com.github.javafaker.Faker;

import dev.pack.exception.DataNotFoundException;
import dev.pack.modules.auth.AuthenticationService;
import dev.pack.modules.auth.RegisterRequest;
import dev.pack.modules.enums.Grade;
import dev.pack.modules.enums.Role;
import dev.pack.modules.role.RoleRepository;
import dev.pack.modules.role.Roles;
import dev.pack.modules.student.Student;
import dev.pack.modules.student.StudentDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class StudentSeeder implements ApplicationRunner {

    private final AuthenticationService authenticationService;
    private final RoleRepository roleRepository;
    private static final Logger log = LoggerFactory.getLogger(RoleSeeder.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(args.getOptionValues("seeder") != null){
            List<String> seeder = Arrays.asList(args.getOptionValues("seeder").get(0).split(","));
            if(seeder.contains("student")) {
                this.seedStudents();
                log.info("Success run student seeder");
            }
        }else{
            log.info("Student seeder skipped");
        }
    }

    private void seedStudents() {
        Faker faker = new Faker();

        Roles role = this.roleRepository.findRolesByName("User").orElseThrow(() -> null);

        List<RegisterRequest.User> students = List.of(
            RegisterRequest.User.builder()
                .username("12345678916")
                .password("1234")
                .role(Role.USER)
                .studentData(StudentDto.OnRegister.builder()
                    .address("bekasi")
                    .school_origin("ytd")
                    .grade(Grade.SMK)
                    .name("Yagami")
                    .build())
                .build(),
            RegisterRequest.User.builder()
                .username("12345678917")
                .password("1234")
                .role(Role.USER)
                .studentData(StudentDto.OnRegister.builder()
                    .address("bekasi")
                    .school_origin("ytd")
                    .grade(Grade.SMK)
                    .name("Inumaki")
                    .build())
                .build(),
            RegisterRequest.User.builder()
                .username("12345678918")
                .password("1234")
                .role(Role.USER)
                .studentData(StudentDto.OnRegister.builder()
                    .address("bekasi")
                    .school_origin("ytd")
                    .grade(Grade.SMK)
                    .name("Suzuki")
                    .build())
                .build(),
            RegisterRequest.User.builder()
                .username("12345678919")
                .password("1234")
                .role(Role.USER)
                .studentData(StudentDto.OnRegister.builder()
                    .address("bekasi")
                    .school_origin("ytd")
                    .grade(Grade.SMK)
                    .name("Sakura")
                    .build())
                .build()
        );

        for(RegisterRequest.User request : students){
            this.authenticationService.registerStudent(request);
        }
        // for (int i = 0; i < students.size(); i++) {
            
            // String name = faker.name().fullName();
            // String phonenumber = faker.phoneNumber()
            //         .phoneNumber()
            //         .replace("-","")
            //         .replace("(","")
            //         .replace(")","");

            // String address = faker.address().fullAddress();

            // this.authenticationService.registerStudent(
            //         RegisterRequest.User.builder()
            //                 .username(phonenumber)
            //                 .password("1234")
            //                 .role(Role.USER)
            //                 .studentData(StudentDto.OnRegister.builder()
            //                         .address(address)
            //                         .school_origin("YATINDO")
            //                         .grade(Grade.SMK)
            //                         .name(name)
            //                         .build())
            //                 .build()
            // );
        // }
    }
}