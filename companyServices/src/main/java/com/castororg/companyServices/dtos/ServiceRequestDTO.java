package com.castororg.companyServices.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ServiceRequestDTO(

        @NotEmpty(message = "ID of customer applicant can not be empty")
        @NotNull(message = "Id of customer applicant can not be null")
        Long idCustomerApplicant,
        @NotEmpty(message = "ID of Castor attention can not be empty")
        @NotNull(message = "Id of Castor attention can not be null")
        Integer castorActivity
) {
}
