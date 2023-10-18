package dev.pack.modules.admin.jalur_pendaftaran;

import com.fasterxml.jackson.annotation.*;
import dev.pack.modules.admin.biaya_tambahan.BiayaTambahan;
import dev.pack.modules.admin.enums.FormPurchaseType;
import dev.pack.modules.admin.gelombang_ppdb.Gelombang;
import dev.pack.modules.admin.keterangan.Keterangan;
import dev.pack.modules.user.User;
import dev.pack.utils.Timestamps;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import lombok.*;

import java.util.ArrayList;
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

    @Enumerated(EnumType.STRING)
    private FormPurchaseType tipePembelian;

    @Column(unique = true, nullable=false)
    private String namaJalurPendaftaran;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date waktuDibuka;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date waktuDitutup; //contoh penginputan 2023-10-05T08:00:00

    private Double biayaPendaftaran;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer user_id;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "jalurPendaftaranId"
    )
    private List<Keterangan> keteranganList;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "jalurPendaftaranId"
    )
    private List<BiayaTambahan> biayaTambahanList;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "jalurPendaftaranId"
    )
    private List<Gelombang> gelombangList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userEntityId")
    @JsonIgnore
    private User userId;
}
