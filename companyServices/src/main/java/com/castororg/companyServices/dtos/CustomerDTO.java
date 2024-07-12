package com.castororg.companyServices.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record CustomerDTO(
        @NotEmpty(message = "The ID number can not be empty")
        @NotNull(message = "The ID number can not be null")
        Long id,
        @NotEmpty(message = "The name can not be empty")
        @NotNull(message = "The name can not be null")
        String name,
        @NotEmpty(message = "The email can not be empty")
        @NotNull(message = "The email can not be null")
        @Email
        String email,
        @NotEmpty(message = "The phone number can not be empty")
        @NotNull(message = "The phone number can not be null")
        Long phoneNumber
) {
}
