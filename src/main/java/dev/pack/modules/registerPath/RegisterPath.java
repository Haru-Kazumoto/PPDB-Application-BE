package dev.pack.modules.registerPath;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import dev.pack.modules.enums.FormPurchaseType;
import dev.pack.modules.registerPath.publicInformation.PublicInformation;
import dev.pack.modules.user.User;
import dev.pack.utils.Timestamps;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

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
public class RegisterPath extends Timestamps {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private FormPurchaseType tipePembelian;
    private String namaJalurPendaftaran;
    private Date waktuDibuka;
    private Date waktuDitutup;
    private Double biayaPendaftaran;

    @OneToOne(
            mappedBy = "registerPathId",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private PublicInformation publicInformation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User userId;
}
