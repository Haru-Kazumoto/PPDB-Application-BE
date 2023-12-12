package dev.pack.modules.student;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import dev.pack.modules.enums.Grade;
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
import java.util.UUID;

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

    private String formulirId;

    private String lastInsertedNumber;

    @Column(unique = true)
    private String nisn;

    private String name;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Column(unique = true)
    private String phone;

    private String surname;

    private String gender;
    private String religion;
    private String birth_place;
    private Date birth_date;
    private String address;
    private String province;
    private String city;
    private String district;
    private String sub_district;
    private String postal_code;
    private String school_origin;

    private String profile_picture;

    private String status;

    private String major;
    private String first_major;
    private String second_major;
    private String fix_major;

    private Date registrationDate;

    private Integer staging_id;
    private Integer path_id;
    private Integer batch_id;
    private String pathName;

    private Boolean isPurchasingDone = false;
    private Boolean isReturningDone = false;

    private String birth_card;
    private String family_card;

    private String dad_name;
    private String dad_phone;
    private String dad_job;
    private String dad_address;

    private String mother_name;
    private String mother_phone;
    private String mother_job;
    private String mother_address;

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

    @OneToMany(
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            mappedBy = "student"
    )
    @JsonIgnore
    private List<StudentAchievement> studentAchievements;
}
