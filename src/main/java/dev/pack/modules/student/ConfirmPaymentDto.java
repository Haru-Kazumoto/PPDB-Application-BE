package dev.pack.modules.student;

import dev.pack.modules.enums.FormPurchaseType;
import dev.pack.modules.enums.Grade;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmPaymentDto {

    @NotNull
    private Integer payment_id;

    @NotNull
    private Integer student_id;

}
