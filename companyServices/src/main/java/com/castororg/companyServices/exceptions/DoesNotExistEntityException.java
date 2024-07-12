package com.castororg.companyServices.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DoesNotExistEntityException extends RuntimeException {

    private String status;

    public DoesNotExistEntityException(String message, String code) {
        super(message);
        this.status = code;
    }
}
