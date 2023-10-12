package dev.pack.modules.biaya;

import dev.pack.payloads.HttpResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v${application.version}")
public class BiayaController {

    private final BiayaService biayaService;
    private final ModelMapper model;

    private HttpResponse http;

    @PostMapping(path = "/post")
    public ResponseEntity<?> store(@RequestBody @Valid BiayaDto.Create dto){
        Biaya entity = this.model.map(dto, Biaya.class);

        return http.response(HttpStatus.CREATED.value(), new Date(), this.biayaService.store(entity));
    }

}
