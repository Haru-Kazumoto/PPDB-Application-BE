package dev.pack.modules.jalur_pendaftaran;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.pack.modules.enums.FormPurchaseType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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
        private Date waktuDibuka;

        @NotNull
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
