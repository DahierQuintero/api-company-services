package com.castororg.companyServices.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record PositionDTO(

        Integer idPosition,
        @NotEmpty(message = "The position name can not be empty")
        @NotNull(message = "The position name can not be null")
        String name
) {}
