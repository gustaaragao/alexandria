package com.biblioteca.gustavo.alexandria.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.gustavo.alexandria.livro.LivroDTO;

@RestController
@RequestMapping("/livros")
public class LivrosController {
    
    @PostMapping
    public void cadastrar(@RequestBody LivroDTO dados) {
        System.out.println(dados);
    }

}
