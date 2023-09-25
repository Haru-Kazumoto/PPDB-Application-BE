package dev.pack.modules.ppdbFlow;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PpdbFlowDto {

    @NotEmpty
    @NotNull
    private String title;

    @NotEmpty
    @NotNull
    private String content;

    @NotNull
    private Integer userId;

}
