package dev.pack.modules.biaya_tambahan;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import dev.pack.modules.informasi_umum.InformasiUmum;
import dev.pack.modules.biaya.Biaya;
import dev.pack.utils.Timestamps;
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
@Table(name = "biaya_tambahan_tbl")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class BiayaTambahan extends Timestamps {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String judulBiaya;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "biayaTambahanId"
    )
    private List<Biaya> biayaList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "informasiUmumId")
    @JsonIgnore()
    private InformasiUmum informasiUmumId;

}
