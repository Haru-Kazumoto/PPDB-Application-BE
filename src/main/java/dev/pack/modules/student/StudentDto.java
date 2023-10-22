package dev.pack.modules.student;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class StudentDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class OnRegister{
        @NotEmpty()
        @NotNull()
        private String name;
        @NotEmpty()
        @NotNull()
        private String address;
        @NotEmpty()
        @NotNull()
        private String school_origin;
    }

}
