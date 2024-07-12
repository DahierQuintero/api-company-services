package com.castororg.companyServices.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record EmployeeDTO(
        @NotEmpty(message = "The ID number can not be empty")
        @NotNull(message = "The ID number can not be null")
        Long idNumber,
        @NotEmpty(message = "The name can not be empty")
        @NotNull(message = "The name can not be null")
        String name,
        @NotEmpty(message = "The photo path can not be empty")
        @NotNull(message = "The photo path can not be null")
        String photoPath,
        @NotEmpty(message = "The date of entry can not be empty")
        @NotNull(message = "The date of entry can not be null")
        LocalDate dateOfEntry,
        @NotEmpty(message = "The position number can not be empty")
        @NotNull(message = "The position number can not be null")
        Integer position
) {}
