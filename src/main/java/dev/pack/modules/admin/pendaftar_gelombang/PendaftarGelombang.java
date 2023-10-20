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

    private Integer jumlahPendaftar; //auto get all student that has registered by wave

    private Integer jumlahPendaftarDiterima; //Auto get data when data from dataPendaftar

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

    //TODO:BUAT DATA OTOMATIS UNTUK MENGHITUNG DATA DATA USER YANG MENDAFTAR DI GELOMBANG INI.

    @PrePersist
    public void onCount(){
        this.jumlahPendaftar = dataPendaftarList.size();
        this.jumlahPendaftarDiterima = Math.toIntExact(dataPendaftarList
                .stream()
                .filter(DataPendaftar::getStatusPembayaranPendaftar)
                .count());
    }
}
