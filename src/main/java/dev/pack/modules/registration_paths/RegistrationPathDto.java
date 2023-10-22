package dev.pack.modules.registration_paths;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class RegistrationPathDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create{
        @NotNull()
        @NotEmpty()
        private String name;

        @NotNull()
        @NotEmpty()
        private String type;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update{
        @NotNull()
        @NotEmpty()
        private String name;

        @NotNull()
        @NotEmpty()
        private String type;
    }

}
