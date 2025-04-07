package com.unexcoder.libreria_api.models;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutorActivoDTO {
    private UUID id;
    private String nombre;
    private boolean activo;
}