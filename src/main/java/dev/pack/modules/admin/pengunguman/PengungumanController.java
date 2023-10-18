package dev.pack.modules.admin.pengunguman;

import dev.pack.payloads.HttpResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v${application.version}/admin/pengunguman")
public class PengungumanController {

    private final PengungumanService pengungumanService;
    private final ModelMapper model;
    private final HttpResponse http;

    @PostMapping(path = "/post")
    public ResponseEntity<?> store(@RequestBody @Valid PengungumanDto.Create dto) {
        Pengunguman entity = this.model.map(dto, Pengunguman.class);
        return this.http.response(CREATED.value(), new Date(), this.pengungumanService.store(entity));
    }

}
