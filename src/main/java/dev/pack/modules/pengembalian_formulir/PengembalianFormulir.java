package dev.pack.modules.pengembalian_formulir;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import dev.pack.modules.data_pendaftar.DataPendaftar;
import dev.pack.modules.pendaftar_ppdb.PendaftarPpdb;
import dev.pack.modules.prestasi.Prestasi;
import dev.pack.utils.Timestamps;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "pengembalian_formulir_tbl")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PengembalianFormulir extends Timestamps {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String judul;

    private Long jumlahPendaftar;
    private Long pesertaDiterima;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "pengembalianFormulirId",
            orphanRemoval = true
    )
    private List<DataPendaftar> dataPendaftarList;

    @OneToOne(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "pengembalianFormulirId"
    )
    private Prestasi prestasi;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pendaftarPpdbId")
    @JsonIgnore
    private PendaftarPpdb pendaftarPpdbId;
}
