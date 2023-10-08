package dev.pack.payloads;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteResponse {

//    private Date deleteAt;
    private Integer httpStatus;
    private String message;

}
