package com.biblioteca.gustavo.alexandria.dto;

import java.time.LocalDate;

import com.biblioteca.gustavo.alexandria.enums.GenreEnum;
import com.biblioteca.gustavo.alexandria.enums.PublisherEnum;

// TODO: Como funciona a validação de campos como esses? Nem todos devem ser obrigatórios. Porém, por exemplo, caso eu passe o name ele deve ser
// NotBlank.
// Enfim, como eu faço validação de PUTs (Ou será que aqui deveria ser um PATCH).
public record BookUpdateDTO(
    String name,

    String author,

    GenreEnum genre,

    PublisherEnum publisher,

    LocalDate releaseDate
) {
}
