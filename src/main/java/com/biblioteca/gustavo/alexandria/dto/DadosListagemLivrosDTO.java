package com.biblioteca.gustavo.alexandria.dto;

import java.time.LocalDate;

import com.biblioteca.gustavo.alexandria.enums.EditoraEnum;
import com.biblioteca.gustavo.alexandria.enums.GeneroEnum;
import com.biblioteca.gustavo.alexandria.model.Livro;

public record DadosListagemLivrosDTO(String nome, GeneroEnum genero, String autor, EditoraEnum editora, LocalDate lancamento) {
	
	public DadosListagemLivrosDTO(Livro livro) {
		this(livro.getNome(), livro.getGenero(), livro.getAutor(), livro.getEditora(), livro.getLancamento());
	}

}
