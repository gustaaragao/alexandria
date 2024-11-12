package com.biblioteca.gustavo.alexandria.services;


import com.biblioteca.gustavo.alexandria.dto.BookCreateDTO;
import com.biblioteca.gustavo.alexandria.dto.BookResponseDTO;
import com.biblioteca.gustavo.alexandria.dto.BookUpdateDTO;
import com.biblioteca.gustavo.alexandria.exceptions.AlreadyDisabledException;
import com.biblioteca.gustavo.alexandria.exceptions.AlreadyEnabledException;
import com.biblioteca.gustavo.alexandria.exceptions.ResourceNotFoundException;
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

    public BookResponseDTO getBookById(Long idBook) throws ResourceNotFoundException {
        Optional<Book> bookOptional = bookRepository.findById(idBook);

        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();

            return new BookResponseDTO(book);
        }

        throw new ResourceNotFoundException("Book not found with id: " + idBook);
    }

    public BookResponseDTO updateBook(Long idBook, BookUpdateDTO bookUpdateDTO) throws ResourceNotFoundException {

        Optional<Book> bookOptional = bookRepository.findById(idBook);

        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();

            book.updateInformation(bookUpdateDTO);

            return new BookResponseDTO(book);
        }

        throw new ResourceNotFoundException("Book not found with id: " + idBook);
    }

    public void deleteBook(Long idBook) throws ResourceNotFoundException {
        if (!bookRepository.existsById(idBook)) {
            throw new ResourceNotFoundException("Book not found with id: " + idBook);
        }

        bookRepository.deleteById(idBook);
    }

    public void disableBook(Long idBook) throws ResourceNotFoundException, AlreadyDisabledException {
        if (!bookRepository.existsById(idBook)) {
            throw new ResourceNotFoundException("Book not found with id: " + idBook);
        }

        Book book = bookRepository.findById(idBook).get();

        if (!book.isAvailable()) {
            throw new AlreadyDisabledException("Book with id " + idBook + " is already disabled");
        }

        book.setAvailable(false);
    }

    public void enableBook(Long idBook) throws ResourceNotFoundException, AlreadyEnabledException {
        if (!bookRepository.existsById(idBook)) {
            throw new ResourceNotFoundException("Book not found with id: " + idBook);
        }

        Book book = bookRepository.findById(idBook).get();

        if (book.isAvailable()) {
            throw new AlreadyEnabledException("Book with id " + idBook + " is already enabled");
        }

        book.setAvailable(true);
    }

}