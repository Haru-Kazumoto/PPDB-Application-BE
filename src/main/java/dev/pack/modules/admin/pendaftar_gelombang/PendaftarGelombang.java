package dev.pack.modules.admin.pendaftar_gelombang;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import dev.pack.modules.admin.data_pendaftar.DataPendaftar;
import dev.pack.modules.admin.gelombang_ppdb.Gelombang;
import dev.pack.utils.Timestamps;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Entity
@Table(name = "pendaftar_gelombang_tbl")
public class PendaftarGelombang extends Timestamps {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Long jumlahPendaftar; //get all student that has registered by wave
    private Long jumlahPendaftarDiterima;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "pendaftarGelombangId",
            orphanRemoval = true
    )
    private List<DataPendaftar> dataPendaftarList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gelombangEntityId", updatable = false)
    @JsonIgnore
    private Gelombang gelombangId;

}
