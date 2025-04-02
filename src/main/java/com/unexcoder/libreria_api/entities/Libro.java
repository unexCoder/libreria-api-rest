package com.unexcoder.libreria_api.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="libro")
@EntityListeners(AuditingEntityListener.class)
public class Libro {
    
    @Id
    private Long isbn;
    
    @NotNull(message = "El nombre no puede ser nulo")
    @Size(min = 2, max = 255, message = "El título debe tener entre 2 y 255 caracteres")
    @Column(name = "nombre",length = 255, nullable = false)
    private String titulo;

    @PositiveOrZero(message = "El número de ejemplares no puede ser negativo")
    private int ejemplares;
    
    @Column(name = "activo",nullable = false)
    @NotNull(message = "El estado no puede ser nulo")
    @Builder.Default
    private boolean activo = true;
    
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "autor_id", nullable = false)
    private Autor autor;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "editorial_id", nullable = false)
    private Editorial editorial;

    // Audit fields
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
