package com.upiiz.Practica346.controllers;

import com.upiiz.Practica346.entities.Pelicula;
import com.upiiz.Practica346.services.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Películas", description = "Operaciones CRUD sobre las películas")
@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    @Operation(summary = "Obtiene todas las películas")
    @ApiResponse(responseCode = "200", description = "Películas encontradas correctamente")
    @GetMapping
    public List<Pelicula> all() { return peliculaService.getAll(); }

    @Operation(summary = "Obtiene una película por ID")
    @ApiResponse(responseCode = "200", description = "Película encontrada")
    @ApiResponse(responseCode = "404", description = "Película no encontrada")
    @GetMapping("/{id}")
    public Pelicula get(@PathVariable Long id)
    {
        return peliculaService.getById(id);
    }


    @Operation(summary = "Crea una nueva película")
    @ApiResponse(responseCode = "201", description = "Película creada correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Pelicula.class)))
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @ApiResponse(responseCode = "409", description = "Película duplicada")
    @PostMapping
    public Pelicula create(@RequestBody Pelicula pelicula) { return peliculaService.create(pelicula); }


    @Operation(summary = "Actualiza una película existente")
    @ApiResponse(responseCode = "200", description = "Película actualizada")
    @ApiResponse(responseCode = "404", description = "Película no encontrada")
    @PutMapping("/{id}")
    public Pelicula update(@PathVariable Long id, @RequestBody Pelicula pelicula) { return peliculaService.update(id, pelicula); }

    @Operation(summary = "Elimina una película por ID")
    @ApiResponse(responseCode = "204", description = "Película eliminada correctamente")
    @ApiResponse(responseCode = "404", description = "Película no encontrada")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { peliculaService.delete(id); }
}

