package dev.pack.modules.admin.kegiatan;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

public class KegiatanDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create{
        @NotNull @NotEmpty
        private String namaKegiatan;
        @NotNull
        private Date waktuDibuka;
        @NotNull
        private Date waktuDitutup;

        @NotNull
        private Integer gelombang_id;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update{
        @NotNull @NotEmpty
        private String namaKegiatan;
        @NotNull
        private Date waktuDibuka;
        @NotNull
        private Date waktuDitutup;
    }


}
