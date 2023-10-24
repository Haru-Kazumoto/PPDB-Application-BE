package dev.pack.modules.registration_paths;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import dev.pack.modules.registration_batch.RegistrationBatch;
import dev.pack.modules.enums.FormPurchaseType;
import dev.pack.modules.registration_general_information.RegistrationGeneralInformation;
import dev.pack.modules.student.Student;
import dev.pack.modules.student_logs.StudentLogs;
import dev.pack.modules.student_payments.StudentPayments;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

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
@Table(name = "registration_paths")
public class RegistrationPaths implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Enumerated(EnumType.STRING)
    private FormPurchaseType type;

    private Date start_date;

    @Future()
    private Date end_date;

    private Double price;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "registrationPaths"
    )
    @JsonIgnore
    private List<RegistrationBatch> registrationBatches;

    @JsonIgnoreProperties(
            {
                    "hibernateLazyInitializer",
                    "handler"
            })
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "registrationPaths"
    )
    @JsonIgnore
    private List<RegistrationGeneralInformation> registrationGeneralInformations;

    //one to many ke 3 model student, student_logs, student_payments
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "registrationPaths"
    )
    @JsonIgnore
    private List<Student> students;

}
