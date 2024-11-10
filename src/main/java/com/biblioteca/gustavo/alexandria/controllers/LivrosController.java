package com.biblioteca.gustavo.alexandria.controllers;

import com.biblioteca.gustavo.alexandria.dto.DadosAtualizarLivroDTO;
import com.biblioteca.gustavo.alexandria.dto.DadosCadastroLivroDTO;
import com.biblioteca.gustavo.alexandria.dto.ResponseLivroDTO;
import com.biblioteca.gustavo.alexandria.model.Livro;
import com.biblioteca.gustavo.alexandria.repository.LivroRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/livros")
public class LivrosController {

    // Injecao de Dependencias
    @Autowired
    private LivroRepository repository;

    @PostMapping
    @Transactional // Rebombinar a aplicacao para antes do erro na requisicao
    public ResponseEntity<Object> cadastrarLivro(
        @RequestBody @Valid DadosCadastroLivroDTO novoLivroDTO,
        UriComponentsBuilder uriBuilder
    ) {
        Livro novoLivro = new Livro(novoLivroDTO);
        repository.save(novoLivro);

        // A classe UriComponentsBuilder encapsula toda a lógica para gerar URIs. (O problema aqui é localhost vs deploy)
        // buildAndExpand serve para gerar de maneira dinâmica o {id}
        var uri = uriBuilder.path("/livros/{id}").buildAndExpand(novoLivro.getId()).toUri();

        return ResponseEntity.created(uri).body(new ResponseLivroDTO(novoLivro));
    }

    @GetMapping
    public ResponseEntity<List<ResponseLivroDTO>> listarLivros() {
        var listaLivros = repository.findAllByAtivoTrue().stream().map(ResponseLivroDTO::new).toList();

        return ResponseEntity.ok(listaLivros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseLivroDTO> buscarLivroPorId(@PathVariable Long id) {
        var livro = repository.getReferenceById(id);

        return ResponseEntity.ok(new ResponseLivroDTO(livro));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ResponseLivroDTO> atualizarLivro(@RequestBody @Valid DadosAtualizarLivroDTO novosDadosLivroDTO) {
        // Pegar a referência para esse Livro que será atualizado
        var livroReferencia = repository.getReferenceById(novosDadosLivroDTO.id());

        livroReferencia.atualizarInformacoes(novosDadosLivroDTO);

        return ResponseEntity.ok(new ResponseLivroDTO(livroReferencia));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluirLivro(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build(); // .build() permite a criação de uma entidade sem body
    }
    
    @DeleteMapping("inativar/{id}")
    @Transactional
    public ResponseEntity<Void> inativarLivro(@PathVariable Long id) {
        var livroReferencia = repository.getReferenceById(id);
        
        livroReferencia.inativar();

        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("reativar/{id}")
    @Transactional
    public ResponseEntity<Void> reativarLivro(@PathVariable Long id) {
        var livroReferencia = repository.getReferenceById(id);
        
        livroReferencia.ativar();

        return ResponseEntity.ok().build();
    }
}
