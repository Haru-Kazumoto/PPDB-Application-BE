package dev.pack.modules.dashboard_statistic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DashboardStatistic {

    private Integer registeredStudentCount;
    private Integer waitingPaymentStudentCount;
    private Integer confirmedPaymentStudentCount;
    private List<PurchasePathStatistic> purchasePathStatistics;
    private List<ReturnPathStatistic> returnPathStatistics;
    private List<AllBatchStatistic> allBatchStatistics;

}