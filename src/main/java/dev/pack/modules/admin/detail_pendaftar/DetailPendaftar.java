package dev.pack.modules.admin.detail_pendaftar;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import dev.pack.modules.admin.data_pendaftar.DataPendaftar;
import dev.pack.modules.admin.enums.StatusPembayaran;
import dev.pack.modules.admin.prestasi.Prestasi;
import dev.pack.modules.admin.struk_pembayaran.StrukPembayaran;
import dev.pack.utils.Timestamps;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(
        property = "id",
        generator = ObjectIdGenerators.PropertyGenerator.class
)
@Entity
@Table(name = "detail_pendaftar_tbl")
public class DetailPendaftar extends Timestamps {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String namaPendaftar;
    private String alamatPendaftar;
    private String nomorTelepon;
    private Date tanggalMendaftar;
    private StatusPembayaran statusPembayaranPendaftar;

    @OneToOne(
            mappedBy = "detailPendaftarId",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private StrukPembayaran strukPembayaran;

    @OneToOne(
            cascade = CascadeType.ALL,
            mappedBy = "detailPendaftarId",
            orphanRemoval = true
    )
    private Prestasi prestasi;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dataPendaftarEntityId", updatable = false)
    @JsonIgnore
    private DataPendaftar dataPendaftarId;
}