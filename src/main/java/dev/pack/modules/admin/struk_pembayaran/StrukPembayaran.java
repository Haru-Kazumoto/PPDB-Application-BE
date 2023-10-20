package dev.pack.modules.admin.struk_pembayaran;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import dev.pack.modules.admin.data_pendaftar.DataPendaftar;
import dev.pack.modules.admin.enums.TipePembayaran;
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
    @JoinColumn(name = "dataPendaftarId", updatable = false)
    @JsonIgnore
    private DataPendaftar dataPendaftarId;

}