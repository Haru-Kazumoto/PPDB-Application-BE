package dev.pack.modules.registration_paths;

import dev.pack.modules.enums.FormPurchaseType;
import dev.pack.modules.enums.Grade;
import dev.pack.modules.registration_batch.RegistrationBatch;
import dev.pack.modules.registration_batch.RegistrationBatchDto;
import jakarta.annotation.Nullable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

public class RegistrationPathDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create{
        @NotNull()
        @NotEmpty()
        private String name;

        @NotNull()
        @Enumerated(EnumType.STRING)
        private FormPurchaseType type;

        private Date start_date;

        @Future
        private Date end_date;

        private Double price;

        private Grade grade;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update{
        @NotNull()
        @NotEmpty()
        private String name;

        @NotNull()
        @NotEmpty()
        private String type;

        private Date start_date;

        @Future
        private Date end_date;

        private Double price;
    }

}
