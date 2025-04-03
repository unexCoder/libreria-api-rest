package com.unexcoder.libreria_api.models;

import java.util.UUID;
import lombok.Data;

@Data
public class AutorActivoDTO {
    private UUID id;
    private String nombre;
    private boolean activo;
}