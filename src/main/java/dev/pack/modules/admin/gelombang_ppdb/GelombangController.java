package dev.pack.modules.admin.gelombang_ppdb;

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
@RequestMapping(path = "/api/v${application.version}/admin/gelombang")
public class GelombangController {

    private final ModelMapper model;
    private final GelombangService gelombangService;
    private final HttpResponse http;

    @PostMapping(path = "/post")
    public ResponseEntity<?> createGelombang(@RequestBody @Valid GelombangDto.Create dto){
        Gelombang entity = model.map(dto, Gelombang.class);
        return this.http.response(CREATED.value(), new Date(), this.gelombangService.store(entity));
    }

    @GetMapping(path = "/index")
    public ResponseEntity<?> getAllGelombang(@RequestParam(name = "jalurId", defaultValue = "") int jalurId){
        return this.http.response(OK.value(), new Date(), this.gelombangService.index(jalurId));
    }

    @PatchMapping(path = "/update")
    public ResponseEntity<?> updateGelombang(
            @RequestParam(name = "dataId", defaultValue = "") int dataId,
            @RequestBody @Valid GelombangDto.Update dto
    ){
        Gelombang entity = model.map(dto, Gelombang.class);
        return this.http.response(CREATED.value(), new Date(), this.gelombangService.update(dataId, entity));
    }

}
