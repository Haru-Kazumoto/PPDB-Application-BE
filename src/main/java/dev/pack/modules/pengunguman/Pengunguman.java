package dev.pack.modules.pengunguman;

import dev.pack.modules.gelombang_ppdb.Gelombang;
import dev.pack.utils.Timestamps;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pengunguman_tbl")
public class Pengunguman extends Timestamps {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String namaPengunguman;
    private Date tanggalPengunguman;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gelombangId")
    private Gelombang gelombangId;

}
