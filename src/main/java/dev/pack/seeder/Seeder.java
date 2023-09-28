package dev.pack.seeder;

import dev.pack.modules.enums.FormPurchaseType;
import dev.pack.modules.ppdbFlow.PpdbFlow;
import dev.pack.modules.ppdbFlow.PpdbFlowRepository;
import dev.pack.modules.registerPath.RegisterPath;
import dev.pack.modules.registerPath.RegisterPathRepository;
import dev.pack.modules.registerPath.publicInformation.PublicInformation;
import dev.pack.modules.registerPath.publicInformation.PublicInformationRepository;
import dev.pack.modules.registerPath.publicInformation.aditionalCost.AdditionalCost;
import dev.pack.modules.registerPath.publicInformation.aditionalCost.AdditionalCostRepository;
import dev.pack.modules.registerPath.publicInformation.aditionalCost.cost.Cost;
import dev.pack.modules.registerPath.publicInformation.aditionalCost.cost.CostRepository;
import dev.pack.modules.registerPath.publicInformation.information.Information;
import dev.pack.modules.registerPath.publicInformation.information.InformationRepository;
import dev.pack.modules.user.User;
import dev.pack.modules.user.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.transaction.TransactionalException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.UnexpectedRollbackException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static dev.pack.modules.enums.Role.ADMIN;
import static dev.pack.modules.enums.Role.USER;

@Configuration
@Transactional
@RequiredArgsConstructor
public class Seeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RegisterPathRepository registerPathRepository;
    private final PublicInformationRepository publicInformationRepository;
    private final InformationRepository informationRepository;
    private final AdditionalCostRepository additionalCostRepository;
    private final CostRepository costRepository;
    private final PpdbFlowRepository ppdbFlowRepository;

    private final PasswordEncoder passwordEncoder;

    private static final Logger log = LoggerFactory.getLogger(Seeder.class);

    @Override
    public void run(String... args) throws Exception {
        try{
            //Put repo count here.
            long userData = userRepository.count();

            log.info("Seeding data . . . ");

            /**
             * Put your seed function here
             */
            seedUserData();
            seedAdminData();
            seedRegisterPath();
            seedPpdbFlows();

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

    public void seedPpdbFlows(){
        PpdbFlow ppdbFlow = PpdbFlow.builder()
                .title("PPDBFLOW TITLE")
                .content("PPDB FLOW CONTENT")
                .userId(userRepository.getReferenceById(2))
                .build();

        ppdbFlowRepository.save(ppdbFlow);

        log.info("Seed data :: {} -> {}","PPDBFLOW",ppdbFlowRepository.count());
    }

    public void seedRegisterPath(){
        long countData = registerPathRepository.count();
        if(countData == 0) {
            List<Cost> costs = new ArrayList<>();
            List<AdditionalCost> additionalCosts = new ArrayList<>();
            PublicInformation publicInformation = null;

            RegisterPath registerPath = RegisterPath.builder()
                    .tipePembelian(FormPurchaseType.PEMBELIAN_FORMULIR)
                    .namaJalurPendaftaran("PEMBELIAN FORMULIR")
                    .waktuDibuka(new Date())
                    .waktuDitutup(new Date())
                    .biayaPendaftaran(200.00)
                    .publicInformation(publicInformation)
                    .userId(userRepository.getReferenceById(2))
                    .build();

            Information information = Information.builder()
                    .namaKeterangan("Pembelian Formulir")
                    .deskripsiKeterangan("Deskripsi pembelian Formulir")
                    .publicInformationId(publicInformationRepository.getReferenceById(1))
                    .build();

            publicInformation = PublicInformation.builder()
                    .keterangan(information)
                    .biayaTambahan(additionalCosts)
                    .registerPathId(registerPathRepository.getReferenceById(1))
                    .build();

            for (AdditionalCost additionalCost_1 : additionalCosts) {
                additionalCost_1.setJudulBiaya("Biaya tambahan gelombang 1");
                additionalCost_1.setPublicInformationId(publicInformationRepository.getReferenceById(1));
                additionalCost_1.setCosts(costs);

                additionalCostRepository.save(additionalCost_1);
            }

            for (Cost cost : costs) {
                cost.setNamaBiayaTambahan("Biaya tambahan gedung");
                cost.setJumlahBiayaTambahan(100.00);
                cost.setAdditionalCostId(additionalCostRepository.getReferenceById(1));

                costRepository.save(cost);
            }


            registerPathRepository.save(registerPath);
            publicInformationRepository.save(publicInformation);
            informationRepository.save(information);
            additionalCostRepository.saveAll(additionalCosts);
            costRepository.saveAll(costs);
        }

        log.info("Seed data :: {} -> {}", "REGISTER_PATH",registerPathRepository.count());
        log.info("Seed data :: {} -> {}", "ADDITIONAL_COST", additionalCostRepository.count());
        log.info("Seed data :: {} -> {}", "PUBLIC_INFORMATION", publicInformationRepository.count());
        log.info("Seed data :: {} -> {}", "INFORMATION", informationRepository.count());
        log.info("Seed data :: {} -> {}", "COST", costRepository.count());
    }
}
