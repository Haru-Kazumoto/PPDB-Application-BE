package dev.pack.modules.registration_batch.dto;

import dev.pack.modules.enums.FormPurchaseType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetStagingStatusDto {
    @NotNull
    private Integer stagingId;

    @NotNull
    private FormPurchaseType type;
}
