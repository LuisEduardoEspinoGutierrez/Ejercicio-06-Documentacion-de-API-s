package com.upiiz.Practica346.services;

import com.upiiz.Practica346.entities.Pelicula;
import com.upiiz.Practica346.repositories.PeliculaRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepo;

    public List<Pelicula> getAll() { return peliculaRepo.findAll(); }

    public Pelicula getById(Long id) {
        return peliculaRepo.findById(id).orElseThrow(() -> new RuntimeException("Pelicula no encontrada"));
    }

    public Pelicula create(Pelicula pelicula) { return peliculaRepo.save(pelicula); }

    public Pelicula update(Long id, Pelicula p) {
        Pelicula existing = getById(id);
        existing.setTitulo(p.getTitulo());
        existing.setAnio(p.getAnio());
        existing.setCritica(p.getCritica());
        existing.setCaratula(p.getCaratula());
        return peliculaRepo.save(existing);
    }

    public void delete(Long id) { peliculaRepo.deleteById(id); }
}

