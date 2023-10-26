package dev.pack.modules.prices;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceDetailsDto {

    @NotNull
    @NotEmpty
    private String subTitle;

    @NotNull
    private Double price;

}
