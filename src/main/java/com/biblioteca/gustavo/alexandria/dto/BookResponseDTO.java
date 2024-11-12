package com.biblioteca.gustavo.alexandria.dto;

import java.time.LocalDate;

import com.biblioteca.gustavo.alexandria.enums.GenreEnum;
import com.biblioteca.gustavo.alexandria.enums.PublisherEnum;
import com.biblioteca.gustavo.alexandria.model.Book;

public record BookResponseDTO(
    Long id, 
	String name, 
	String author, 
	GenreEnum genre, 
	PublisherEnum publisher, 
	LocalDate releaseDate,
    int quantity,
    Boolean available
) {
    
    public BookResponseDTO(Book book) {
        this(
            book.getId(), 
            book.getName(), 
            book.getAuthor(), 
            book.getGenre(), 
            book.getPublisher(), 
            book.getReleaseDate(),
            book.getQuantity(), 
            book.isAvailable()
        );
    }

}
