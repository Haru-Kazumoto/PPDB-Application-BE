package dev.pack.modules.registration_batch;

import dev.pack.modules.enums.FormPurchaseType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChooseBatchDto {
    @NotNull
    private Integer batch_id;

    @Enumerated(EnumType.STRING)
    private FormPurchaseType type;
}
