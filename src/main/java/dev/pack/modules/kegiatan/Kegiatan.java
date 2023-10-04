package dev.pack.modules.kegiatan;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import dev.pack.modules.gelombang_ppdb.Gelombang;
import dev.pack.utils.Timestamps;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "kegiatan_tbl")
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Kegiatan extends Timestamps {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String namaKegiatan;
    private Date waktuDibuka;
    private Date waktuDitutup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gelombangId")
    private Gelombang gelombangId;

}
