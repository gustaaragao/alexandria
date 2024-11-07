package com.biblioteca.gustavo.alexandria.DTO;

import com.biblioteca.gustavo.alexandria.enums.EditoraEnum;
import com.biblioteca.gustavo.alexandria.enums.GeneroEnum;

public record LivroDTO(
    String nome,
    GeneroEnum genero,
    String autor,
    String dataLancamento,
    int numeroPaginas,
    int quantidade,
    EditoraEnum editora
) {
}
