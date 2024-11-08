package com.biblioteca.gustavo.alexandria.dto;

import com.biblioteca.gustavo.alexandria.enums.EditoraEnum;
import com.biblioteca.gustavo.alexandria.enums.GeneroEnum;

public record LivroDTO(
    String nome,
    GeneroEnum genero,
    String autor,
    EditoraEnum editora
) {
}
