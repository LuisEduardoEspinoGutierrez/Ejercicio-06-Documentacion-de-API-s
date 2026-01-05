package com.upiiz.Practica346.repositories;
import com.upiiz.Practica346.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
