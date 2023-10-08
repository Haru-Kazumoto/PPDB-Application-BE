package dev.pack.modules.jalur_pendaftaran;

import com.fasterxml.jackson.annotation.*;
import dev.pack.modules.enums.FormPurchaseType;
import dev.pack.modules.gelombang_ppdb.Gelombang;
import dev.pack.modules.informasi_umum.InformasiUmum;
import dev.pack.modules.user.User;
import dev.pack.utils.Timestamps;
import jakarta.persistence.*;
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
    private List<Gelombang> gelombangList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userEntityId")
    @JsonIgnore
    private User userId;
}
