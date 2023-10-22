package dev.pack.modules.data.registration_paths;

import dev.pack.modules.data.registration_batch.RegistrationBatch;
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
@Table(name = "registration_paths")
public class RegistrationPaths {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String type;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "registrationPathsId"
    )
    private List<RegistrationBatch> registrationBatches;

}
