package com.upiiz.Practica346.controllers;

import com.upiiz.Practica346.entities.Copia;
import com.upiiz.Practica346.entities.Pelicula;
import com.upiiz.Practica346.services.CopiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Copias", description = "Operaciones CRUD sobre las copias")
@RestController
@RequestMapping("/copias")
public class CopiaController {

    @Autowired
    private CopiaService copiaService;

    @Operation(summary = "Obtiene todas las copias")
    @ApiResponse(responseCode = "200", description = "Copias encontradas correctamente")
    @GetMapping
    public List<Copia> all() { return copiaService.getAll(); }

    @Operation(summary = "Obtiene una copia por ID")
    @ApiResponse(responseCode = "200", description = "Copia encontrada")
    @ApiResponse(responseCode = "404", description = "Copia no encontrada")
    @GetMapping("/{id}")
    public Copia get(@PathVariable Long id) { return copiaService.getById(id); }


    @Operation(summary = "Crea una copia película")
    @ApiResponse(responseCode = "201", description = "Copia creada correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Copia.class)))
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @ApiResponse(responseCode = "409", description = "Copia duplicada")
    // CREATE: enviar JSON con pelicula.id dentro -> { "formato":"DVD", "deteriorada": false, "precioAlquiler": 25.0, "pelicula": { "id": 1 } }
    @PostMapping
    public Copia create(@RequestBody Copia copia) { return copiaService.create(copia); }

    @Operation(summary = "Actualiza una copia existente")
    @ApiResponse(responseCode = "200", description = "Copia actualizada")
    @ApiResponse(responseCode = "404", description = "Copia no encontrada")
    @PutMapping("/{id}")
    public Copia update(@PathVariable Long id, @RequestBody Copia copia) { return copiaService.update(id, copia); }


    @Operation(summary = "Elimina una copia por ID")
    @ApiResponse(responseCode = "204", description = "Copia eliminada correctamente")
    @ApiResponse(responseCode = "404", description = "Copia no encontrada")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { copiaService.delete(id); }

    @Operation(summary = "Obtiene todas las copias asociadas a una película específica")
    @ApiResponse(responseCode = "200", description = "Copia encontrada")
    @ApiResponse(responseCode = "404", description = "Copia no encontrada")
    @GetMapping("/por-pelicula/{peliculaId}")
    public List<Copia> byPelicula(@PathVariable Long peliculaId) { return copiaService.findByPeliculaId(peliculaId); }
}

