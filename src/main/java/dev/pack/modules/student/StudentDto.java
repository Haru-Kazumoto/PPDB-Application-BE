package dev.pack.modules.student;

import dev.pack.modules.enums.Genders;
import dev.pack.modules.enums.Grade;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

public class StudentDto {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class OnRegister{
        @NotEmpty()
        @NotNull()
        private String name;
        @NotNull()
        @NotEmpty()
        private Grade grade;
        @NotEmpty()
        @NotNull()
        private String address;
        @NotEmpty()
        @NotNull()
        private String school_origin;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class onUpdate{
        private String nisn;
        private String name;
        private String phone;
        private String surname;
        private Genders gender;
        private String religion;
        private String birth_place;
        private Date birth_date;
        private String address;
        private String province;
        private String city;
        private String district;
        private String sub_district;
        private String postal_code;
        private String school_origin;

        private String profile_picture;

        private String status;
        private String major; //mengambil data type dari lookup
    }

}
