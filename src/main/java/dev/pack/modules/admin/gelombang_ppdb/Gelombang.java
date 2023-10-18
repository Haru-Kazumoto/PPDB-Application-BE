package dev.pack.modules.admin.gelombang_ppdb;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import dev.pack.modules.admin.enums.Banks;
import dev.pack.modules.admin.jalur_pendaftaran.JalurPendaftaran;
import dev.pack.modules.admin.ujian_penerimaan.UjianPenerimaan;
import dev.pack.modules.admin.kegiatan.Kegiatan;
import dev.pack.modules.admin.pengunguman.Pengunguman;
import dev.pack.utils.Timestamps;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "gelombang_tbl")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Gelombang extends Timestamps {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String namaGelombang;

    @Nullable
    private Double diskonGelombang;
    private Long jumlahPenerimaan;
    private Date waktuPendaftaranDibuka;
    private Date waktuPendaftaranDitutup;
    private Banks namaBank;
    private String nomorRekening;
    private String namaPemilikRekening;
    private Double biayaPendaftaran;

    private Integer jalurPendaftaran_id;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "gelombangId"
    )
    private List<UjianPenerimaan> ujianPenerimaanList;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "gelombangId"
    )
    private List<Pengunguman> pengungumanList;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "gelombangId"
    )
    private List<Kegiatan> kegiatanList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jalurPendaftaranEntityId")
    @JsonIgnore
    private JalurPendaftaran jalurPendaftaranId;

}
