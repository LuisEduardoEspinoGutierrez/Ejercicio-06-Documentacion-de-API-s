package com.upiiz.Practica346.entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "clientes")
@Schema(description = "Entidad que representa a un cliente registrado en el cine")

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // corresponde a cod_cliente -> ahora id
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "ID autogenerado del cliente")
    private Long id;

    @Column(nullable = false, unique = true) // dni = uk1, nn1
    @Schema(description = "DNI del cliente", example = "123juan")
    private String dni;

    @Column(nullable = false) // nombre = nn2
    @Schema(description = "Nombre completo del cliente", example = "Juan")
    private String nombre;

    @Column(nullable = false) // apellido1 = nn3
    @Schema(description = "Apellido 1 del cliente", example = "Pérez")
    private String apellido1;

    @Column(nullable = true) // apellido2 = nn4 (si quieres nn4 not null, cambia nullable=false)
    @Schema(description = "Apellido 2 del cliente", example = "Lopez")
    private String apellido2;

    @Schema(description = "Direccion del cliente", example = "Calle san ignacio, colonia santa maria")
    private String direccion;

    @Column(unique = true) // email = uk2
    @Schema(description = "Email del cliente", example = "juan@gmail.com")
    private String email;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("cliente-prestamos")
    private List<Prestamo> prestamos;

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido1() { return apellido1; }
    public void setApellido1(String apellido1) { this.apellido1 = apellido1; }

    public String getApellido2() { return apellido2; }
    public void setApellido2(String apellido2) { this.apellido2 = apellido2; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<Prestamo> getPrestamos() { return prestamos; }
    public void setPrestamos(List<Prestamo> prestamos) { this.prestamos = prestamos; }
}

