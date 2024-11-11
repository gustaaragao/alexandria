package com.biblioteca.gustavo.alexandria.dto;

import java.time.LocalDate;

import com.biblioteca.gustavo.alexandria.enums.EditoraEnum;
import com.biblioteca.gustavo.alexandria.enums.GeneroEnum;
import com.biblioteca.gustavo.alexandria.model.Livro;

public record LivroResponseDTO(
    Long id, 
	String nome, 
	GeneroEnum genero, 
	String autor, 
	EditoraEnum editora, 
	LocalDate lancamento,
    int quantidade,
    Boolean disponivel
) {
    
    public LivroResponseDTO(Livro livro) {
        this(
            livro.getId(), livro.getNome(), livro.getGenero(), livro.getAutor(), 
            livro.getEditora(), livro.getLancamento(), livro.getQuantidade(), livro.isDisponivel()
        );
    }

}
