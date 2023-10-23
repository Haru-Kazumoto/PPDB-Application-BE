package dev.pack.payloads;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.pack.utils.CustomDateSerializer;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayloadsResponse {

    private Integer statusCode;

    @JsonSerialize(using = CustomDateSerializer.class)
    @Temporal(TemporalType.DATE)
    private Date timestamps = new Date();

    @JsonSerialize
    @JsonIgnoreProperties(
            {
                    "hibernateLazyInitializer",
                    "handler"
            })
    private Object data;

}
