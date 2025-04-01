package com.unexcoder.libreria_api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unexcoder.libreria_api.entities.Editorial;
import com.unexcoder.libreria_api.services.EditorialServicio;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/editorial")
@RequiredArgsConstructor
public class EditorialControlador {
    private final EditorialServicio editorialServicio;
    
    @PostMapping("crear")
    public ResponseEntity<Object> crearEditorial(@RequestParam String nombre) {
       try {
           editorialServicio.crearEditorial(nombre);
           return new ResponseEntity<>(HttpStatus.OK);
       } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @GetMapping("listar")
    public ResponseEntity<List<Editorial>> listarEditoriales() {
        try {
            List<Editorial> editoriales = editorialServicio.listarEditoriales();  // Llama al servicio para obtener la lista de autores
            return new ResponseEntity<>(editoriales,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("actualizar")
    public ResponseEntity<Object> actualizarEditorial(@RequestParam String id, @RequestParam String nombre) {
        try {
            editorialServicio.modificarEditorial(UUID.fromString(id),nombre);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PatchMapping("eliminar")
    public ResponseEntity<Object> eliminarEditorial(@RequestParam String id) {
        try {
            editorialServicio.eliminarEditorial(UUID.fromString(id));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("listar/activas")
    public ResponseEntity<List<Editorial>> listarEditorialesActivas(@RequestParam boolean activa) {
        try {
            List<Editorial> editoriales = editorialServicio.listarEditorialesActivas(activa);  // Llama al servicio para obtener la lista de autores
            return new ResponseEntity<>(editoriales,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

