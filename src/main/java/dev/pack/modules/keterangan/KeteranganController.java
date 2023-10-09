package dev.pack.modules.keterangan;

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
@RequestMapping(path = "/api/v${application.version}/admin/keterangan")
public class KeteranganController {

    private final KeteranganService keteranganService;
    private final ModelMapper model;

    private final HttpResponse http;

    @PostMapping(path = "/post")
    public ResponseEntity<?> store(@RequestBody @Valid KeteranganDto.Create dto){
        Keterangan entity = model.map(dto, Keterangan.class);
        return http.response(CREATED.value(), new Date(), this.keteranganService.store(entity));
    }

    @GetMapping(path = "/index")
    public ResponseEntity<?> index(@RequestParam(name = "jalurId", defaultValue = "") int jalurId){
        return http.response(OK.value(), new Date(), this.keteranganService.index(jalurId));
    }

    @GetMapping(path = "/index-deleted")
    public ResponseEntity<?> indexDeleted(@RequestParam(name = "id", defaultValue = "")Integer id){
        return http.response(OK.value(), new Date(), this.keteranganService.indexDeleted(id));
    }

    @PatchMapping(path = "/update")
    public ResponseEntity<?> update(
            @RequestParam(name = "dataId", defaultValue = "") Integer dataId,
            @RequestBody @Valid KeteranganDto.Update dto
    ){
        Keterangan entity = model.map(dto, Keterangan.class);
        return http.response(OK.value(), new Date(), this.keteranganService.update(dataId, entity));
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<?> softDelete(@RequestParam(name = "dataId", defaultValue = "")Integer id){
        this.keteranganService.softDelete(id);
        return http.response(OK.value(), new Date(), "Data success to deleted!");
    }

}
