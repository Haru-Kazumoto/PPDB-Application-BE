package dev.pack.modules.additional_prices;

import dev.pack.modules.prices.Prices;
import dev.pack.modules.registration_general_information.RegistrationGeneralInformation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "additinoal_prices")
public class AdditionalPrices {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double price;
    private Integer index;
    private Integer info_id;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "additionalPrices"
    )
    private List<Prices> prices;
}
