package dev.pack.modules.registerPath.publicInformation.aditionalCost.cost;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import dev.pack.modules.registerPath.publicInformation.aditionalCost.AdditionalCost;
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
@Table(name = "cost_tbl")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Cost {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String namaBiayaTambahan;

    private Double jumlahBiayaTambahan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "additionalCostId")
//    @JsonIgnore <- uncomment this when has success
    private AdditionalCost additionalCostId;


}
