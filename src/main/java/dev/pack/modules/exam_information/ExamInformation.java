package dev.pack.modules.exam_information;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.pack.modules.registration_batch.RegistrationBatch;
import dev.pack.modules.registration_paths.RegistrationPaths;
import dev.pack.utils.Timestamps;
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
@Table(name = "exam_information")
public class ExamInformation extends Timestamps {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String link;
    private Date startDate;
    private Date endDate;
    private Integer batchId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private RegistrationBatch registrationBatch;

}
