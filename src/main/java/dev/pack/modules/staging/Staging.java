package dev.pack.modules.staging;

import dev.pack.modules.enums.FormPurchaseType;
import dev.pack.modules.enums.Grade;
import dev.pack.modules.student_logs.StudentLogs;
import dev.pack.utils.Timestamps;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "staging")
public class Staging extends Timestamps {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name; //PILIH GELOMBANG

    private Integer index; // urutan

    private Boolean is_visible = Boolean.TRUE;

    private FormPurchaseType type;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "staging"
    )
    private List<StudentLogs> studentLogs;

}
