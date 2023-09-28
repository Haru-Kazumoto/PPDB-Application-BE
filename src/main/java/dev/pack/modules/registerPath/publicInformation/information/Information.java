package dev.pack.modules.registerPath.publicInformation.information;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import dev.pack.modules.registerPath.publicInformation.PublicInformation;
import dev.pack.utils.Timestamps;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "information_tbl")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Information extends Timestamps {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String namaKeterangan;
    private String deskripsiKeterangan;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "publicInformationId", updatable = false)
    @JsonIgnore
    private PublicInformation publicInformationId;

}
