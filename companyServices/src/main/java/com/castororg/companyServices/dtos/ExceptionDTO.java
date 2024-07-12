package com.castororg.companyServices.dtos;

import lombok.Builder;

@Builder
public record ExceptionDTO(
        String code,
        String message
) {}
