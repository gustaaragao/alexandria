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
    public ResponseEntity<List<ResponseLivroDTO>> listarLivros() {
        var listaLivros = repository.findAllByAtivoTrue().stream().map(ResponseLivroDTO::new).toList();

        return ResponseEntity.ok(listaLivros);
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

        return ResponseEntity.noContent().build(); // .build() permite a criação de uma entidade sem body
    }
    
    @PutMapping("reativar/{id}")
    @Transactional
    public ResponseEntity<Void> reativarLivro(@PathVariable Long id) {
        var livroReferencia = repository.getReferenceById(id);
        
        livroReferencia.ativar();

        return ResponseEntity.ok().build(); // .build() permite a criação de uma entidade sem body
    }
}
