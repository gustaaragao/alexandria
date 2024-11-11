package com.biblioteca.gustavo.alexandria.dto;

import java.time.LocalDate;

import com.biblioteca.gustavo.alexandria.enums.EditoraEnum;
import com.biblioteca.gustavo.alexandria.enums.GeneroEnum;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;

public record LivroCriarDTO(
        @NotBlank String nome,
        @Enumerated GeneroEnum genero,
        @NotBlank(message="O campo 'autor' não deve estar em branco.")
        String autor,
        @Enumerated EditoraEnum editora,
        @PositiveOrZero int quantidade,
        @PastOrPresent LocalDate lancamento) {
}
