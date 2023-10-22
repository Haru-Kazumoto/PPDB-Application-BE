package dev.pack.seeder;

import dev.pack.modules.data.role.RoleRepository;
import dev.pack.modules.data.role.Roles;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

import java.util.ArrayList;
import java.util.List;

//@Configuration
@RequiredArgsConstructor
public class RoleSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;


    private static final Logger log = LoggerFactory.getLogger(RoleSeeder.class);

    @Override
    public void run(String... args) throws Exception {
        seedRoles();
    }

    private void seedRolesMenus() {
        List<String> rolesMenus = new ArrayList<>();

        rolesMenus.add("User");
        rolesMenus.add("Admin");
    }

    private void seedRoles(){
        List<String> roles = new ArrayList<>();

        roles.add("User");
        roles.add("Admin");

        var index = 0;
        for (var role : roles ) {

            var r = Roles.builder()
                    .role_name(role)
                    .build();

            this.roleRepository.save(r);
            log.info("Success run RoleSeeder {}",roles.get(index));


            index++;
        }
    }



}