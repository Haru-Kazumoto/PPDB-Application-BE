package dev.pack.modules.prices;

import dev.pack.modules.additional_prices.AdditionalPrices;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "prices")
public class Prices {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String subTitle;
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "additional_prices_id")
    private AdditionalPrices additionalPrices;

}
