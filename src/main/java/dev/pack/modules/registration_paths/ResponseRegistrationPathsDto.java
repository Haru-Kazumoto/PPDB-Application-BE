package dev.pack.modules.registration_paths;

import dev.pack.modules.additional_prices.ResponseAdditionalPriceDto;
import dev.pack.modules.enums.Grade;
import dev.pack.modules.registration_batch.ResponseRegistrationBatchDto;
import dev.pack.modules.registration_general_information.ResponseGeneralInformationDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseRegistrationPathsDto {

    private Integer id;
    private String name;
    private Grade grade;
    private List<ResponseRegistrationBatchDto> registrationBatches;
    private List<ResponseGeneralInformationDto> generalInformations;
    private List<ResponseAdditionalPriceDto> additionalPrices;

}
