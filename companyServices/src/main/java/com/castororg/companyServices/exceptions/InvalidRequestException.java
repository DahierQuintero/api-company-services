package com.castororg.companyServices.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class InvalidRequestException extends RuntimeException{

    private String status;

    public InvalidRequestException(String message, String code) {
        super(message);
        this.status = code;
    }
}
