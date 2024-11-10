package com.biblioteca.gustavo.alexandria.controller;

import com.biblioteca.gustavo.alexandria.dto.DadosAtualizarLivroDTO;
import com.biblioteca.gustavo.alexandria.dto.DadosCadastroLivroDTO;
import com.biblioteca.gustavo.alexandria.dto.DadosListagemLivrosDTO;
import com.biblioteca.gustavo.alexandria.model.Livro;
import com.biblioteca.gustavo.alexandria.repository.LivroRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    @Transactional // Rebombinar a aplicacao para antes do erro na requisicao
    public void cadastrarLivro(@RequestBody @Valid DadosCadastroLivroDTO novoLivroDTO) {

        Livro novoLivro = new Livro(novoLivroDTO);

        repository.save(novoLivro);

    }

    @GetMapping
    public List<DadosListagemLivrosDTO> listarLivros() {
        return repository.findAll().stream().map(DadosListagemLivrosDTO::new).toList();
    }

    @PutMapping
    @Transactional
    public void atualizarLivro(@RequestBody @Valid DadosAtualizarLivroDTO novosDadosLivroDTO) {
        // Pegar a referência para esse Livro que será atualizado
        var livroReferencia = repository.getReferenceById(novosDadosLivroDTO.id());
    
        livroReferencia.atualizarInformacoes(novosDadosLivroDTO);
    }
}
