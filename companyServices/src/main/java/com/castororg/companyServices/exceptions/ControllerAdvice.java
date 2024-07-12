package com.castororg.companyServices.exceptions;

import com.castororg.companyServices.dtos.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class ControllerAdvice extends RuntimeException{

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ExceptionDTO> runtimeExceptionHandler(RuntimeException ex) {
        ExceptionDTO exceptionDTO = ExceptionDTO.builder()
                .code("400")
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(exceptionDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidRequestException.class)
    public ResponseEntity<ExceptionDTO> requestExceptionHandler(InvalidRequestException ex) {
        ExceptionDTO exceptionDTO = ExceptionDTO.builder()
                .code(ex.getStatus())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(exceptionDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DoesNotExistEntityException.class)
    public ResponseEntity<ExceptionDTO> doesNotExistEntityExceptionHandler(DoesNotExistEntityException ex) {
        ExceptionDTO exceptionDTO = ExceptionDTO.builder()
                .code(ex.getStatus())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }

}
