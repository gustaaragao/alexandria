package com.biblioteca.gustavo.alexandria.dto;

import java.time.LocalDate;

import com.biblioteca.gustavo.alexandria.enums.EditoraEnum;
import com.biblioteca.gustavo.alexandria.enums.GeneroEnum;

import jakarta.validation.constraints.NotNull;

public record LivroAtualizarDTO(
    @NotNull
    Long id, // Apenas esse campo é obrigatório, por causa do @NotNull
    String nome,
    GeneroEnum genero,
    String autor,
    EditoraEnum editora,
    LocalDate lancamento
) {

}
