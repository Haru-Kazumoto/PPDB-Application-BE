package dev.pack.modules.registration_general_information;

import dev.pack.modules.enums.TypeGeneral;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class RegistrationGeneralInformationDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class onCreate{

        @NotEmpty()
        private String name;

        @NotEmpty()
        private String description;

        @NotNull()
        private Integer index;

        @NotNull()
        private Integer path_id;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class onUpdate{

        @NotEmpty()
        private String name;

        @NotEmpty()
        private String description;

        @NotNull()
        private Integer index;
    }

}
