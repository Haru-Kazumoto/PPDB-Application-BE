package dev.pack.modules.student.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetStudentAchievementDto {

    @NotNull
    private String type;

    @NotNull
    private Integer studentId;
}