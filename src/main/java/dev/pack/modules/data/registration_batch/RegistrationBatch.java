package dev.pack.modules.data.registration_batch;

import dev.pack.modules.data.registration_paths.RegistrationPaths;
import dev.pack.modules.enums.Banks;
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
@Table(name = "registration_batch")
public class RegistrationBatch {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer index;
    private Integer max_quota;
    private Date start_date;
    private Date end_date;
    private Banks bank_name;
    private String bank_user;
    private Double price;
    private String bank_account;
    private Integer path_id;

    @ManyToOne(fetch = FetchType.LAZY)
    private RegistrationPaths registrationPathsId;

}
