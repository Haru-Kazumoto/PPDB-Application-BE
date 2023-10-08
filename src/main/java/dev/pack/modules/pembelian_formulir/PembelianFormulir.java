package dev.pack.modules.pembelian_formulir;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import dev.pack.modules.data_pendaftar.DataPendaftar;
import dev.pack.modules.pendaftar_ppdb.PendaftarPpdb;
import dev.pack.utils.Timestamps;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "pembelian_formulir_tbl")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class PembelianFormulir extends Timestamps {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String judul;
    private Long jumlahPendaftar;
    private Long jumlahPenerimaan;
    private Long pesertaDiterima;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "pembelianFormulirId",
            orphanRemoval = true
    )
    private List<DataPendaftar> dataPendaftarList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pendaftarPpdbId")
    private PendaftarPpdb pendaftarPpdbId;
}
