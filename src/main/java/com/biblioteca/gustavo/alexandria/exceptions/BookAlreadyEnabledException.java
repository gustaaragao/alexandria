package com.biblioteca.gustavo.alexandria.exceptions;

public class BookAlreadyEnabledException extends RuntimeException {
    public BookAlreadyEnabledException(String message) {
        super(message);
    }
}
