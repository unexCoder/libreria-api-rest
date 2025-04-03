package com.unexcoder.libreria_api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibroActivoDTO {
    private String titulo;
    private int ejemplares;
}
