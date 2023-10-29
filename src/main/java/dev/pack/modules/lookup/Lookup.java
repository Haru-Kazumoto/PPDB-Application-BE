package dev.pack.modules.lookup;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.pack.modules.student_payments.StudentPayments;
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
@Table(name = "lookup")
public class Lookup {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name; // Teknik Jaringan dan Komputer

    private String value; // TJKT

    private String type; // MAJOR

}