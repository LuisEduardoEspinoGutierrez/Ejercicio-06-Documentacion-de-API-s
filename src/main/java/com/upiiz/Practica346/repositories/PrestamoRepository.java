package com.upiiz.Practica346.repositories;
import com.upiiz.Practica346.entities.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long>
{
    List<Prestamo> findByClienteId(Long clienteId);
    List<Prestamo> findByCopiaId(Long copiaId);

}
