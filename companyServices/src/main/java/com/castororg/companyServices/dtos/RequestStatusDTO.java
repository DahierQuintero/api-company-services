package com.castororg.companyServices.dtos;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record RequestStatusDTO(
        @NotEmpty(message = "ID number can not be empty")
        @NotNull(message = "ID number can not be null")
        Integer id,
        @NotEmpty(message = "Status name can not be empty")
        @NotNull(message = "Status name can not be null")
        String statusName
) {}
