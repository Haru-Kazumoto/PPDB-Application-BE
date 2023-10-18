package dev.pack.modules.admin.ujian_penerimaan;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import dev.pack.modules.admin.enums.MediaTest;
import dev.pack.modules.admin.gelombang_ppdb.Gelombang;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ujian_penerimaan_tbl")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class UjianPenerimaan {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String namaUjianPenerimaan;
    private MediaTest mediaTest;
    private String keterangan;
    private Date waktuDibuka;
    private Date waktuDitutup;
    private String lokasiTest;
    private Integer kkm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gelombangId")
    private Gelombang gelombangId;
}
