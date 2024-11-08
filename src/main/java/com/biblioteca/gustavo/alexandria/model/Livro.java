package com.biblioteca.gustavo.alexandria.model;

import com.biblioteca.gustavo.alexandria.dto.LivroDTO;
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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "livros")
@Entity(name = "livros")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Livro {

	public Livro(LivroDTO novoLivroDTO) {
		this.nome = novoLivroDTO.nome();
		this.genero = novoLivroDTO.genero();
		this.autor = novoLivroDTO.autor();
		this.editora = novoLivroDTO.editora();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@Enumerated(EnumType.STRING)
	private GeneroEnum genero;
	private String autor;
	@Enumerated(EnumType.STRING)
	private EditoraEnum editora;
}