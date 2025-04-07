package com.unexcoder.libreria_api.repositories;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.unexcoder.libreria_api.entities.Autor;
import com.unexcoder.libreria_api.models.AutorActivoDTO;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor,UUID>{
    @Query("SELECT new com.unexcoder.libreria_api.models.AutorActivoDTO(" +
       "a.id, a.nombre, a.activo) " +
       "FROM Autor a " +
       "WHERE (LOWER(a.nombre) LIKE LOWER(CONCAT('%', :txt, '%')))")
      List<AutorActivoDTO> findAutorByText(@Param("txt") String txt);
}
