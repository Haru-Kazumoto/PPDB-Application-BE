package dev.pack.modules.registration_paths;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import dev.pack.modules.additional_prices.AdditionalPrices;
import dev.pack.modules.enums.Grade;
import dev.pack.modules.registration_batch.RegistrationBatch;
import dev.pack.modules.enums.FormPurchaseType;
import dev.pack.modules.exam_information.ExamInformation;
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

    @Enumerated(EnumType.STRING)
    private Grade grade;

    private Integer countStudent;

    public RegistrationPaths(Integer id, String name, FormPurchaseType type, Date start_date, Date end_date, Double price, Integer countStudent) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.start_date = start_date;
        this.end_date = end_date;
        this.price = price;
        this.countStudent = countStudent;
    }

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

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "registrationPaths"
    )
    @JsonIgnore
    private List<AdditionalPrices> additionalPrices;


    //one to many ke 3 model student, student_logs, student_payments
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "registrationPaths"
    )
    @JsonIgnore
    private List<Student> students;

}
