package com.biblioteca.gustavo.alexandria.model;

import java.time.LocalDate;

import com.biblioteca.gustavo.alexandria.dto.LivroAtualizarDTO;
import com.biblioteca.gustavo.alexandria.dto.LivroCriarDTO;
import com.biblioteca.gustavo.alexandria.enums.EditoraEnum;
import com.biblioteca.gustavo.alexandria.enums.GeneroEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "livros")
@Entity(name = "livros")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Livro {

	public Livro(LivroCriarDTO novoLivroDTO) {
		this.nome = novoLivroDTO.nome();
		this.genero = novoLivroDTO.genero();
		this.autor = novoLivroDTO.autor();
		this.editora = novoLivroDTO.editora();
		this.lancamento = novoLivroDTO.lancamento();
		this.quantidade = novoLivroDTO.quantidade();
		this.disponivel = true;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String autor;
	
	@Enumerated(EnumType.STRING)
	private GeneroEnum genero;
	
	@Enumerated(EnumType.STRING)
	private EditoraEnum editora;

	private LocalDate lancamento;
	private int quantidade;
	private boolean disponivel;

	public void atualizarInformacoes(LivroAtualizarDTO novosDados) {
		if (novosDados.nome() != null)
			this.nome = novosDados.nome();
		if (novosDados.genero() != null)
			this.genero = novosDados.genero();
		if (novosDados.autor() != null)
			this.autor = novosDados.autor();
		if (novosDados.editora() != null)
			this.editora = novosDados.editora();
		if (novosDados.lancamento() != null)
			this.lancamento = novosDados.lancamento();
	}
}