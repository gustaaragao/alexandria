package com.biblioteca.gustavo.alexandria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.gustavo.alexandria.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findAllByAtivoTrue();
    
}
