package com.unexcoder.libreria_api.repositories;

// import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.unexcoder.libreria_api.entities.Editorial;
// import com.unexcoder.libreria_api.models.EditorialDTO;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, UUID> {

    // @Query("SELECT new com.unexcoder.libreria_api.models.EditorialDetailDTO(e.id, e.nombre, e.activo) " +
    //         "FROM Editorial e " +
    //         "WHERE LOWER(e.nombre) LIKE LOWER(CONCAT('%', :txt, '%'))")
    // List<EditorialDTO> findEditorialByText(@Param("txt") String txt);

}
