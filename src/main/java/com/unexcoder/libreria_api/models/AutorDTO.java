package com.unexcoder.libreria_api.models;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;

@Data
public class AutorDTO {
    private UUID id;
    private String nombre;
    private boolean activo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}