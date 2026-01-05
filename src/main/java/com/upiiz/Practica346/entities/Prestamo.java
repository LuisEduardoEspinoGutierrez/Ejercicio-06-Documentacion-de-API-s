package com.upiiz.Practica346.entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "prestamos")
@Schema(description = "Entidad que representa un préstamo de una copia de película a un cliente")
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // corresponde a id_prestamo -> ahora id
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Column(nullable = false) // fecha_prestamo = nn1
    @Schema(description = "Fecha del préstamo", example = "2025-10-03")
    private LocalDate fechaPrestamo;

    @Schema(description = "Fecha tope del préstamo", example = "2025-10-10")
    private LocalDate fechaTope;

    @Schema(description = "Fecha de entrega del préstamo", example = "2025-10-07")
    private LocalDate fechaEntrega;

    // cod_cliente = fk1, nn2 -> NOT NULL
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    @JsonBackReference("cliente-prestamos")
    @Schema(description = "Cliente que realiza el préstamo")
    private Cliente cliente;

    // n_copia = fk2, nn3 -> NOT NULL
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "copia_id", nullable = false)
    @JsonBackReference("copia-prestamos")
    @Schema(description = "Copia prestada")
    private Copia copia;

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getFechaPrestamo() { return fechaPrestamo; }
    public void setFechaPrestamo(LocalDate fechaPrestamo) { this.fechaPrestamo = fechaPrestamo; }

    public LocalDate getFechaTope() { return fechaTope; }
    public void setFechaTope(LocalDate fechaTope) { this.fechaTope = fechaTope; }

    public LocalDate getFechaEntrega() { return fechaEntrega; }
    public void setFechaEntrega(LocalDate fechaEntrega) { this.fechaEntrega = fechaEntrega; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Copia getCopia() { return copia; }
    public void setCopia(Copia copia) { this.copia = copia; }
}

