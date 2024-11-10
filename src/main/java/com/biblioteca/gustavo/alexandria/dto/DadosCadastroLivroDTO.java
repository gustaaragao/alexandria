package com.biblioteca.gustavo.alexandria.dto;

import java.time.LocalDate;

import com.biblioteca.gustavo.alexandria.enums.EditoraEnum;
import com.biblioteca.gustavo.alexandria.enums.GeneroEnum;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;

public record DadosCadastroLivroDTO(
        @NotBlank String nome,
        @Enumerated GeneroEnum genero,
        @NotBlank String autor,
        @Enumerated EditoraEnum editora,
        @PositiveOrZero int quantidade,
        @PastOrPresent LocalDate lancamento) {
}