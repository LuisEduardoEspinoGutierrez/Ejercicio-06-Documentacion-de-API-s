package com.upiiz.Practica346.entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;


@Entity
@Table(name = "copias")
@Schema(description = "Entidad que representa una copia física o digital de una película")
public class Copia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // corresponde a n_copia -> ahora id
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Si la copia esta deteriorada", example = "true")
    private Boolean deteriorada;

    @Column(nullable = false) // formato = nn1
    @Schema(description = "Formato de la copia", example = "Fisico")
    private String formato;

    @Schema(description = "Precio de la copia", example = "150")
    private Double precioAlquiler;

    // id_pelicula = fk1, nn2 -> NOT NULL
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pelicula_id", nullable = false)
    @JsonBackReference("pelicula-copias")
    @Schema(description = "Película a la que pertenece la copia")
    private Pelicula pelicula;

    @OneToMany(mappedBy = "copia", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("copia-prestamos")
    private List<Prestamo> prestamos;

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Boolean getDeteriorada() { return deteriorada; }
    public void setDeteriorada(Boolean deteriorada) { this.deteriorada = deteriorada; }

    public String getFormato() { return formato; }
    public void setFormato(String formato) { this.formato = formato; }

    public Double getPrecioAlquiler() { return precioAlquiler; }
    public void setPrecioAlquiler(Double precioAlquiler) { this.precioAlquiler = precioAlquiler; }

    public Pelicula getPelicula() { return pelicula; }
    public void setPelicula(Pelicula pelicula) { this.pelicula = pelicula; }

    public List<Prestamo> getPrestamos() { return prestamos; }
    public void setPrestamos(List<Prestamo> prestamos) { this.prestamos = prestamos; }
}

