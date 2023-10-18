package dev.pack.modules.admin.gelombang_ppdb;

import dev.pack.modules.admin.enums.Banks;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

public class GelombangDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create{
        @NotEmpty @NotNull
        private String namaGelombang;
        @NotNull
        private Double diskonGelombang;
        @NotNull
        private Long jumlahPenerimaan;
        @NotNull
        private Date waktuPendaftaranDibuka;
        @NotNull
        private Date waktuPendaftaranDitutup;
        @NotNull
        private Banks namaBank;
        @NotEmpty @NotNull
        private String nomorRekening;
        @NotEmpty @NotNull
        private String namaPemilikRekening;
        @NotNull
        private Double biayaPendaftaran;
        @NotNull
        private Integer jalurPendaftaran_id;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update{
        @NotEmpty @NotNull
        private String namaGelombang;
        @NotNull
        private Double diskonGelombang;
        @NotNull
        private Long jumlahPenerimaan;
        @NotNull
        private Date waktuPendaftaranDibuka;
        @NotNull
        private Date waktuPendaftaranDitutup;
        @NotNull
        private Banks namaBank;
        @NotEmpty @NotNull
        private String nomorRekening;
        @NotEmpty @NotNull
        private String namaPemilikRekening;
        @NotNull
        private Double biayaPendaftaran;
    }

}
