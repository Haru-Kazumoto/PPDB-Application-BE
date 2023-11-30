package dev.pack.modules.exam_information;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

public class ExamInformationDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Create{
        @NotNull(message = "Title cannot be null field")
        @NotEmpty(message = "Title cannot be empty field")
        private String title;

        @NotNull(message = "Link cannot be null field")
        @NotEmpty(message = "Link cannot be empty field")
        private String link;

        @NotNull(message = "Start date canoot be null field")
        private Date startDate;

        @NotNull(message = "End date canoot be null field")
        private Date endDate;

        @NotNull(message = "Batch id cannot be null field")
        private Integer batchId;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Update {
        @NotNull(message = "Title cannot be null field")
        @NotEmpty(message = "Title cannot be empty field")
        private String title;

        @NotNull(message = "Link cannot be null field")
        @NotEmpty(message = "Link cannot be empty field")
        private String link;

        @NotNull(message = "Start date canoot be null field")
        private Date startDate;

        @NotNull(message = "End date canoot be null field")
        private Date endDate;
    }

}
