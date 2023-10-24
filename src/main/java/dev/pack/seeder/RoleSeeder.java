package dev.pack.seeder;

import dev.pack.modules.role.RoleMenusRepository;
import dev.pack.modules.role.RoleRepository;
import dev.pack.modules.role.Roles;
import dev.pack.modules.role.RolesMenus;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

//@Configuration
@RequiredArgsConstructor
public class RoleSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final RoleMenusRepository roleMenusRepository;


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
        List<String> userPath = new ArrayList<>();

        roles.add("User");
        roles.add("Admin");

        userPath.add("/ppdb/main/home");
        userPath.add("/ppdb/main/pembelian");
        userPath.add("/ppdb/main/pengembalian");

        var index = 0;
        for (var role : roles ) {

            var r = Roles.builder()
                    .role_name(role)
                    .build();

            Roles result = this.roleRepository.save(r);

            if(index == 0) {
                for (var p : userPath) {
                    this.roleMenusRepository.save(RolesMenus.builder().role_id(result).path(p).build());
                }
            }


            log.info("Success run RoleSeeder {}",roles.get(index));


            index++;
        }
    }



}