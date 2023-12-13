package dev.pack.seeder;

import dev.pack.modules.role.RoleMenusRepository;
import dev.pack.modules.role.RoleRepository;
import dev.pack.modules.role.Roles;
import dev.pack.modules.role.RolesMenus;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class RoleSeeder implements ApplicationRunner {

    private final RoleRepository roleRepository;
    private final RoleMenusRepository roleMenusRepository;


    private static final Logger log = LoggerFactory.getLogger(RoleSeeder.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(args.getOptionValues("seeder") != null){
            List<String> seeder = Arrays.asList(args.getOptionValues("seeder").get(0).split(","));
            if(seeder.contains("role")) {
                seedRoles();
                log.info("Success run role seeder");
            }
        }else{
            log.info("role seeder skipped");
        }

    }

    private void seedRolesMenus() {
        List<String> rolesMenus = new ArrayList<>();

        rolesMenus.add("User");
        rolesMenus.add("Admin");
    }

    private void seedRoles(){
        List<String> roles = new ArrayList<>();
        List<String> userPath = new ArrayList<>();
        List<String> adminPath = new ArrayList<>();

        roles.add("User");
        roles.add("Admin");

        userPath.add("/ppdb/main/home");
        userPath.add("/ppdb/main/pembelian");
        userPath.add("/ppdb/main/pengembalian");
        userPath.add("/ppdb/main/tes-ujian");

        adminPath.add("/ppdb/main/dashboard");
        adminPath.add("/ppdb/main/alur");
        adminPath.add("/ppdb/main/jalur-pendaftaran");
        adminPath.add("/ppdb/main/pendaftar-ppdb");

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

            if(index == 1) {
                for(var s : adminPath) {
                    this.roleMenusRepository.save(RolesMenus.builder().role_id(result).path(s).build());
                }
            }


            log.info("Success run RoleSeeder {}",roles.get(index));


            index++;
        }
    }



}