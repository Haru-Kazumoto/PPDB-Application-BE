package dev.pack.modules.student_payments;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.pack.modules.enums.FormPurchaseType;
import dev.pack.modules.enums.Grade;
import dev.pack.modules.enums.PaymentMethod;
import dev.pack.modules.lookup.Lookup;
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
@Table(name = "student_payments")
public class StudentPayments extends Timestamps {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double total;
    private PaymentMethod method;
    private String status;
    private Integer path_id;
    private Integer batch_id;
    private String image;
    private FormPurchaseType type;

    private String bank_name;
    private String bank_account;
    private String bank_user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Student student;

}
