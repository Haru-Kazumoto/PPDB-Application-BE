package dev.pack.modules.jalur_pendaftaran;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import dev.pack.modules.enums.FormPurchaseType;
import dev.pack.modules.gelombang_ppdb.Gelombang;
import dev.pack.modules.informasi_umum.InformasiUmum;
import dev.pack.modules.user.User;
import dev.pack.utils.Timestamps;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "jalur_pendaftaran_tbl")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class JalurPendaftaran extends Timestamps {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private FormPurchaseType tipePembelian;
    private String namaJalurPendaftaran;
    private Date waktuDibuka;
    private Date waktuDitutup;
    private Double biayaPendaftaran;

    @OneToOne(
            mappedBy = "jalurPendaftaranId",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private InformasiUmum informasiUmum;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "jalurPendaftaranId"
    )
    private List<Gelombang> gelombangList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User userId;
}
