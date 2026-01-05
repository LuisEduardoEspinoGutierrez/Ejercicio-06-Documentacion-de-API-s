package com.upiiz.Practica346.entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Entity
@Table(name = "peliculas")
@Schema(description = "Entidad que representa una película disponible en el Cine")
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // corresponde a id_pelicula -> ahora id
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "ID autogenerado de la película")
    private Long id;

    @Column(nullable = false) // titulo = nn1
    @Schema(description = "Título de la película", example = "Inception")
    private String titulo;

    @Schema(description = "Año de estreno", example = "2010")
    private Integer anio;

    @Schema(description = "Critica de la pelicula", example = "Es una pelicula muy buena")
    private String critica;

    @Schema(description = "Caratula de la pelicua", example = "matrix.jpg")
    private String caratula;

    @OneToMany(mappedBy = "pelicula", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("pelicula-copias")
    private List<Copia> copias;

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public Integer getAnio() { return anio; }
    public void setAnio(Integer anio) { this.anio = anio; }

    public String getCritica() { return critica; }
    public void setCritica(String critica) { this.critica = critica; }

    public String getCaratula() { return caratula; }
    public void setCaratula(String caratula) { this.caratula = caratula; }

    public List<Copia> getCopias() { return copias; }
    public void setCopias(List<Copia> copias) { this.copias = copias; }
}

