package dev.pack.modules.gelombang_ppdb;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import dev.pack.modules.enums.Banks;
import dev.pack.modules.jalur_pendaftaran.JalurPendaftaran;
import dev.pack.modules.kegiatan.Kegiatan;
import dev.pack.modules.pengunguman.Pengunguman;
import dev.pack.modules.ujian_penerimaan.UjianPenerimaan;
import dev.pack.utils.Timestamps;
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
    private Double diskonGelombang;
    private Long jumlahPenerimaan;
    private Date waktuPendaftaranDibuka;
    private Date waktuPendaftaranDitutup;
    private Banks namaBank;
    private String nomorRekening;
    private String namaPemilikRekening;
    private Double biayaPendaftaran;

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
    @JoinColumn(name = "jalurPendaftaranId")
    @JsonIgnore
    private JalurPendaftaran jalurPendaftaranId;

}
