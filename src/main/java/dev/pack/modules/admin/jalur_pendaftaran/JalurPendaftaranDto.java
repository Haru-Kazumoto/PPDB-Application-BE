package dev.pack.modules.admin.jalur_pendaftaran;

import dev.pack.modules.admin.enums.FormPurchaseType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

public class JalurPendaftaranDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Create{
        @Enumerated(EnumType.STRING)
        private FormPurchaseType tipePembelian;

        @NotNull
        @NotEmpty
        private String namaJalurPendaftaran;

        @NotNull
        @Past
        private Date waktuDibuka;

        @NotNull
        @Future
        private Date waktuDitutup;

        @NotNull
        private Double biayaPendaftaran;

        @NotNull
//        @JsonIgnore
        private Integer user_id;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Update{
        @Enumerated(EnumType.STRING)
        private FormPurchaseType tipePembelian;

        @NotNull
        @NotEmpty
        private String namaJalurPendaftaran;

        @NotNull
        private Date waktuDibuka;

        @NotNull
        private Date waktuDitutup;

        @NotNull
        private Double biayaPendaftaran;
    }
}
