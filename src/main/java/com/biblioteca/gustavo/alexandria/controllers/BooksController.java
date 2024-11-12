package com.biblioteca.gustavo.alexandria.controllers;

import com.biblioteca.gustavo.alexandria.dto.BookCreateDTO;
import com.biblioteca.gustavo.alexandria.dto.BookResponseDTO;
import com.biblioteca.gustavo.alexandria.dto.BookUpdateDTO;

import com.biblioteca.gustavo.alexandria.services.BookService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/books")
public class BooksController {

    private final BookService bookService;

    @Autowired
    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Object> createBook(@RequestBody @Valid BookCreateDTO bookCreateDTO, UriComponentsBuilder uriBuilder) {
        BookResponseDTO bookResponseDTO = bookService.createBook(bookCreateDTO);

        var uri = uriBuilder.path("/books/{id}").buildAndExpand(bookResponseDTO.id()).toUri();

        return ResponseEntity.created(uri).body(bookResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> getBooks(
            @RequestParam(value = "available", required = false)
            Boolean available
    ) {
        List<BookResponseDTO> books;

        if (available == null) {
            books = bookService.getAllBooks();
        } else if (available) {
            books = bookService.getAllAvailableBooks();
        } else {
            books = bookService.getAllUnavailableBooks();
        }

        return ResponseEntity.ok(books);
    }

    @GetMapping("/{idBook}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable Long idBook) {
        BookResponseDTO bookResponseDTO = bookService.getBookById(idBook);

        return ResponseEntity.ok(bookResponseDTO);
    }

    @PutMapping("{idBook}")
    @Transactional
    public ResponseEntity<BookResponseDTO> updateBook(
            @PathVariable Long idBook,
            @RequestBody @Valid BookUpdateDTO bookUpdateDTO
    ) {
        BookResponseDTO bookResponseDTO = bookService.updateBook(idBook, bookUpdateDTO);

        return ResponseEntity.ok(bookResponseDTO);
    }

    @DeleteMapping("/{idBook}")
    @Transactional
    public ResponseEntity<Void> deleteBook(@PathVariable Long idBook) {
        bookService.deleteBook(idBook);

        return ResponseEntity.noContent().build(); // .build() permite a criação de uma entidade sem body
    }

    @DeleteMapping("disable/{idBook}")
    @Transactional
    public ResponseEntity<Void> disableBook(@PathVariable Long idBook) {
        bookService.disableBook(idBook);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("enable/{idBook}")
    @Transactional
    public ResponseEntity<Void> enableBook(@PathVariable Long idBook) {
        bookService.enableBook(idBook);

        return ResponseEntity.ok().build();
    }
}
