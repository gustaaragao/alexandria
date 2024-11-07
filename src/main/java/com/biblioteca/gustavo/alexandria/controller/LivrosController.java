package com.biblioteca.gustavo.alexandria.controller;

import com.biblioteca.gustavo.alexandria.dto.LivroDTO;
import com.biblioteca.gustavo.alexandria.model.Livro;
import com.biblioteca.gustavo.alexandria.repository.LivroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livros")
public class LivrosController {

    // Injecao de Dependencias
    @Autowired
    private LivroRepository repository;

    @PostMapping
    public void cadastrarLivro(@RequestBody LivroDTO novoLivroDTO) {

        Livro novoLivro = new Livro(novoLivroDTO);

        repository.save(novoLivro);

    }

}
