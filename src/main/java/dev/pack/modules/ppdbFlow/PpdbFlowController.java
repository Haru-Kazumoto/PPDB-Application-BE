package dev.pack.modules.ppdbFlow;

import dev.pack.payloads.PayloadsResponse;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v${application.version}/admin/ppdb-flow")
@PreAuthorize("hasRole('ADMIN')")
public class PpdbFlowController {

    private final PpdbFlowService ppdbFlowService;
    private final ModelMapper model;

    @PostMapping(path = "/post")
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<?> store(@RequestBody @Valid PpdbFlowDto bodyDto){
        PpdbFlow data = model.map(bodyDto, PpdbFlow.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new PayloadsResponse(
                        HttpStatus.CREATED.value(),
                        new Date(),
                        this.ppdbFlowService.createFlow(data)
                )
        );
    }

    @GetMapping(path ="/index")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<?> index(@RequestParam(name = "userId", defaultValue = "") int userId){
        return ResponseEntity.status(HttpStatus.OK).body(
                new PayloadsResponse(
                        HttpStatus.OK.value(),
                        new Date(),
                        this.ppdbFlowService.getAllFlowByUserId(userId)
                )
        );
    }
}