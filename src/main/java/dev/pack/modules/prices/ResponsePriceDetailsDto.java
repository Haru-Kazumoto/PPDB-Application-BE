package dev.pack.modules.prices;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePriceDetailsDto {

    private Integer id;
    private String subTitle;
    private Double price;

}
