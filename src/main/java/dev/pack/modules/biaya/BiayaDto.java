package dev.pack.modules.biaya;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class BiayaDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create{
        private String namaBiayaTambahan;
        private Double jumlahBiayaTambahan;
        private Integer biayaTambahan_id;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update{
        private String namaBiayaTambahan;
        private Double jumlahBiayaTambahan;
    }

}
