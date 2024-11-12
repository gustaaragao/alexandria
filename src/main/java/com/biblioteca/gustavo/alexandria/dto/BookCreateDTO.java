package com.biblioteca.gustavo.alexandria.dto;

import java.time.LocalDate;

import com.biblioteca.gustavo.alexandria.enums.GenreEnum;
import com.biblioteca.gustavo.alexandria.enums.PublisherEnum;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;

public record BookCreateDTO(
        @NotBlank String name,
        @NotBlank
        String author,
        @Enumerated GenreEnum genre,
        @Enumerated PublisherEnum publisher,
        @PositiveOrZero int quantity,
        @PastOrPresent LocalDate releaseDate) {
}
