package com.unexcoder.libreria_api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.unexcoder.libreria_api.entities.Editorial;
import com.unexcoder.libreria_api.repositories.EditorialRepositorio;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EditorialServicio {

    private final EditorialRepositorio editorialRepositorio;

    @Transactional
    public void crearEditorial(String nombre) {
        // validar(nombre);
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        editorial.setActiva(true);
        editorialRepositorio.save(editorial);
    }
    
    @Transactional(readOnly = true)
    public List<Editorial> listarEditoriales() {
        List<Editorial> editoriales = new ArrayList<>();
        editoriales = editorialRepositorio.findAll();
        return editoriales;
    }
    
    @Transactional
    public void modificarEditorial(UUID id,String nombre) {
        // validar(nombre);
        Optional<Editorial> editorial = editorialRepositorio.findById(id);
        if (editorial.isPresent()) {
            Editorial e = editorial.get();
            e.setNombre(nombre);
            editorialRepositorio.save(e);
        }
    }
 
    @Transactional
    public void eliminarEditorial(UUID id) {
        // validar(nombre);
        Optional<Editorial> editorial = editorialRepositorio.findById(id);
        if (editorial.isPresent()) {
            Editorial e = editorial.get();
            e.setActiva(false);
            editorialRepositorio.save(e);
        }
    }

    // public void validar(String nombre) {
    //     if (nombre.isEmpty() || nombre == null) {
    //         throw new ValidationException("El campo 'nombre' no puede estar vacío");
    //     }
    // }

    @Transactional(readOnly = true)
    public Editorial getOne(UUID id) {
        return editorialRepositorio.getReferenceById(id);
    }

    @Transactional(readOnly = true)
    public List<Editorial> listarEditorialesActivas(boolean activa) {
        List<Editorial> editoriales = new ArrayList<>();
        editoriales = editorialRepositorio
            .findAll()
            .stream()
            .filter(editorial -> editorial.isActiva() == activa)
            .collect(Collectors.toList());
        return editoriales;
    }
}
