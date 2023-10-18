package dev.pack.modules.admin.biaya_tambahan;

import dev.pack.payloads.HttpResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v${application.version}/admin/biaya-tambahan")
public class BiayaTambahanController {

    private final ModelMapper modelMapper;
    private final BiayaTambahanService biayaTambahanService;

    private final HttpResponse http;

    @PostMapping(path = "/post")
    public ResponseEntity<?> store(@RequestBody @Valid BiayaTambahanDto.Create dto){
        BiayaTambahan entity = modelMapper.map(dto, BiayaTambahan.class);
        return http.response(HttpStatus.CREATED.value(), new Date(), this.biayaTambahanService.store(entity));
    }

    @GetMapping(path = "/index")
    public ResponseEntity<?> index(@RequestParam(name = "dataId", defaultValue = "") int dataId){
        return http.response(HttpStatus.OK.value(), new Date(), this.biayaTambahanService.index(dataId));
    }

    @PatchMapping(path = "/update")
    public ResponseEntity<?> update(
            @RequestParam(name = "dataId", defaultValue = "") int dataId,
            @RequestBody @Valid BiayaTambahanDto.Update dto
    ){
        BiayaTambahan entity = modelMapper.map(dto, BiayaTambahan.class);
        return http.response(HttpStatus.OK.value(), new Date(), this.biayaTambahanService.update(dataId, entity));
    }
}