package dev.pack.modules.registration_general_information;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGeneralInformationDto {

    private Integer id;
    private String name;
    private String description;

}
