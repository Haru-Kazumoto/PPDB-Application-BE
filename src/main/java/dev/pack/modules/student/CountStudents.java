package dev.pack.modules.student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CountStudents {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class DetailBatch {
        private Long totalStudents;
        private Long studentAccepted;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class InfoBatch {
        private Integer id;
        private Long totalStudents;
    }
}
