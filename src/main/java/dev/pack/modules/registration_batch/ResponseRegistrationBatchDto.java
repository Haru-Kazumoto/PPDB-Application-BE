package dev.pack.modules.registration_batch;

import dev.pack.modules.enums.Banks;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseRegistrationBatchDto {

    private Integer id;
    private String name;
    private Integer index;
    private Integer max_quota;
    private String batchCode;
    private Date start_date;
    private Date end_date;
    private Banks bank_name;
    private String bank_user;
    private Double price;
    private String bank_account;
}
