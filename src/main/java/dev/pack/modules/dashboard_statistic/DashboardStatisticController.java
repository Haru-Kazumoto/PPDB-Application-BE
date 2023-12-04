package dev.pack.modules.dashboard_statistic;

import dev.pack.payloads.HttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(path = "/api/v${application.version}/admin/dashboard-stat")
@RequiredArgsConstructor
public class DashboardStatisticController {

    private final DashboardStatisticService dashboardStatisticService;
    private final HttpResponse http;

    @GetMapping("/get-stat")
    public ResponseEntity<?> getDashboardStatistic(){
        return http.response(
                HttpStatus.OK.value(),
                new Date(),
                this.dashboardStatisticService.getDashboardStatisticData()
        );
    }

}
