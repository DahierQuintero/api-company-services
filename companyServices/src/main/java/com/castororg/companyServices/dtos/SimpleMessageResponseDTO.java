package com.castororg.companyServices.dtos;


import lombok.Builder;

@Builder
public record SimpleMessageResponseDTO(
        String message
) {}
