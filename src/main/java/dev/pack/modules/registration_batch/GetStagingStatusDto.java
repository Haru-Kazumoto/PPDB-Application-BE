package dev.pack.modules.registration_batch;

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

    private Integer StudentId;
}
