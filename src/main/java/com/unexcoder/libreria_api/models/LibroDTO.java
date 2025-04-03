package com.unexcoder.libreria_api.models;

import lombok.Data;

@Data
public class LibroDTO {
    private Long isbn;
    private String titulo;
    private int ejemplares;
    private String idAutor;
    private String idEditorial;    
    private boolean activo;
}
