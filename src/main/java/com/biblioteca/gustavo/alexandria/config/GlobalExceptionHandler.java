package com.biblioteca.gustavo.alexandria.config;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.biblioteca.gustavo.alexandria.dto.ApiErrorDTO;
import com.biblioteca.gustavo.alexandria.exceptions.BookNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ApiErrorDTO>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var fieldErrors = ex.getFieldErrors();

        var body = fieldErrors.stream()
                .map(error -> new ApiErrorDTO(HttpStatus.BAD_REQUEST, error))
                .toList();

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorDTO> handleGenericException(Exception ex) {
        var apiError = new ApiErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno no servidor");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ApiErrorDTO> handleBookNotFoundException() {
        var apiError = new ApiErrorDTO(HttpStatus.NOT_FOUND, "Livro não encontrado");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

}
