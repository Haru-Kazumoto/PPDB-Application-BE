package dev.pack.modules.alur_ppdb;

import dev.pack.modules.enums.Grade;
import dev.pack.payloads.HttpResponse;
import dev.pack.payloads.PayloadsResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v${application.version}/admin/alur-ppdb")
public class AlurPpdbController {

    private final AlurPpdbService alurPpdbService;
    private final ModelMapper model;
    private final HttpResponse http;

    @PostMapping(path = "/post")
    public ResponseEntity<?> store(@RequestBody @Valid AlurPpdbDto.Create bodyDto){
        AlurPpdb data = model.map(bodyDto, AlurPpdb.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new PayloadsResponse(
                        HttpStatus.CREATED.value(),
                        new Date(),
                        this.alurPpdbService.createFlow(data)
                )
        );
    }

    @GetMapping(path ="/index")
    public ResponseEntity<?> index(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new PayloadsResponse(
                        HttpStatus.OK.value(),
                        new Date(),
                        this.alurPpdbService.getAll()
                )
        );
    }

    @PutMapping(path = "/update")
    public ResponseEntity<?> update(
            @RequestParam(name = "dataId", defaultValue = "") int dataId,
            @RequestBody @Valid AlurPpdbDto.Update dto
    ){
        AlurPpdb newData = model.map(dto, AlurPpdb.class);

        return ResponseEntity.status(HttpStatus.OK).body(
                new PayloadsResponse(
                        HttpStatus.OK.value(),
                        new Date(),
                        this.alurPpdbService.updateFlow(dataId, newData)
                )
        );
    }

    @DeleteMapping(path = "/delete")
    public void delete(@RequestParam(name = "id", defaultValue = "")int id){
        this.alurPpdbService.hardDeleteById(id);
    }
}