package dev.pack.modules.additional_prices;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.pack.modules.prices.PriceDetails;
import dev.pack.modules.registration_paths.RegistrationPaths;
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
@Table(name = "additional_prices")
public class AdditionalPrices {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String namePrice;

    private Integer path_id;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "additionalPrices"
    )
    private List<PriceDetails> priceDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private RegistrationPaths registrationPaths;
}
