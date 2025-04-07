package com.unexcoder.libreria_api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unexcoder.libreria_api.entities.Editorial;
import com.unexcoder.libreria_api.models.EditorialDTO;
import com.unexcoder.libreria_api.models.EditorialDetailDTO;
// import com.unexcoder.libreria_api.models.EditorialDetailDTO;
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
    
    @Transactional
    public void crearEditorial(EditorialDTO editorialDTO) {
        // validar(nombre);
        Editorial editorial = new Editorial();
        editorial.setNombre(editorialDTO.getNombre());
        editorial.setActiva(true);
        editorialRepositorio.save(editorial);
    }

    @Transactional(readOnly = true)
    public List<Editorial> listarEditoriales() {
        List<Editorial> editoriales = new ArrayList<>();
        editoriales = editorialRepositorio.findAll();
        return editoriales;
    }

    @Transactional(readOnly = true)
    public List<EditorialDTO> listarEditorialesDTO() {
        return editorialRepositorio.findAll().stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public EditorialDTO editorialById(UUID id) {
        Editorial editorial = editorialRepositorio.findById(id)
            .orElse(null);
        if (editorial != null) {
            return convertToDTO(editorial);
        }
        return null;
    }

    @Transactional
    public void modificarEditorial(UUID id, String nombre) {
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
    
    // hard delete
    @Transactional
    public void eliminarEditorialHard(UUID id) {
        // validar(nombre);
        Optional<Editorial> editorial = editorialRepositorio.findById(id);
        if (editorial.isPresent()) {
            Editorial e = editorial.get();
            editorialRepositorio.delete(e);
        }
    }

    // public void validar(String nombre) {
    // if (nombre.isEmpty() || nombre == null) {
    // throw new ValidationException("El campo 'nombre' no puede estar vacío");
    // }
    // }

    @Transactional(readOnly = true)
    public Editorial getOne(UUID id) {
        return editorialRepositorio.getReferenceById(id);
    }


    // public List<EditorialDetailDTO> buscarPorTexto(String txt) {
        
    //     List<EditorialDetailDTO> editoriales = editorialRepositorio.findEditorialByText(txt);
    //     return editoriales.stream()
    //         .collect(Collectors.toList());
    // }

    // este metodo implemeta el filtro activo/inactivo en el servicio
    // posible implementar consulta especifica en repositorio para optimizar
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

    @Transactional(readOnly = true)
    public List<EditorialDetailDTO> findEditorialByTxt(String txt) {
        return editorialRepositorio.findEditorialByText(txt).stream()
                .map(editorial -> {
                    EditorialDetailDTO dto = new EditorialDetailDTO();
                    dto.setId(editorial.getId());
                    dto.setNombre(editorial.getNombre());
                    dto.setActivo(editorial.isActivo());
                    return dto;
                })
                .collect(Collectors.toList());
    }
    
    // private methods
    private EditorialDTO convertToDTO(Editorial editorial) {
        EditorialDTO dto = new EditorialDTO();
        dto.setId(editorial.getId());
        dto.setNombre(editorial.getNombre());
        dto.setActivo(editorial.isActiva());
        dto.setCreatedAt(editorial.getCreatedAt());
        dto.setUpdatedAt(editorial.getUpdatedAt());
        return dto;
    }
}
