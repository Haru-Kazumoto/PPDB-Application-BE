package dev.pack.modules.struk_pembayaran;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import dev.pack.modules.detail_pendaftar.DetailPendaftar;
import dev.pack.modules.enums.TipePembayaran;
import dev.pack.utils.Timestamps;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String fotoPembayaran;
    private TipePembayaran tipePembayaran;
    private Double jumlahPembayaran;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "detailPendaftarId", updatable = false)
    private DetailPendaftar detailPendaftarId;

}