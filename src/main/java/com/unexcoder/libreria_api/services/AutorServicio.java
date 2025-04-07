package com.unexcoder.libreria_api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unexcoder.libreria_api.entities.Autor;
import com.unexcoder.libreria_api.models.AutorActivoDTO;
import com.unexcoder.libreria_api.models.AutorDTO;
import com.unexcoder.libreria_api.repositories.AutorRepositorio;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AutorServicio {

    private final AutorRepositorio autorRepositorio;

    @Transactional
    public void crearAutor(String nombre) {
        // validar(nombre);
        Autor autor = new Autor();
        autor.setNombre(nombre);
        autor.setActivo(true);
        autorRepositorio.save(autor);
    }

    @Transactional(readOnly = true)
    public List<Autor> listarAutores() {
        List<Autor> autores = new ArrayList<>();
        autores = autorRepositorio.findAll();
        return autores;
    }

    @Transactional(readOnly = true)
    public List<AutorDTO> listarAutoresDTO() {
        return autorRepositorio.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AutorDTO autorById(UUID id) {
        Autor autor = autorRepositorio.findById(id)
                .orElse(null);
        if (autor != null) {
            return convertToDTO(autor);
        }
        return null;
    }

    @Transactional
    public void modificarAutor(UUID id, String nombre) {
        // validar(nombre);
        Optional<Autor> autor = autorRepositorio.findById(id);
        if (autor.isPresent()) {
            Autor a = autor.get();
            a.setNombre(nombre);
            autorRepositorio.save(a);
        }
    }

    @Transactional
    public void modificarAutor(AutorActivoDTO autorActivoDTO) {
        // validar(nombre);
        Optional<Autor> autor = autorRepositorio.findById(autorActivoDTO.getId());
        if (autor.isPresent()) {
            Autor a = autor.get();
            a.setNombre(autorActivoDTO.getNombre());
            a.setActivo(autorActivoDTO.isActivo());
            autorRepositorio.save(a);
        }
    }

    @Transactional
    public void eliminarAutor(UUID id) {
        // validar(nombre);
        Optional<Autor> autor = autorRepositorio.findById(id);
        if (autor.isPresent()) {
            Autor a = autor.get();
            a.setActivo(false);
            autorRepositorio.save(a);
        }
    }

    // hard delete
    @Transactional
    public void eliminarAutorlHard(UUID id) {
        // validar(nombre);
        Optional<Autor> autor = autorRepositorio.findById(id);
        if (autor.isPresent()) {
            Autor a = autor.get();
            autorRepositorio.delete(a);
        }
    }

    // public void validar(String nombre) {
    // if (nombre.isEmpty() || nombre == null) {
    // throw new ValidationException("El campo 'nombre' no puede estar vacío");
    // }
    // }

    @Transactional(readOnly = true)
    public Autor getOne(UUID id) {
        return autorRepositorio.getReferenceById(id);
    }

    // este metodo implemeta el filtro activo/inactivo en el servicio
    // posible implementar consulta especifica en repositorio para optimizar
    @Transactional(readOnly = true)
    public List<Autor> listarAutoresActivos(boolean activo) {
        List<Autor> autores = new ArrayList<>();
        autores = autorRepositorio
                .findAll()
                .stream()
                .filter(autor -> autor.isActivo() == activo)
                .collect(Collectors.toList());
        return autores;
    }


    @Transactional(readOnly = true)
    public List<AutorActivoDTO> findAutorByTxt(String txt) {
        return autorRepositorio.findAutorByText(txt).stream()
                .map(autor -> {
                    AutorActivoDTO dto = new AutorActivoDTO();
                    dto.setId(autor.getId());
                    dto.setNombre(autor.getNombre());
                    dto.setActivo(autor.isActivo());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // private methods
    private AutorDTO convertToDTO(Autor autor) {
        AutorDTO dto = new AutorDTO();
        dto.setId(autor.getId());
        dto.setNombre(autor.getNombre());
        dto.setActivo(autor.isActivo());
        dto.setCreatedAt(autor.getCreatedAt());
        dto.setUpdatedAt(autor.getUpdatedAt());
        return dto;
    }
}
