package dev.pack.modules.registerPath.publicInformation.aditionalCost;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import dev.pack.modules.registerPath.publicInformation.PublicInformation;
import dev.pack.modules.registerPath.publicInformation.aditionalCost.cost.Cost;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "aditional_cost_tbl")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class AdditionalCost {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String judulBiaya;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "additionalCostId"
    )
    private List<Cost> costs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publicInformationId")
//    @JsonIgnore() <- uncomment this when has successfully.
    private PublicInformation publicInformationId;

}
