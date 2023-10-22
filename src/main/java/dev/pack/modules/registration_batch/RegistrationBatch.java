package dev.pack.modules.registration_batch;

import dev.pack.modules.registration_paths.RegistrationPaths;
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

    private Banks bank_name; //nama bank
    private String bank_user; //nama punya akun
    private Double price;
    private String bank_account; //nomor rekening

    private Integer path_id; //registration path id

    @ManyToOne(fetch = FetchType.LAZY)
    private RegistrationPaths registrationPaths;

}
