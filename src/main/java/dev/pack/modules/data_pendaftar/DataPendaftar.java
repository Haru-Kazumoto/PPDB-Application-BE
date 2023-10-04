package dev.pack.modules.data_pendaftar;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import dev.pack.utils.Timestamps;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@JsonIdentityInfo(
        property = "id",
        generator = ObjectIdGenerators.PropertyGenerator.class
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "data_pendaftar_tbl")
public class DataPendaftar extends Timestamps implements Serializable {

    @Id
    private Integer id;
    private String nama;
    private String nomorTelepon;



}
