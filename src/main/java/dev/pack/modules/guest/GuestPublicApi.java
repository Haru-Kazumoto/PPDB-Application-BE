package dev.pack.modules.guest;

import dev.pack.modules.alur_ppdb.AlurPpdbService;
import dev.pack.modules.registration_paths.RegistrationPathsService;
import dev.pack.payloads.HttpResponse;
import dev.pack.payloads.PayloadsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/public")
public class GuestPublicApi {

    private final RegistrationPathsService registrationPathsService;
    private final AlurPpdbService alurPpdbService;
    private final HttpResponse http;

    @GetMapping(path = "/index-registration-path")
    public ResponseEntity<?> indexWithRecursion(){
        return this.http.response(
                HttpStatus.OK.value(),
                new Date(),
                this.registrationPathsService.indexAllWithRecursion()
        );
    }

    @GetMapping(path ="/index-alur-ppdb")
    public ResponseEntity<?> index(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new PayloadsResponse(
                        HttpStatus.OK.value(),
                        new Date(),
                        this.alurPpdbService.getAll()
                )
        );
    }

}
