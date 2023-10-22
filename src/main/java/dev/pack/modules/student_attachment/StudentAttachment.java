package dev.pack.modules.student_attachment;

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
@Table(name = "student_attachment")
public class StudentAttachment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String file;
    private Boolean is_validated;
    private Integer student_id;

}
