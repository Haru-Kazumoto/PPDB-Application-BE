package dev.pack.modules.pendaftar_ppdb;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import dev.pack.modules.pembelian_formulir.PembelianFormulir;
import dev.pack.modules.pengembalian_formulir.PengembalianFormulir;
import dev.pack.modules.user.User;
import dev.pack.utils.Timestamps;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pendaftar_ppdb_tbl")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class PendaftarPpdb extends Timestamps {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(
            mappedBy = "pendaftarPpdbId",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    List<PembelianFormulir> pembelianFormulirList;

    @OneToMany(
            mappedBy = "pendaftarPpdbId",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    List<PengembalianFormulir> pengembalianFormulirList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", updatable = false)
    @JsonIgnore
    private User userId;

}
