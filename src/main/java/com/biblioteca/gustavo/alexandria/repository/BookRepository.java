package com.biblioteca.gustavo.alexandria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.gustavo.alexandria.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByAvailableTrue();

    List<Book> findAllByAvailableFalse();
}
