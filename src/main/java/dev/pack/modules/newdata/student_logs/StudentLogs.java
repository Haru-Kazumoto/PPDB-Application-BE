package dev.pack.modules.newdata.student_logs;

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
    private Integer batch_id;
    private Integer staging_id;

}
