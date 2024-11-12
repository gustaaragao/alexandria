package com.biblioteca.gustavo.alexandria.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyDisabledException extends RuntimeException {
    public AlreadyDisabledException(String message) {
        super(message);
    }
}
