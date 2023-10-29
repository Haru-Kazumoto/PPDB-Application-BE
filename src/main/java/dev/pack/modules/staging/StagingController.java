package dev.pack.modules.staging;

import dev.pack.modules.enums.FormPurchaseType;
import dev.pack.payloads.HttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v${application.version}/staging")
public class StagingController {
    private final StagingService stagingService;
    private final HttpResponse http;


    @GetMapping("/get-student-offset")
    ResponseEntity<?> getStudentOffset(@RequestParam(name = "type",required = true) FormPurchaseType type) {
        return this.http.response(
                HttpStatus.OK.value(),
                new Date(),
                this.stagingService.getAllByStudent( type)
        );
    }
}