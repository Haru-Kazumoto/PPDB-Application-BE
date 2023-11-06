package dev.pack.modules.student;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseStudentDto {

    private Integer id;
    private String phone;
    private String name;
    private String address;
    private String school_origin;

}
