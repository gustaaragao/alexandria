package com.biblioteca.gustavo.alexandria.model;

import java.time.LocalDate;

import com.biblioteca.gustavo.alexandria.dto.BookCreateDTO;
import com.biblioteca.gustavo.alexandria.dto.BookUpdateDTO;
import com.biblioteca.gustavo.alexandria.enums.GenreEnum;
import com.biblioteca.gustavo.alexandria.enums.PublisherEnum;

import jakarta.persistence.Column;
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

@Table(name = "books")
@Entity(name = "Book")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String author;

	@Enumerated(EnumType.STRING)
	private GenreEnum genre;

	@Enumerated(EnumType.STRING)
	private PublisherEnum publisher;

	@Column(name = "release_date")
	private LocalDate releaseDate;
	
	private int quantity;
	private boolean available;

	public Book(BookCreateDTO bookDTO) {
		this.name = bookDTO.name();
		this.genre = bookDTO.genre();
		this.author = bookDTO.author();
		this.publisher = bookDTO.publisher();
		this.releaseDate = bookDTO.releaseDate();
		this.quantity = bookDTO.quantity();
		this.available = true;
	}

	public void updateInformation(BookUpdateDTO bookDTO) {
		if (bookDTO.name() != null)
			this.name = bookDTO.name();
		if (bookDTO.genre() != null)
			this.genre = bookDTO.genre();
		if (bookDTO.author() != null)
			this.author = bookDTO.author();
		if (bookDTO.publisher() != null)
			this.publisher = bookDTO.publisher();
		if (bookDTO.releaseDate() != null)
			this.releaseDate = bookDTO.releaseDate();
	}
}