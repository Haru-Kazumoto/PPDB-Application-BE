package dev.pack.modules.registration_batch;

import dev.pack.modules.enums.Banks;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

public class RegistrationBatchDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Create{
        @NotNull()
        private Integer index;

        @NotEmpty
        @NotNull
        private String name;

        @NotNull()
        private Integer max_quota;

        @NotEmpty()
        @NotNull()
        private String batchCode;

        @NotNull()
        private Date start_date;

        @NotNull()
        @Future()
        private Date end_date;

        @NotNull()
        @Enumerated(EnumType.STRING)
        private Banks bank_name; //nama bank

        @NotNull()
        private String bank_user; //nama punya akun

        @NotNull()
        private Double price;

        @NotNull()
        private Integer path_id;

        @NotNull()
        private String bank_account; //nomor rekening
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Update{
        @NotNull()
        private Integer index;

        @NotEmpty
        @NotNull
        private String name;

        @NotNull()
        private Integer max_quota;

        @NotEmpty()
        @NotNull()
        private String batchCode;

        @NotNull()
        private Date start_date;

        @NotNull()
        @Future()
        private Date end_date;

        @NotNull()
        @Enumerated(EnumType.STRING)
        private Banks bank_name; //nama bank

        @NotNull()
        private String bank_user; //nama punya akun

        @NotNull()
        private Double price;

        @NotNull()
        private String bank_account; //nomor rekening
    }

}


