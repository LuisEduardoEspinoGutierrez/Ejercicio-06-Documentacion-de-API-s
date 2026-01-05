package com.upiiz.Practica346.services;

import com.upiiz.Practica346.entities.Copia;
import com.upiiz.Practica346.entities.Pelicula;
import com.upiiz.Practica346.repositories.CopiaRepository;
import com.upiiz.Practica346.repositories.PeliculaRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class CopiaService {

    @Autowired
    private CopiaRepository copiaRepo;

    @Autowired
    private PeliculaRepository peliculaRepo;

    public List<Copia> getAll() { return copiaRepo.findAll(); }

    public Copia getById(Long id) {
        return copiaRepo.findById(id).orElseThrow(() -> new RuntimeException("Copia no encontrada"));
    }

    // Crear copia recibiendo en el body una copia con pelicula.id
    public Copia create(Copia copia) {
        if (copia.getPelicula() == null || copia.getPelicula().getId() == null) {
            throw new RuntimeException("Debe enviar pelicula.id en el body");
        }
        Pelicula p = peliculaRepo.findById(copia.getPelicula().getId())
                .orElseThrow(() -> new RuntimeException("Película no encontrada"));
        copia.setPelicula(p);
        return copiaRepo.save(copia);
    }

    public Copia update(Long id, Copia copiaDetails) {
        Copia existing = getById(id);
        existing.setDeteriorada(copiaDetails.getDeteriorada());
        existing.setFormato(copiaDetails.getFormato());
        existing.setPrecioAlquiler(copiaDetails.getPrecioAlquiler());
        // Si vienen pelicula.id en body y se quiere cambiar la pelicula:
        if (copiaDetails.getPelicula() != null && copiaDetails.getPelicula().getId() != null) {
            Pelicula p = peliculaRepo.findById(copiaDetails.getPelicula().getId())
                    .orElseThrow(() -> new RuntimeException("Película no encontrada"));
            existing.setPelicula(p);
        }
        return copiaRepo.save(existing);
    }

    public void delete(Long id) { copiaRepo.deleteById(id); }

    public List<Copia> findByPeliculaId(Long peliculaId) { return copiaRepo.findByPeliculaId(peliculaId); }
}



