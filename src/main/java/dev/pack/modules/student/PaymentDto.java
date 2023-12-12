package dev.pack.modules.student;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public class PaymentDto {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Confirm{
        @NotNull
        private Integer payment_id;

        @NotNull
        private Integer student_id;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Cancel{
        @NotNull
        private Integer payment_id;

        @NotNull
        private Integer student_id;
    }

}
