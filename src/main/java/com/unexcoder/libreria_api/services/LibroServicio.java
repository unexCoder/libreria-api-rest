package com.unexcoder.libreria_api.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.unexcoder.libreria_api.entities.Autor;
import com.unexcoder.libreria_api.entities.Editorial;
import com.unexcoder.libreria_api.entities.Libro;
import com.unexcoder.libreria_api.models.LibroActivoDTO;
import com.unexcoder.libreria_api.models.LibroDTO;
import com.unexcoder.libreria_api.projections.LibroActivo;
import com.unexcoder.libreria_api.repositories.LibroRepositorio;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LibroServicio {

    private final LibroRepositorio libroRepositorio;
    private final AutorServicio autorServicio;
    private final EditorialServicio editorialServicio;

    @Transactional
    public void crearLibroDTO(LibroDTO libroDTO) {

        Libro libro = new Libro();
        libro.setIsbn(libroDTO.getIsbn());
        libro.setTitulo(libroDTO.getTitulo());
        libro.setEjemplares(libroDTO.getEjemplares());
        libro.setActivo(libroDTO.isActivo());
        Autor autor = autorServicio.getOne(UUID.fromString(libroDTO.getIdAutor()));
        if (autor != null) {
            libro.setAutor(autor);
        }
        Editorial editorial = editorialServicio.getOne(UUID.fromString(libroDTO.getIdEditorial()));
        if (editorial != null) {
            libro.setEditorial(editorial);
        }
        libroRepositorio.save(libro);
    }

    @Transactional(readOnly = true)
    public List<LibroDTO> listar() {
        return libroRepositorio.findAll().stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<LibroActivo> listarActivos() {
        return libroRepositorio.listarActivos();
    }

    @Transactional(readOnly = true)
    public List<LibroActivoDTO> listarActivosDTO() {
        return libroRepositorio.listarActivosDTO();
    }

    @Transactional(readOnly = true)
    public LibroDTO libroByIsbn(Long isbn) {
        Libro libro = libroRepositorio.findById(isbn)
            .orElse(null);
        if (libro != null) {
            return convertToDTO(libro);
        }
        return null;
    }

    // private methods
    private LibroDTO convertToDTO(Libro libro) {
        LibroDTO dto = new LibroDTO();
        dto.setIsbn(libro.getIsbn());
        dto.setTitulo(libro.getTitulo());
        dto.setActivo(libro.isActivo());
        dto.setEjemplares(libro.getEjemplares());
        dto.setIdAutor(libro.getAutor().getNombre());
        dto.setIdEditorial(libro.getEditorial().getNombre());
        return dto;
    }
}
