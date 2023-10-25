package dev.pack.modules.registration_general_information;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.pack.modules.additional_prices.AdditionalPrices;
import dev.pack.modules.enums.TypeGeneral;
import dev.pack.modules.registration_paths.RegistrationPaths;
import dev.pack.utils.Timestamps;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "registration_general_information") //DESKRIPSI
public class RegistrationGeneralInformation extends Timestamps {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private Integer index;

    private Integer path_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private RegistrationPaths registrationPaths;

}
