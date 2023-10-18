package dev.pack.modules.admin.biaya;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import dev.pack.modules.admin.biaya_tambahan.BiayaTambahan;
import dev.pack.utils.Timestamps;
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
@Table(name = "biaya_tbl")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Biaya extends Timestamps {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String namaBiayaTambahan;

    private Double jumlahBiayaTambahan;

    private Integer biayaTambahan_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "biayaTambahanEntityId")
    @JsonIgnore
    private BiayaTambahan biayaTambahanId;


}
