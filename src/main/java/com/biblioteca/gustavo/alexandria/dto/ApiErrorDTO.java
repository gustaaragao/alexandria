package com.biblioteca.gustavo.alexandria.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import lombok.Builder;

// Será que essa abordagem é boa? Eu me inspirei em uma que eu vi no Baeldung.
@Builder
public record ApiErrorDTO(
    LocalDateTime timestamp,
    int code,
    String status,
    String field,
    String message
) {

    public ApiErrorDTO(HttpStatus httpStatus, FieldError error) {
        this(
            LocalDateTime.now(), 
            httpStatus.value(), 
            httpStatus.getReasonPhrase(), 
            error.getField(), 
            error.getDefaultMessage()
        );
    }

    // Esse construtor é para errors que não possuem campos, apenas uma mensagem.
    public ApiErrorDTO(HttpStatus httpStatus, String message) {
        this(
            LocalDateTime.now(), 
            httpStatus.value(), 
            httpStatus.getReasonPhrase(), 
            null,
            message
        );
    }
}
