package com.biblioteca.gustavo.alexandria.controllers;

import com.biblioteca.gustavo.alexandria.dto.LivroUpdateDTO;
import com.biblioteca.gustavo.alexandria.dto.LivroCreateDTO;
import com.biblioteca.gustavo.alexandria.dto.LivroResponseDTO;

import com.biblioteca.gustavo.alexandria.services.LivroService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/livros")
public class LivrosController {

    private final LivroService livroService;

    @Autowired
    public LivrosController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    @Transactional // Rebombinar a aplicacao para antes do erro na requisicao
    public ResponseEntity<Object> createLivro(@RequestBody @Valid LivroCreateDTO livroCreateDTO, UriComponentsBuilder uriBuilder) {
        LivroResponseDTO livroResponseDTO = livroService.createLivro(livroCreateDTO);

        var uri = uriBuilder.path("/livros/{id}").buildAndExpand(livroResponseDTO.id()).toUri();

        return ResponseEntity.created(uri).body(livroResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<LivroResponseDTO>> getLivros(
            @RequestParam(value = "disponivel", required = false)
            Boolean disponivel
    ) {
        List<LivroResponseDTO> livros;

        if (disponivel == null) {
            livros = livroService.getAllLivros();
        } else if (disponivel) {
            livros = livroService.getAllLivrosDisponiveis();
        } else {
            livros = livroService.getAllLivrosIndisponiveis();
        }

        return ResponseEntity.ok(livros);
    }

    @GetMapping("/{idLivro}")
    public ResponseEntity<LivroResponseDTO> getLivroById(@PathVariable Long idLivro) {
        LivroResponseDTO livroResponseDTO = livroService.getLivroById(idLivro);

        return ResponseEntity.ok(livroResponseDTO);
    }

    @PutMapping("{idLivro}")
    @Transactional
    public ResponseEntity<LivroResponseDTO> updateLivro(
            @PathVariable Long idLivro,
            @RequestBody @Valid LivroUpdateDTO livroUpdateDTO
    ) {
        LivroResponseDTO livroResponseDTO = livroService.updateLivro(idLivro, livroUpdateDTO);

        return ResponseEntity.ok(livroResponseDTO);
    }

    @DeleteMapping("/{idLivro}")
    @Transactional
    public ResponseEntity<Void> deleteLivro(@PathVariable Long idLivro) {
        livroService.deleteLivro(idLivro);

        return ResponseEntity.noContent().build(); // .build() permite a criação de uma entidade sem body
    }

    @DeleteMapping("disable/{idLivro}")
    @Transactional
    public ResponseEntity<Void> disableLivro(@PathVariable Long idLivro) {
        livroService.disableLivro(idLivro);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("enable/{idLivro}")
    @Transactional
    public ResponseEntity<Void> enableLivro(@PathVariable Long idLivro) {
        livroService.enableLivro(idLivro);

        return ResponseEntity.ok().build();
    }
}
