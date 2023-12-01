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

    private String first_major;
    private String second_major;
    private String fix_major;

    @NotNull
    private FormPurchaseType type;

    @NotNull
    private Integer stagingId;
}
