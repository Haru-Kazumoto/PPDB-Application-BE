package dev.pack.modules.dashboard_statistic;

public interface StatisticCount {
    Long getRegistered();
    Long getWaiting_Payment();
    Long getPayment_Confirmed();
}
