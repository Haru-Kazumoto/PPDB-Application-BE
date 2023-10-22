package dev.pack.modules.newdata.student_payments;

import dev.pack.modules.enums.PaymentMethod;
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
    private Integer pict_id;

}
