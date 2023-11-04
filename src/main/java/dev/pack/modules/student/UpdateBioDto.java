package dev.pack.modules.student;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.pack.modules.registration_batch.RegistrationBatch;
import dev.pack.modules.registration_paths.RegistrationPaths;
import dev.pack.modules.student_logs.StudentLogs;
import dev.pack.modules.student_payments.StudentPayments;
import dev.pack.modules.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBioDto {

    private String nisn;

    private String name;

    private String phone;

    private String surname;

    private String gender;
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
    private MultipartFile profile_picture;
    private String status;
    private String major;

    private Integer staging_id;
    private Integer path_id;
    private Integer batch_id;

    private String dad_name;
    private String dad_phone;
    private String dad_job;
    private String dad_address;

    private String mother_name;
    private String mother_phone;
    private String mother_job;
    private String mother_address;

    private MultipartFile family_card;

    private MultipartFile birth_card;
}
