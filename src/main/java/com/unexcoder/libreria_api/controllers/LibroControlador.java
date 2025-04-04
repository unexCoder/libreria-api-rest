package com.unexcoder.libreria_api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unexcoder.libreria_api.models.LibroActivoDTO;
import com.unexcoder.libreria_api.models.LibroDTO;
import com.unexcoder.libreria_api.models.LibroDetailDTO;
import com.unexcoder.libreria_api.models.LibrosAutorDTO;
import com.unexcoder.libreria_api.models.LibrosEditorialDTO;
// import com.unexcoder.libreria_api.projections.LibroActivo;
import com.unexcoder.libreria_api.services.LibroServicio;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/libro")
@RequiredArgsConstructor
public class LibroControlador {

    private final LibroServicio libroservicio;

    @PostMapping("crear")
    public ResponseEntity<Object> crearLibro(@RequestBody LibroDTO libroDTO) {
        if (libroDTO.getTitulo() == null || libroDTO.getTitulo().trim().isEmpty()) {
            return new ResponseEntity<>("El nombre no puede estar vacío", HttpStatus.BAD_REQUEST);
        }
        try {
            libroservicio.crearLibroDTO(libroDTO);
            return new ResponseEntity<>("Libro creado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear libro: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<LibroDTO>> listarLibros() {
        try {
            List<LibroDTO> libros = libroservicio.listar();
            return new ResponseEntity<>(libros, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @GetMapping("activos")
    // public ResponseEntity<List<LibroActivo>> listarLibrosActivos() {
    // try {
    // List<LibroActivo> libros = libroservicio.listarActivos();
    // return new ResponseEntity<>(libros, HttpStatus.OK);
    // } catch (Exception e) {
    // return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    // }
    // }

    @GetMapping("activos")
    public ResponseEntity<List<LibroActivoDTO>> listarLibrosActivosDTO() {
        try {
            List<LibroActivoDTO> libros = libroservicio.listarActivosDTO();
            return new ResponseEntity<>(libros, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{isbn}")
    public ResponseEntity<LibroDTO> libroByIsbn(@PathVariable Long isbn) {
        try {
            LibroDTO libro = libroservicio.libroByIsbn(isbn);
            if (libro != null) {
                return new ResponseEntity<>(libro, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/editorial/{id}")
    public ResponseEntity<List<LibrosEditorialDTO>> getLibrosByEditorial(@PathVariable String id) {
        try {
            List<LibrosEditorialDTO> libros = libroservicio.librosByEditorial(UUID.fromString(id));
            return new ResponseEntity<>(libros, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/autor/{id}")
    public ResponseEntity<List<LibrosAutorDTO>> getLibrosByAutor(@PathVariable String id) {
        try {
            List<LibrosAutorDTO> libros = libroservicio.librosAutor(UUID.fromString(id));
            return new ResponseEntity<>(libros, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<LibroDetailDTO>> searchLibros(
            @RequestParam(required = false) String autorId,
            @RequestParam(required = false) String editorialId) {
        try {
            UUID autorUUID = autorId != null ? UUID.fromString(autorId) : null;
            UUID editorialUUID = editorialId != null ? UUID.fromString(editorialId) : null;

            List<LibroDetailDTO> libros = libroservicio.findLibros(autorUUID, editorialUUID);
            return ResponseEntity.ok(libros);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
