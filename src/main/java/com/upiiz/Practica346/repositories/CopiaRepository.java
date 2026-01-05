package com.upiiz.Practica346.repositories;

import com.upiiz.Practica346.entities.Copia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CopiaRepository extends JpaRepository<Copia, Long>
{
    List<Copia> findByPeliculaId(Long peliculaId);
}
