package com.biblioteca.gustavo.alexandria.dto;

import java.time.LocalDate;

import com.biblioteca.gustavo.alexandria.enums.GenreEnum;
import com.biblioteca.gustavo.alexandria.enums.PublisherEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;

public record BookCreateDTO(
        @NotBlank
        String name,
        
        @NotBlank
        String author,
        
        // TODO: Melhorar a validação de Enums.
        // Em si, não retorna 400 e manda uma mensagem de erro não muito amigável e segura, mostrando os pacotes e classes.
        GenreEnum genre,

        PublisherEnum publisher,
        
        @PositiveOrZero
        int quantity,
        
        @PastOrPresent
        LocalDate releaseDate
) {
}
