package dev.pack.modules.alur_ppdb;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AlurPpdbDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create{
        @NotEmpty
        @NotNull
        private String title;

        @NotEmpty
        @NotNull
        private String content;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update{
        @NotEmpty
        @NotNull
        private String title;

        @NotEmpty
        @NotNull
        private String content;
    }

}
