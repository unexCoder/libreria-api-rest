package com.unexcoder.libreria_api.models;

import java.util.UUID;
import lombok.Data;

@Data
public class EditorialDetailDTO {
    private UUID id;
    private String nombre;
    private boolean activo;
}
