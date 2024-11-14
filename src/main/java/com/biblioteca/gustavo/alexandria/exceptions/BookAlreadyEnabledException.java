package com.biblioteca.gustavo.alexandria.exceptions;

public class BookAlreadyEnabledException extends RuntimeException {
    public BookAlreadyEnabledException(String message) {
        super(message);
    }

    public BookAlreadyEnabledException(Long idBook) {
        super("Book with id " + idBook + " is already enabled.");
    }

    public BookAlreadyEnabledException() {
        super("Book is already enabled.");
    }
}
