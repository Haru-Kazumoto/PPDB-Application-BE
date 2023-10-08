package dev.pack.modules.jalur_pendaftaran;

import dev.pack.payloads.HttpResponse;
import dev.pack.payloads.PayloadsResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v${application.version}/admin/jalur-pendaftaran")
public class JalurPendaftaranController {

    private final JalurPendaftaranService jalurPendaftaranService;
    private final ModelMapper modelMapper;

    private final HttpResponse http;

    @PostMapping(path = "/post")
    public ResponseEntity<?> store(@RequestBody @Valid JalurPendaftaranDto.Create dto){
        JalurPendaftaran entity = modelMapper.map(dto, JalurPendaftaran.class);
        return http.response(CREATED.value(), new Date(), this.jalurPendaftaranService.store(entity));
    }

    @GetMapping(path = "/index")
    public ResponseEntity<?> index(@RequestParam(name = "userId", defaultValue = "") int userId){
        return http.response(OK.value(), new Date(), this.jalurPendaftaranService.index(userId));
    }

    @PatchMapping(path = "/update")
    public ResponseEntity<?> update(
            @RequestParam(name = "dataId", defaultValue = "") int dataId,
            @RequestBody @Valid JalurPendaftaranDto.Update dto
    ){
        JalurPendaftaran entity = this.modelMapper.map(dto, JalurPendaftaran.class);
        return http.response(OK.value(), new Date(), this.jalurPendaftaranService.update(dataId, entity));
    }

}
