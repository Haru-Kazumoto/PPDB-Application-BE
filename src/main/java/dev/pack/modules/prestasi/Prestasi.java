package dev.pack.modules.prestasi;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import dev.pack.modules.detail_pendaftar.DetailPendaftar;
import dev.pack.modules.pengembalian_formulir.PengembalianFormulir;
import dev.pack.utils.Timestamps;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "prestasi_tbl")
public class Prestasi extends Timestamps {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String pengalaman;
    private String sertifikasi;
    private String kemampuanBahasa;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pengembalianFormulirId", updatable = false)
    @JsonIgnore
    private PengembalianFormulir pengembalianFormulirId;

}
