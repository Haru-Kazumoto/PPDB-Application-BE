package dev.pack.modules.admin.keterangan;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class KeteranganDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create{
        @NotNull()
        @NotEmpty()
        private String namaKeterangan;

        @NotNull()
        @NotEmpty()
        private String deskripsiKeterangan;

        @NotNull()
        private Integer jalurPendaftaran_id;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update{
        @NotNull()
        @NotEmpty()
        private String namaKeterangan;

        @NotNull()
        @NotEmpty()
        private String deskripsiKeterangan;
    }

}
