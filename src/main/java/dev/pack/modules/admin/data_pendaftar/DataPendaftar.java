package dev.pack.modules.admin.data_pendaftar;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import dev.pack.modules.admin.enums.StatusPendaftaran;
import dev.pack.modules.admin.pendaftar_gelombang.PendaftarGelombang;
import dev.pack.modules.admin.prestasi.Prestasi;
import dev.pack.modules.admin.struk_pembayaran.StrukPembayaran;
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

    private Integer pendaftarGelombang_id; //Relation key

    private String namaPendaftar;
    private String alamatPendaftar;
    private Boolean statusPembayaranPendaftar;

    @OneToOne(
            mappedBy = "dataPendaftarId",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private StrukPembayaran strukPembayaran;

    @OneToOne(
            cascade = CascadeType.ALL,
            mappedBy = "dataPendaftarId",
            orphanRemoval = true
    )
    private Prestasi prestasi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pendaftarGelombangEntityId")
    private PendaftarGelombang pendaftarGelombangId;

}
