package com.biblioteca.gustavo.alexandria.dto;

import java.time.LocalDate;

import com.biblioteca.gustavo.alexandria.enums.EditoraEnum;
import com.biblioteca.gustavo.alexandria.enums.GeneroEnum;

import jakarta.validation.constraints.NotNull;

public record LivroUpdateDTO(
    String nome,
    GeneroEnum genero,
    String autor,
    EditoraEnum editora,
    LocalDate lancamento
) {

}
