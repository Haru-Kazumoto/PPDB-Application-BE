package dev.pack.modules.registration_batch;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import dev.pack.modules.registration_paths.RegistrationPaths;
import dev.pack.modules.enums.Banks;
import dev.pack.modules.student.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Table(name = "registration_batch")
public class RegistrationBatch implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer index;
    private Integer max_quota;

    private Date start_date;
    private Date end_date;

    private Banks bank_name; //nama bank
    private String bank_user; //nama punya akun
    private Double price;
    private String bank_account; //nomor rekening

    private Boolean isOpen = true; //buat cron schedule service

    @Transient
    private Integer countStudent; //read only, not stored to column table

    @JsonIgnoreProperties(
            {
                    "hibernateLazyInitializer",
                    "handler"
            })
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private RegistrationPaths registrationPaths;

    //one to many ke 3 model students, student_payments, student_logs

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "registrationBatch"
    )
    private List<Student> students;

}
