package dev.pack.seeder;

import dev.pack.modules.action.Action;
import dev.pack.modules.action.ActionRepository;
import dev.pack.modules.newdata.role.RoleRepository;
import dev.pack.modules.newdata.role.Roles;
import dev.pack.modules.newdata.role.RolesMenus;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

import static dev.pack.modules.enums.Role.USER;

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

            this.roleRepository.save(
                    Roles.builder()
                    .role_name(role)
                    .build());
            log.info("Success run RoleSeeder {}",roles.get(index));


            index++;
        }
    }



}