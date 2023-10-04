package dev.pack.modules.alur_ppdb;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlurPpdbDto {

    @NotEmpty
    @NotNull
    private String title;

    @NotEmpty
    @NotNull
    private String content;

    @NotNull
    private Integer userId;

}
