package com.biblioteca.gustavo.alexandria.services;


import com.biblioteca.gustavo.alexandria.dto.BookCreateDTO;
import com.biblioteca.gustavo.alexandria.dto.BookResponseDTO;
import com.biblioteca.gustavo.alexandria.dto.BookUpdateDTO;
import com.biblioteca.gustavo.alexandria.exceptions.BookAlreadyDisabledException;
import com.biblioteca.gustavo.alexandria.exceptions.BookAlreadyEnabledException;
import com.biblioteca.gustavo.alexandria.exceptions.BookNotFoundException;
import com.biblioteca.gustavo.alexandria.model.Book;
import com.biblioteca.gustavo.alexandria.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookResponseDTO createBook(BookCreateDTO bookDTO) {
        Book book = new Book(bookDTO);

        bookRepository.save(book);

        return new BookResponseDTO(book);
    }

    public List<BookResponseDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();

        return books.stream().map(BookResponseDTO::new).toList();
    }

    public List<BookResponseDTO> getAllAvailableBooks() {
        List<Book> books = bookRepository.findAllByAvailableTrue();

        return books.stream().map(BookResponseDTO::new).toList();
    }

    public List<BookResponseDTO> getAllUnavailableBooks() {
        List<Book> books = bookRepository.findAllByAvailableFalse();

        return books.stream().map(BookResponseDTO::new).toList();
    }

    public BookResponseDTO getBookById(Long idBook) throws BookNotFoundException {
        Optional<Book> bookOptional = bookRepository.findById(idBook);

        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();

            return new BookResponseDTO(book);
        }

        throw new BookNotFoundException(idBook);
    }

    public BookResponseDTO updateBook(Long idBook, BookUpdateDTO bookUpdateDTO) throws BookNotFoundException {

        Optional<Book> bookOptional = bookRepository.findById(idBook);

        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();

            book.updateInformation(bookUpdateDTO);

            return new BookResponseDTO(book);
        }

        throw new BookNotFoundException(idBook);
    }

    public void deleteBook(Long idBook) throws BookNotFoundException {
        if (!bookRepository.existsById(idBook)) {
            throw new BookNotFoundException(idBook);
        }

        bookRepository.deleteById(idBook);
    }

    public void disableBook(Long idBook) throws BookNotFoundException, BookAlreadyDisabledException {
        if (!bookRepository.existsById(idBook)) {
            throw new BookNotFoundException(idBook);
        }

        Book book = bookRepository.findById(idBook).get();

        if (!book.isAvailable()) {
            throw new BookAlreadyDisabledException("Book with id " + idBook + " is already disabled");
        }

        book.setAvailable(false);
    }

    public void enableBook(Long idBook) throws BookNotFoundException, BookAlreadyEnabledException {
        if (!bookRepository.existsById(idBook)) {
            throw new BookNotFoundException(idBook);
        }

        Book book = bookRepository.findById(idBook).get();

        if (book.isAvailable()) {
            throw new BookAlreadyEnabledException("Book with id " + idBook + " is already enabled");
        }

        book.setAvailable(true);
    }

}