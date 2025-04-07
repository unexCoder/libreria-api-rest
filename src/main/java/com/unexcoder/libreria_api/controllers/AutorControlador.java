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

import com.unexcoder.libreria_api.entities.Autor;
import com.unexcoder.libreria_api.models.AutorActivoDTO;
import com.unexcoder.libreria_api.models.AutorDTO;
import com.unexcoder.libreria_api.services.AutorServicio;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/autor")
@RequiredArgsConstructor
public class AutorControlador {
    private final AutorServicio autorServicio;

    @PostMapping(value = "crear")
    public ResponseEntity<Object> crearAutor(@RequestParam(required = true) String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return new ResponseEntity<>("El nombre no puede estar vacío", HttpStatus.BAD_REQUEST);
        }
        try {
            autorServicio.crearAutor(nombre);
            return new ResponseEntity<>("Autor creado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error al crear autor: " + e.getMessage());
            return new ResponseEntity<>("Error al crear autor: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @GetMapping("")
    // public ResponseEntity<List<Autor>> listarAutores() {
    //     try {
    //         List<Autor> autores = autorServicio.listarAutores(); // Llama al servicio para obtener la lista de autores
    //         return new ResponseEntity<>(autores, HttpStatus.OK);
    //     } catch (Exception e) {
    //         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }

    @GetMapping("")
    public ResponseEntity<List<AutorDTO>> listarAutores() {
        try {
            List<AutorDTO> autores = autorServicio.listarAutoresDTO();
            return new ResponseEntity<>(autores, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("actualizar")
    public ResponseEntity<Object> actualizarAutor(@RequestParam String id, @RequestParam String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return new ResponseEntity<>("El nombre no puede estar vacío", HttpStatus.BAD_REQUEST);
        }
        try {
            autorServicio.modificarAutor(UUID.fromString(id), nombre);
            return new ResponseEntity<>("Autor actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error al actualizar autor: " + e.getMessage());
            return new ResponseEntity<>("Error al actualizar autor: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("actualizarDTO")
    public ResponseEntity<Object> actualizarAutor(@RequestBody AutorActivoDTO autorActivoDTO) {
        if (autorActivoDTO.getNombre() == null || autorActivoDTO.getNombre().trim().isEmpty()) {
            return new ResponseEntity<>("El nombre no puede estar vacío", HttpStatus.BAD_REQUEST);
        }
        try {
            autorServicio.modificarAutor(autorActivoDTO);
            return new ResponseEntity<>("Autor actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error al actualizar autor: " + e.getMessage());
            return new ResponseEntity<>("Error al actualizar autor: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("eliminar")
    public ResponseEntity<Object> eliminarAutor(@RequestParam String id) {
        try {
            autorServicio.eliminarAutor(UUID.fromString(id));
            return new ResponseEntity<>("Autor eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar autor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<Object> eliminarAutorHard(@PathVariable String id) {
        try {
            autorServicio.eliminarAutorlHard(UUID.fromString(id));
            return new ResponseEntity<>("Autor eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar autor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("activos")
    public ResponseEntity<List<Autor>> listarAutoresActivos(@RequestParam boolean activo) {
        try {
            List<Autor> autores = autorServicio.listarAutoresActivos(activo); // Llama al servicio para obtener la lista
                                                                              // de autores
            return new ResponseEntity<>(autores, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<AutorDTO> autorById(@PathVariable String id) {
        try {
            AutorDTO autor = autorServicio.autorById(UUID.fromString(id));
            if (autor != null) {
                return new ResponseEntity<>(autor, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/{txt}")
    public ResponseEntity<List<AutorActivoDTO>> searchautorByText(@PathVariable String txt) {
        try {
            List<AutorActivoDTO> autores = autorServicio.findAutorByTxt(txt);
            if (autores.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(autores);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
