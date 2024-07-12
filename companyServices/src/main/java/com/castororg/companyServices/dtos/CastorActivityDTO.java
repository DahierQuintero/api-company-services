package com.castororg.companyServices.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record CastorActivityDTO(

        @NotEmpty(message = "Activity number can not be empty")
        @NotNull(message = "Activity number can not be null")
        Integer activityNumber,
        @NotEmpty(message = "Service name can not be empty")
        @NotNull(message = "Service name can not be null")
        String name
) {}
