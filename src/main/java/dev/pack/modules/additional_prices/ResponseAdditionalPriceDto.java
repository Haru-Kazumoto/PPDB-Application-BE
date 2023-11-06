package dev.pack.modules.additional_prices;

import dev.pack.modules.prices.ResponsePriceDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAdditionalPriceDto {

    private Integer id;
    private String namePrice;

    private List<ResponsePriceDetailsDto> priceDetails;

}
