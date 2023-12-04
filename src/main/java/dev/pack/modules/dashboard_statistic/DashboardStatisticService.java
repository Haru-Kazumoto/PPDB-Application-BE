package dev.pack.modules.dashboard_statistic;

import dev.pack.modules.enums.FormPurchaseType;
import dev.pack.modules.registration_batch.RegistrationBatchRepository;
import dev.pack.modules.registration_paths.RegistrationPaths;
import dev.pack.modules.registration_paths.RegistrationPathsRepository;
import dev.pack.modules.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardStatisticService {

    private final StudentRepository studentRepository;
    private final RegistrationBatchRepository registrationBatchRepository;
    private final RegistrationPathsRepository registrationPathsRepository;

    public DashboardStatistic getDashboardStatisticData(){
        Integer studentByStatusRegistered = this.studentRepository.countStudentByStatus("REGISTERED");
        Integer studentByStatusWaitingPayment = this.studentRepository.countStudentByStatus("WAITING_PAYMENT");
        Integer studentByStatusConfirmed = this.studentRepository.countStudentByStatus("PAYMENT_CONFIRMED");

        DashboardStatistic.DashboardStatisticBuilder dashboardStatisticBuilder = DashboardStatistic.builder()
                .registeredStudentCount(studentByStatusRegistered)
                .waitingPaymentStudentCount(studentByStatusWaitingPayment)
                .confirmedPaymentStudentCount(studentByStatusConfirmed)
                .purchasePathStatistics(buildPurchasePathStatistics());

        return dashboardStatisticBuilder.build();
    }

    private List<PurchasePathStatistic> buildPurchasePathStatistics(){
        List<PurchasePathStatistic> purchasePathStatistics = new ArrayList<>();

//        List<RegistrationPaths> countStudentByRegistrationPaths = this.studentRepository.countStudentsByRegistrationPathAndType(FormPurchaseType.PEMBELIAN);
        List<RegistrationPaths> registrationPaths = this.registrationPathsRepository.findAll();

        for(RegistrationPaths path : registrationPaths){
            PurchasePathStatistic.PurchasePathStatisticBuilder purchasePathStatisticBuilder = PurchasePathStatistic.builder()
                    .id(path.getId())
                    .name(path.getName())
                    .countStudent(path.getCountStudent());

            purchasePathStatistics.add(purchasePathStatisticBuilder.build());
        }

        return purchasePathStatistics;
    }
}
