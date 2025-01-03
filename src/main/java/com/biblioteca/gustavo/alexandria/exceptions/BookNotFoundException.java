package com.biblioteca.gustavo.alexandria.exceptions;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(String message) {
        super(message);
    }

    public BookNotFoundException() {
        super("Book not found.");
    }

    public BookNotFoundException(Long idBook) {
        super("Book not found with id: " + idBook + ".");
    }
}