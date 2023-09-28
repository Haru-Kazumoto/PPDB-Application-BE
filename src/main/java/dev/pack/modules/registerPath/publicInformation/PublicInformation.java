package dev.pack.modules.registerPath.publicInformation;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import dev.pack.modules.registerPath.RegisterPath;
import dev.pack.modules.registerPath.publicInformation.aditionalCost.AdditionalCost;
import dev.pack.modules.registerPath.publicInformation.information.Information;
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
@Table(name = "informasi_umum_tbl")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class PublicInformation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(
            mappedBy = "publicInformationId",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Information keterangan;

    @OneToMany(
            mappedBy = "publicInformationId",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<AdditionalCost> biayaTambahan;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "registerPathId", updatable = false)
    @JsonIgnore
    private RegisterPath registerPathId;

}
