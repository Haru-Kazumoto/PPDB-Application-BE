package dev.pack.modules.student;

import dev.pack.modules.enums.FormPurchaseType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChooseMajorDto {
    @NotNull
    private String major;

    @NotNull
    private FormPurchaseType type;

    @NotNull
    private Integer stagingId;
}
