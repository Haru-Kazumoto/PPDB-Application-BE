package dev.pack.modules.informasi_umum;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import dev.pack.modules.biaya_tambahan.BiayaTambahan;
import dev.pack.modules.keterangan.Keterangan;
import dev.pack.modules.jalur_pendaftaran.JalurPendaftaran;
import dev.pack.utils.Timestamps;
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
public class InformasiUmum extends Timestamps {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(
            mappedBy = "informasiUmumId",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Keterangan keterangan;

    @OneToMany(
            mappedBy = "informasiUmumId",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<BiayaTambahan> biayaTambahan;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "jalurPendaftaranId", updatable = false)
    @JsonIgnore
    private JalurPendaftaran jalurPendaftaranId;

}
