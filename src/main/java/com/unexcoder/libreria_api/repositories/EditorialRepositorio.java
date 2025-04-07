package com.unexcoder.libreria_api.repositories;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.unexcoder.libreria_api.entities.Editorial;
import com.unexcoder.libreria_api.models.EditorialDetailDTO;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, UUID> {

    @Query("SELECT new com.unexcoder.libreria_api.models.EditorialDetailDTO(" +
       "e.id, e.nombre, e.activa) " +
       "FROM Editorial e " +
       "WHERE (LOWER(e.nombre) LIKE LOWER(CONCAT('%', :txt, '%')))")
      List<EditorialDetailDTO> findEditorialByText(@Param("txt") String txt);

}
