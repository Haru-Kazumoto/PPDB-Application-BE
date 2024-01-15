package dev.pack.modules.dashboard_statistic;

import dev.pack.modules.registration_batch.RegistrationBatchRepository;
import dev.pack.modules.registration_paths.RegistrationPathsRepository;
import dev.pack.modules.student.StudentRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardStatisticService {

    private final RegistrationPathsRepository registrationPathsRepository;

    public StatisticCount getStatisticsCount(){
        return this.registrationPathsRepository.countStatistics();
    }

    public List<StatisticPaths> countPathStatistics(){
        return this.registrationPathsRepository.countPathStatistics();
    }
}
