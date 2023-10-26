package dev.pack.modules.additional_prices;

import dev.pack.modules.prices.PriceDetailsDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class AdditionalPricesDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class onCreate{
        @NotEmpty()
        private String namePrice;

        @NotNull()
        private Integer path_id;

        @NotNull()
        private List<PriceDetailsDto> priceDetails;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class onUpdate{
        @NotEmpty()
        private String namePrice;

        @NotNull()
        private List<PriceDetailsDto> priceDetails;
    }

}
