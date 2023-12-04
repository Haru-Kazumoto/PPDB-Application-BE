package dev.pack.modules.lookup;

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
@RequestMapping(path = "/api/v${application.version}/admin/lookup")
public class LookupController {
    private final HttpResponse http;
    private final LookupService lookupService;

    @GetMapping(path = "")
    public ResponseEntity<?> index(@RequestParam("type") String value) {
        return this.http.response(
                HttpStatus.OK.value(),
                new Date(),
                this.lookupService.findByType(value)
        );
    }

    @GetMapping(path = "/index-major")
    public ResponseEntity<?> indexMajor(){
        return this.http.response(
                HttpStatus.OK.value(),
                new Date(),
                this.lookupService.findAllMajor()
        );
    }

}
