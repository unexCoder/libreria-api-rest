package com.unexcoder.libreria_api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibrosAutorDTO {
    private String titulo;
    private int ejemplares;
    private String editorialNombre;
    private boolean activo;
}