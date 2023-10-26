package dev.pack.payloads;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class HttpResponse {
    public ResponseEntity<?> response(Integer statusCode, Date timestamps, Object body){
        return ResponseEntity.status(statusCode).body(
                new PayloadsResponse(
                        statusCode,
                        timestamps,
                        body
                )
        );
    }

}
