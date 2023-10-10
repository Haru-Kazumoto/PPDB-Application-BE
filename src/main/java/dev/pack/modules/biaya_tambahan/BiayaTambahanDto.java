package dev.pack.modules.biaya_tambahan;

import lombok.*;

public class BiayaTambahanDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create{
        public String judulBiaya;
        public Integer jalurPendaftaran_id;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update{
        public String judulBiaya;
    }

}
