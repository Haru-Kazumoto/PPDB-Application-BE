package dev.pack.modules.action;

import dev.pack.modules.enums.Role;
import dev.pack.payloads.PayloadsResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/public/action")
public class ActionController {

    private final ActionService actionService;
    private final ModelMapper model;

    @PostMapping("/post")
    public ResponseEntity<?> store(@RequestBody ActionDto dto){
        Action data = model.map(dto, Action.class);
        return ResponseEntity.status(CREATED).body(
                new PayloadsResponse(
                        CREATED.value(),
                        new Date(),
                        this.actionService.createAction(data)
                )
        );
    }

    @GetMapping("/index")
    public ResponseEntity<?> index(){
        return ResponseEntity.status(OK).body(
                new PayloadsResponse(
                        OK.value(),
                        new Date(),
                        this.actionService.getAllAction()
                )
        );
    }

    @GetMapping("/index-method")
    public ResponseEntity<?> indexByMethod(@RequestParam(name = "method", defaultValue = "GET") String method){
        return ResponseEntity.status(OK).body(
                new PayloadsResponse(
                        OK.value(),
                        new Date(),
                        this.actionService.getAllActionByMethod(method)
                )
        );
    }

    @GetMapping("/index-role")
    public ResponseEntity<?> indexByRole(@RequestParam(name = "role", defaultValue = "") Role role){
        return ResponseEntity.status(OK).body(
                new PayloadsResponse(
                        OK.value(),
                        new Date(),
                        this.actionService.getAllActionByRole(role)
                )
        );
    }

    @GetMapping("/index-status")
    public ResponseEntity<?> indexByBugStatus(@RequestParam(name = "bugStatus", defaultValue = "") Boolean isBug){
        return ResponseEntity.status(OK).body(
                new PayloadsResponse(
                        OK.value(),
                        new Date(),
                        this.actionService.getAllActionByBugStatus(isBug)
                )
        );
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(
            @RequestParam(name = "id") Integer id,
            @RequestBody ActionDto dto
    ){
        Action data = model.map(dto, Action.class);

        return ResponseEntity.status(OK).body(
                new PayloadsResponse(
                        OK.value(),
                        new Date(),
                        this.actionService.updateAction(id, data)
                )
        );
    }

    @DeleteMapping(path = "/delete")
    public void delete(@RequestParam(name = "id", defaultValue = "0") Integer id){
        this.actionService.deleteAction(id);
    }
}
