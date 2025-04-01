package com.unexcoder.libreria_api.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.unexcoder.libreria_api.entities.Editorial;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial,UUID>{
    
}
