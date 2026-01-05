package com.upiiz.Practica346.services;

import com.upiiz.Practica346.entities.*;
import com.upiiz.Practica346.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.time.LocalDate;

@Service
public class PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepo;

    @Autowired
    private ClienteRepository clienteRepo;

    @Autowired
    private CopiaRepository copiaRepo;

    public List<Prestamo> getAll() { return prestamoRepo.findAll(); }

    public Prestamo getById(Long id) { return prestamoRepo.findById(id).orElseThrow(() -> new RuntimeException("Prestamo no encontrado")); }

    // Crear prestamo recibiendo prestamo en body donde vienen cliente.id y copia.id
    public Prestamo create(Prestamo prestamo) {
        if (prestamo.getCliente() == null || prestamo.getCliente().getId() == null)
            throw new RuntimeException("Debe enviar cliente.id en el body");
        if (prestamo.getCopia() == null || prestamo.getCopia().getId() == null)
            throw new RuntimeException("Debe enviar copia.id en el body");

        Cliente c = clienteRepo.findById(prestamo.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Copia cp = copiaRepo.findById(prestamo.getCopia().getId())
                .orElseThrow(() -> new RuntimeException("Copia no encontrada"));

        prestamo.setCliente(c);
        prestamo.setCopia(cp);
        if (prestamo.getFechaPrestamo() == null) prestamo.setFechaPrestamo(LocalDate.now());
        return prestamoRepo.save(prestamo);
    }

    public Prestamo update(Long id, Prestamo prestamoDetails) {
        Prestamo existing = getById(id);
        existing.setFechaPrestamo(prestamoDetails.getFechaPrestamo());
        existing.setFechaTope(prestamoDetails.getFechaTope());
        existing.setFechaEntrega(prestamoDetails.getFechaEntrega());
        // cambiar cliente/copia si vienen en update:
        if (prestamoDetails.getCliente() != null && prestamoDetails.getCliente().getId() != null) {
            Cliente c = clienteRepo.findById(prestamoDetails.getCliente().getId()).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
            existing.setCliente(c);
        }
        if (prestamoDetails.getCopia() != null && prestamoDetails.getCopia().getId() != null) {
            Copia cp = copiaRepo.findById(prestamoDetails.getCopia().getId()).orElseThrow(() -> new RuntimeException("Copia no encontrada"));
            existing.setCopia(cp);
        }
        return prestamoRepo.save(existing);
    }

    public void delete(Long id) { prestamoRepo.deleteById(id); }

    public List<Prestamo> findByClienteId(Long clienteId) { return prestamoRepo.findByClienteId(clienteId); }
    public List<Prestamo> findByCopiaId(Long copiaId) { return prestamoRepo.findByCopiaId(copiaId); }
}

