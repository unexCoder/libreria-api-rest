package com.unexcoder.libreria_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.unexcoder.libreria_api.entities.Libro;
import com.unexcoder.libreria_api.models.LibroActivoDTO;
// import com.unexcoder.libreria_api.models.LibroActivoDTO;
import com.unexcoder.libreria_api.projections.LibroActivo;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro,Long>{

    @Query("SELECT new com.unexcoder.libreria_api.models.LibroActivoDTO(l.titulo, l.ejemplares) FROM Libro l WHERE l.activo = true")
    List<LibroActivoDTO> listarActivosDTO(); 

    @Query(value = "SELECT l.titulo as titulo, l.ejemplares as ejemplares FROM libro l WHERE l.activo = true", nativeQuery = true)
    List<LibroActivo> listarActivos();
    
    // // Add this method for general listing
    // @Query("SELECT l FROM Libro l")
    // List<Libro> findAll();
    
}
