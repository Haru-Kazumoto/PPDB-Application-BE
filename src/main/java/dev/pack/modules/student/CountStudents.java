package dev.pack.modules.student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountStudents {

    private Long totalStudents;
    private Long studentAccepted;

}
