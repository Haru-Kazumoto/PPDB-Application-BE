package dev.pack.modules.admin.kegiatan;

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
@RequestMapping(path = "/api/v${application.version}/admin/kegiatan")
public class KegiatanController {

    private final KegiatanService kegiatanService;
    private final HttpResponse http;
    private final ModelMapper model;

    @PostMapping(path = "/post")
    public ResponseEntity<?> store(@RequestBody @Valid KegiatanDto.Create dto){
        Kegiatan entity = this.model.map(dto, Kegiatan.class);
        return this.http.response(CREATED.value(), new Date(), this.kegiatanService.store(entity));
    }

}
