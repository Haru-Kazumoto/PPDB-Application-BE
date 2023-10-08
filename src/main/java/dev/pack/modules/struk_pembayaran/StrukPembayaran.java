package dev.pack.modules.struk_pembayaran;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import dev.pack.modules.detail_pendaftar.DetailPendaftar;
import dev.pack.modules.enums.TipePembayaran;
import dev.pack.utils.Timestamps;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Entity
@Table(name = "struk_pembayaran_tbl")
public class StrukPembayaran extends Timestamps {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fotoPembayaran; // <- ini url image
    private TipePembayaran tipePembayaran;
    private Double jumlahPembayaran;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "detailPendaftarId", updatable = false)
    @JsonIgnore
    private DetailPendaftar detailPendaftarId;

}