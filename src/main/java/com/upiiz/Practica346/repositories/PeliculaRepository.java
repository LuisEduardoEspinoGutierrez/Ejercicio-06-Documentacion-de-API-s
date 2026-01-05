package com.upiiz.Practica346.repositories;

import com.upiiz.Practica346.entities.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PeliculaRepository extends JpaRepository<Pelicula, Long > {
}
