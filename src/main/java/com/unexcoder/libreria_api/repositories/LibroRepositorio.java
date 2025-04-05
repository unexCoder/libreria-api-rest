package com.unexcoder.libreria_api.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.unexcoder.libreria_api.entities.Libro;
import com.unexcoder.libreria_api.models.LibroActivoDTO;
import com.unexcoder.libreria_api.models.LibroDetailDTO;
import com.unexcoder.libreria_api.models.LibrosAutorDTO;
import com.unexcoder.libreria_api.models.LibrosEditorialDTO;
// import com.unexcoder.libreria_api.models.LibroActivoDTO;
import com.unexcoder.libreria_api.projections.LibroActivo;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Long> {

   @Query("SELECT new com.unexcoder.libreria_api.models.LibroActivoDTO(l.titulo, l.ejemplares) FROM Libro l WHERE l.activo = true")
   List<LibroActivoDTO> listarActivosDTO();

   @Query(value = "SELECT l.titulo as titulo, l.ejemplares as ejemplares FROM libro l WHERE l.activo = true", nativeQuery = true)
   List<LibroActivo> listarActivos();

   @Query("SELECT new com.unexcoder.libreria_api.models.LibrosEditorialDTO(l.titulo, l.ejemplares, l.autor.nombre, l.activo) "
         +
         "FROM Libro l WHERE l.editorial.id = :id")
   List<LibrosEditorialDTO> findLibrosByEditorial(@Param("id") UUID id);

   @Query("SELECT new com.unexcoder.libreria_api.models.LibrosAutorDTO(l.titulo, l.ejemplares, l.editorial.nombre, l.activo) "
         +
         "FROM Libro l WHERE l.autor.id = :id")
   List<LibrosAutorDTO> findLibrosByAutor(@Param("id") UUID id);

   @Query("SELECT new com.unexcoder.libreria_api.models.LibroDetailDTO(" +
       "l.isbn, l.titulo, l.ejemplares, l.autor.nombre, l.editorial.nombre, l.activo) " +
       "FROM Libro l " +
       "WHERE (:autorId IS NULL OR l.autor.id = :autorId) " +
       "AND (:editorialId IS NULL OR l.editorial.id = :editorialId)")
   List<LibroDetailDTO> findLibrosByAutorOrEditorial(@Param("autorId") UUID autorId, @Param("editorialId") UUID editorialId);

   @Query("SELECT new com.unexcoder.libreria_api.models.LibroDetailDTO(" +
       "l.isbn, l.titulo, l.ejemplares, l.autor.nombre, l.editorial.nombre, l.activo) " +
       "FROM Libro l " +
       "WHERE (LOWER(l.titulo) LIKE LOWER(CONCAT('%', :txt, '%')) OR " +
       "      LOWER(l.autor.nombre) LIKE LOWER(CONCAT('%', :txt, '%')) OR " +
       "      LOWER(l.editorial.nombre) LIKE LOWER(CONCAT('%', :txt, '%'))) ")
      List<LibroDetailDTO> findLibrosByText(
       @Param("txt") String txt);
}
