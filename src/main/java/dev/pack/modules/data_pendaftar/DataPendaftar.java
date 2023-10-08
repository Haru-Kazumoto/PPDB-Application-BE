package dev.pack.modules.data_pendaftar;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import dev.pack.modules.detail_pendaftar.DetailPendaftar;
import dev.pack.modules.enums.StatusPendaftaran;
import dev.pack.modules.pembelian_formulir.PembelianFormulir;
import dev.pack.modules.pendaftar_ppdb.PendaftarPpdb;
import dev.pack.modules.pengembalian_formulir.PengembalianFormulir;
import dev.pack.utils.Timestamps;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@JsonIdentityInfo(
        property = "id",
        generator = ObjectIdGenerators.PropertyGenerator.class
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "data_pendaftar_tbl")
public class DataPendaftar extends Timestamps implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nama;
    @Column(unique = true)
    private String nomorTelepon;
    private Date tanggalMendaftar;
    private StatusPendaftaran statusPendaftaran;

    @OneToOne(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "dataPendaftarId"
    )
    private DetailPendaftar detailPendaftar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pembelianFormulirId")
    @JsonIgnore
    private PembelianFormulir pembelianFormulirId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pengembalianFormulirId")
    @JsonIgnore
    private PengembalianFormulir pengembalianFormulirId;

}
