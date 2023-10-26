package dev.pack.modules.student_logs;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.pack.modules.enums.FormPurchaseType;
import dev.pack.modules.registration_batch.RegistrationBatch;
import dev.pack.modules.staging.Staging;
import dev.pack.modules.student.Student;
import dev.pack.utils.Timestamps;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "student_logs")
public class StudentLogs extends Timestamps {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String status;

    private String remark;

    private Integer path_id;

    private FormPurchaseType type;

    @ManyToOne()
    @JoinColumn(name = "batch_id")
    private RegistrationBatch registrationBatch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Staging staging;

}
