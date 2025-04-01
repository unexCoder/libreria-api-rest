package com.unexcoder.libreria_api.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.unexcoder.libreria_api.entities.Autor;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor,UUID>{
    
}
