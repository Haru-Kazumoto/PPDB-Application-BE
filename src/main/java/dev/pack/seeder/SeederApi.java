package dev.pack.seeder;

import dev.pack.config.ApplicationConfig;
import dev.pack.modules.action.Action;
import dev.pack.modules.action.ActionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

import static dev.pack.modules.enums.Role.*;

//@Configuration
@Transactional
@RequiredArgsConstructor
public class SeederApi implements ApplicationRunner {

    private final ActionRepository repository;
    private final ApplicationConfig config;

    private static final Logger log = LoggerFactory.getLogger(SeederApi.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(args.getOptionValues("seeder") != null){
            List<String> seeder = Arrays.asList(args.getOptionValues("seeder").get(0).split(","));
            if(seeder.contains("api")) {
                seedUserAction();
                log.info("Success run api seeder");
            }
        }else{
            log.info("api seeder skipped");
        }
    }

    private void seedUserAction(){
        Action action_1 = Action.builder()
                .path(String.format("/api/%s/user/index-all", this.config.APP_VERSION))
                .parameter("page[int],size[int]")
                .method("GET")
                .module("USER")
                .roleAccess(USER)
                .descriptionAction("Indexing all user with paging.")
                .isBug(false)
                .build();

        Action action_2 = Action.builder()
                .path(String.format("/api/%s/user/index-by-role", this.config.APP_VERSION))
                .parameter("page[int],size[int],role[Role]")
                .method("GET")
                .module("USER")
                .roleAccess(USER)
                .descriptionAction("Indexing all user with paging by role.")
                .isBug(false)
                .build();

        Action action_3 = Action.builder()
                .path(String.format("/api/%s/user/post", this.config.APP_VERSION))
                .method("POST")
                .module("USER")
                .roleAccess(USER)
                .descriptionAction("Create user")
                .isBug(false)
                .build();

        this.repository.save(action_1);
        this.repository.save(action_2);
        this.repository.save(action_3);
    }

    private void seedAlurPpdb(){

    }
}
