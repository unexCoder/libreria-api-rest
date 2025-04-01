package com.unexcoder.libreria_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.unexcoder.libreria_api.entities.Libro;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro,Long>{
    
}
