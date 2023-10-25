package dev.pack.modules.additional_prices;

import dev.pack.modules.prices.Prices;
import jakarta.validation.Valid;
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
        private Double price;

        @NotNull()
        private Integer index;

        @Valid()
        @NotNull()
        private List<Prices> prices;
    }

}
