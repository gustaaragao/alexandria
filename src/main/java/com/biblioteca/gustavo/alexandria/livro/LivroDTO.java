package com.biblioteca.gustavo.alexandria.livro;

public record LivroDTO(
    String nome,
    Genero genero,
    String autor,
    String dataLancamento,
    int numeroPaginas,
    int quantidade,
    Editora editora
) {
}
