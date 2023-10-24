package dev.pack.payloads;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.pack.utils.CustomDateSerializer;
import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private Integer statusCode;

    @JsonSerialize(using = CustomDateSerializer.class)
    private Date timestamps;

    private String statusResponse;

    private String message;

}
