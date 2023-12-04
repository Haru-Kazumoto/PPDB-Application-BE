package dev.pack.modules.registration_batch;

import com.fasterxml.jackson.annotation.*;
import dev.pack.modules.enums.Grade;
import dev.pack.modules.exam_information.ExamInformation;
import dev.pack.modules.registration_paths.RegistrationPaths;
import dev.pack.modules.enums.Banks;
import dev.pack.modules.student.Student;
import dev.pack.modules.student_logs.StudentLogs;
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
@Table(name = "registration_batch")
public class RegistrationBatch implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer index;
    private Integer max_quota;
    private String batchCode;
    private Date start_date;
    private Date end_date;

    @Enumerated(value = EnumType.STRING)
    private Banks bank_name;

    private String bank_user;
    private Double price;
    private String bank_account;
    private Integer path_id;
    private Boolean isOpen = true;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    private Integer countStudent=0;

    public RegistrationBatch(Integer id, String name, Integer index, Integer max_quota, String batchCode, Date start_date, Date end_date, Banks bank_name, String bank_user, Double price, String bank_account, Integer countStudent) {
        this.id = id;
        this.name = name;
        this.index = index;
        this.max_quota = max_quota;
        this.batchCode = batchCode;
        this.start_date = start_date;
        this.end_date = end_date;
        this.bank_name = bank_name;
        this.bank_user = bank_user;
        this.price = price;
        this.bank_account = bank_account;
        this.countStudent = countStudent;
    }

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
    @JsonIgnore
    private List<Student> students;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "registrationBatch"
    )
    @JsonIgnore
    private List<ExamInformation> examInformations;

    @OneToMany(
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            mappedBy = "registrationBatch"
    )
    @JsonIgnore
    private List<StudentLogs> studentLogs;

}