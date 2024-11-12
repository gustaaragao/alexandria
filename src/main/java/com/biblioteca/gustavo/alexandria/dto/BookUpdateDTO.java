package com.biblioteca.gustavo.alexandria.dto;

import java.time.LocalDate;

import com.biblioteca.gustavo.alexandria.enums.GenreEnum;
import com.biblioteca.gustavo.alexandria.enums.PublisherEnum;

public record BookUpdateDTO(
    String name,
    GenreEnum genre,
    String author,
    PublisherEnum publisher,
    LocalDate releaseDate
) {
}
