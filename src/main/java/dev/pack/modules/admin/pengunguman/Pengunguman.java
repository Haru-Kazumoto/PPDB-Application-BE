package dev.pack.modules.admin.pengunguman;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.pack.modules.admin.gelombang_ppdb.Gelombang;
import dev.pack.utils.Timestamps;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
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

    @Column(unique = true, nullable = false)
    private String namaPengunguman;

    @Future
    @Column(nullable = false)
    private Date tanggalPengunguman; //Kapan kegiatan ini akan terjadi.

    private Integer gelombang_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gelombangEntityId")
    @JsonIgnore
    private Gelombang gelombangId;

}
