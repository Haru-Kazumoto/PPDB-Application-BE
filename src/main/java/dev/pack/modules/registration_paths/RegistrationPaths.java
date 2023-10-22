package dev.pack.modules.registration_paths;

import dev.pack.modules.registration_batch.RegistrationBatch;
import dev.pack.modules.enums.FormPurchaseType;
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

    @Column(unique = true, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private FormPurchaseType type;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "registrationPaths"
    )
    private List<RegistrationBatch> registrationBatches;

}
