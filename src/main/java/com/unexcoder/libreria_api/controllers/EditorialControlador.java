package com.unexcoder.libreria_api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unexcoder.libreria_api.entities.Editorial;
import com.unexcoder.libreria_api.models.EditorialDTO;
// import com.unexcoder.libreria_api.models.EditorialDetailDTO;
import com.unexcoder.libreria_api.services.EditorialServicio;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/editorial")
@RequiredArgsConstructor
public class EditorialControlador {
    private final EditorialServicio editorialServicio;
    
    @PostMapping("crear")
    public ResponseEntity<Object> crearEditorial(@RequestParam String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return new ResponseEntity<>("El nombre no puede estar vacío", HttpStatus.BAD_REQUEST);
        }
        try {
           editorialServicio.crearEditorial(nombre);
           return new ResponseEntity<>("Editorial creada exitosamente",HttpStatus.OK);
       } catch (Exception e) {
           return new ResponseEntity<>("Error al crear editorial: " + e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
    
    @PostMapping("crearDTO")
    public ResponseEntity<Object> crearEditorial(@RequestBody EditorialDTO editorialDTO) {
        if (editorialDTO.getNombre() == null || editorialDTO.getNombre().trim().isEmpty()) {
            return new ResponseEntity<>("El nombre no puede estar vacío", HttpStatus.BAD_REQUEST);
        }
        try {
           editorialServicio.crearEditorial(editorialDTO);
           return new ResponseEntity<>("Editorial creada exitosamente",HttpStatus.OK);
       } catch (Exception e) {
           return new ResponseEntity<>("Error al crear editorial: " + e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    // @GetMapping("")
    // public ResponseEntity<List<Editorial>> listarEditoriales() {
    //     try {
    //         List<Editorial> editoriales = editorialServicio.listarEditoriales();  // Llama al servicio para obtener la lista de autores
    //         return new ResponseEntity<>(editoriales,HttpStatus.OK);
    //     } catch (Exception e) {
    //         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }
    
    @GetMapping("")
    public ResponseEntity<List<EditorialDTO>> listarEditoriales() {
        try {
            List<EditorialDTO> editoriales = editorialServicio.listarEditorialesDTO();
            return new ResponseEntity<>(editoriales, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            
        }
    }

    
    @PatchMapping("actualizar")
    public ResponseEntity<Object> actualizarEditorial(@RequestParam String id, @RequestParam String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return new ResponseEntity<>("El nombre no puede estar vacío", HttpStatus.BAD_REQUEST);
        }
        try {
            editorialServicio.modificarEditorial(UUID.fromString(id),nombre);
            return new ResponseEntity<>("Editorial creada exitosamente",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear editorial: " + e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PatchMapping("eliminar")
    public ResponseEntity<Object> eliminarEditorial(@RequestParam String id) {
        try {
            editorialServicio.eliminarEditorial(UUID.fromString(id));
            return new ResponseEntity<>("Editorial eliminada exitosamente",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar editorial: " + e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<Object> eliminarEditorialHard(@PathVariable String id) {
        try {
            editorialServicio.eliminarEditorialHard(UUID.fromString(id));
            return new ResponseEntity<>("Editorial eliminada exitosamente",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar editorial: " + e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("activas")
    public ResponseEntity<List<Editorial>> listarEditorialesActivas(@RequestParam boolean activa) {
        try {
            List<Editorial> editoriales = editorialServicio.listarEditorialesActivas(activa);  // Llama al servicio para obtener la lista de autores
            return new ResponseEntity<>(editoriales,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<EditorialDTO> editorialById(@PathVariable String id) {
        try {
            EditorialDTO editorial = editorialServicio.editorialById(UUID.fromString(id));
            if (editorial != null) {
                return new ResponseEntity<>(editorial, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @GetMapping("search/{txt}")
    // public ResponseEntity<List<EditorialDetailDTO>> buscarEditorial(@PathVariable String txt) {
    //     try {
    //         List<EditorialDetailDTO> editoriales = editorialServicio.buscarPorTexto(txt)
    //         return new ResponseEntity<>(editoriales, HttpStatus.OK);
    //     } catch (Exception e) {
    //         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }
}

