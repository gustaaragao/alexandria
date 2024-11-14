package com.biblioteca.gustavo.alexandria.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BookAlreadyDisabledException extends RuntimeException {
    public BookAlreadyDisabledException(String message) {
        super(message);
    }

    public BookAlreadyDisabledException(Long idBook) {
        super("Book with id " + idBook + " is already disabled.");
    }

    public BookAlreadyDisabledException() {
        super("Book is already disabled.");
    }
}
