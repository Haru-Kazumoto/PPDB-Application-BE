package dev.pack.modules.keterangan;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import dev.pack.modules.jalur_pendaftaran.JalurPendaftaran;
import dev.pack.utils.Timestamps;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "keterangan_tbl")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Keterangan extends Timestamps {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String namaKeterangan;
    private String deskripsiKeterangan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jalurPendaftaranId")
    @JsonIgnore
    private JalurPendaftaran jalurPendaftaranId;

}
