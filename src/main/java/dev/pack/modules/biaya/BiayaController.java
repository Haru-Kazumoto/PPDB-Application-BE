package dev.pack.modules.biaya;

import dev.pack.payloads.HttpResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v${application.version}/admin/biaya")
public class BiayaController {

    private final BiayaService biayaService;
    private final ModelMapper model;
    private final HttpResponse http;

    @PostMapping(path = "/post")
    public ResponseEntity<?> store(@RequestBody @Valid BiayaDto.Create dto){
        Biaya entity = this.model.map(dto, Biaya.class);

        return http.response(CREATED.value(), new Date(), this.biayaService.store(entity));
    }

    @GetMapping(path = "/index")
    public ResponseEntity<?> index(@RequestParam(name = "biayaTambahanId", defaultValue = "") int biayaTambahanId){
        return http.response(OK.value(), new Date(), this.biayaService.index(biayaTambahanId));
    }

    @PatchMapping(path = "/update")
    public ResponseEntity<?> update(
            @RequestParam(name = "dataId", defaultValue = "") int dataId,
            @RequestBody @Valid BiayaDto.Update dto
    ){
        Biaya entity = this.model.map(dto, Biaya.class);
        return http.response(OK.value(),new Date(),this.biayaService.update(dataId, entity));
    }

}
