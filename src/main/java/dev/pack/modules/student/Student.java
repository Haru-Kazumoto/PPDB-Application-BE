package dev.pack.modules.student;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import dev.pack.modules.enums.Genders;
import dev.pack.modules.registration_batch.RegistrationBatch;
import dev.pack.modules.registration_paths.RegistrationPaths;
import dev.pack.modules.student_logs.StudentLogs;
import dev.pack.modules.student_payments.StudentPayments;
import dev.pack.modules.user.User;
import dev.pack.utils.Timestamps;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

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
@Table(name = "students")
public class Student extends Timestamps implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @UuidGenerator
    private String uniqueId;

    @Column(unique = true)
    private String nisn;

    private String name; //2

    @Column(unique = true)
    private String phone; //1

    private String surname;

    private String gender;
    private String religion;
    private String birth_place;
    private Date birth_date; //custom
    private String address; //3
    private String province;
    private String city;
    private String district;
    private String sub_district;
    private String postal_code;
    private String school_origin; //4
    private String profile_picture;
    private String status;
    private String major; //mengambil data type dari lookup

    private Date registrationDate;

    private Integer staging_id;
    private Integer path_id;
    private Integer batch_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private RegistrationBatch registrationBatch;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User userId;

    @OneToMany(
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            mappedBy = "student"
    )
    @JsonIgnore
    private List<StudentLogs> studentLogs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regis_path")
    @JsonIgnore
    private RegistrationPaths registrationPaths;

    @OneToMany(
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            mappedBy = "student"
    )
    @JsonIgnore
    private List<StudentPayments> studentPayments;
}
