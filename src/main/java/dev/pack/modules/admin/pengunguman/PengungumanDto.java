package dev.pack.modules.admin.pengunguman;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

public class PengungumanDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create{
        private String namaPengunguman;
        private Date tanggalPengunguman;
        private Integer gelombang_id;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update{
        private String namaPengunguman;
        private Date tanggalPengunguman;
    }

}
