package com.biblioteca.gustavo.alexandria.services;

import com.biblioteca.gustavo.alexandria.dto.LivroUpdateDTO;
import com.biblioteca.gustavo.alexandria.dto.LivroCreateDTO;
import com.biblioteca.gustavo.alexandria.dto.LivroResponseDTO;
import com.biblioteca.gustavo.alexandria.exceptions.AlreadyDisabledException;
import com.biblioteca.gustavo.alexandria.exceptions.AlreadyEnabledException;
import com.biblioteca.gustavo.alexandria.exceptions.ResourceNotFoundException;
import com.biblioteca.gustavo.alexandria.model.Livro;
import com.biblioteca.gustavo.alexandria.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    @Autowired
    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public LivroResponseDTO createLivro(LivroCreateDTO livroCreateDTO) {
        Livro livro = new Livro(livroCreateDTO);

        livroRepository.save(livro);

        return new LivroResponseDTO(livro);
    }

    public List<LivroResponseDTO> getAllLivros() {
        List<Livro> livros = livroRepository.findAll();

        return livros.stream().map(LivroResponseDTO::new).toList();
    }

    public List<LivroResponseDTO> getAllLivrosDisponiveis() {
        List<Livro> livros = livroRepository.findAllByDisponivelTrue();

        return livros.stream().map(LivroResponseDTO::new).toList();
    }

    public List<LivroResponseDTO> getAllLivrosIndisponiveis() {
        List<Livro> livros = livroRepository.findAllByDisponivelFalse();

        return livros.stream().map(LivroResponseDTO::new).toList();
    }

    public LivroResponseDTO getLivroById(Long idLivro) throws ResourceNotFoundException {
        Optional<Livro> livroOptional = livroRepository.findById(idLivro);

        if (livroOptional.isPresent()) {
            Livro livro = livroOptional.get();

            return new LivroResponseDTO(livro);
        }

        throw new ResourceNotFoundException("Livro não encontrado com o id: " + idLivro);
    }

    public LivroResponseDTO updateLivro(Long idLivro, LivroUpdateDTO livroUpdateDTO) throws ResourceNotFoundException {

        Optional<Livro> livroOptional = livroRepository.findById(idLivro);

        if (livroOptional.isPresent()) {
            Livro livro = livroOptional.get();

            livro.atualizarInformacoes(livroUpdateDTO);
        }

        throw new ResourceNotFoundException("Livro não encontrado com o id: " + idLivro);
    }

    public void deleteLivro(Long idLivro) throws ResourceNotFoundException {
        if (!livroRepository.existsById(idLivro)) {
            throw new ResourceNotFoundException("Livro não encontrado com o id: " + idLivro);
        }

        livroRepository.deleteById(idLivro);
    }

    public void disableLivro(Long idLivro) throws ResourceNotFoundException, AlreadyDisabledException {
        if (!livroRepository.existsById(idLivro)) {
            throw new ResourceNotFoundException("Livro não encontrado com o id: " + idLivro);
        }

        Livro livro = livroRepository.findById(idLivro).get();

        if (!livro.isDisponivel()) {
            throw new AlreadyDisabledException("Livro com o id " + idLivro + " já está desabilitado");
        }

        livro.setDisponivel(false);
    }

    public void enableLivro(Long idLivro) throws ResourceNotFoundException, AlreadyEnabledException {
        if (!livroRepository.existsById(idLivro)) {
            throw new ResourceNotFoundException("Livro não encontrado com o id: " + idLivro);
        }

        Livro livro = livroRepository.findById(idLivro).get();

        if (livro.isDisponivel()) {
            throw new AlreadyEnabledException("Livro com o id " + idLivro + " já está habilitado");
        }

        livro.setDisponivel(true);
    }

}