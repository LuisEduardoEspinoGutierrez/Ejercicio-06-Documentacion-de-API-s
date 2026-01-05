package com.upiiz.Practica346.controllers;

import com.upiiz.Practica346.entities.Cliente;
import com.upiiz.Practica346.entities.Prestamo;
import com.upiiz.Practica346.services.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Prestamos", description = "Operaciones CRUD sobre los prestamos")
@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    @Operation(summary = "Obtiene todos los prestamos")
    @ApiResponse(responseCode = "200", description = "Prestamos encontrados correctamente")
    @GetMapping
    public List<Prestamo> all() { return prestamoService.getAll(); }

    @Operation(summary = "Obtiene un prestamo por ID")
    @ApiResponse(responseCode = "200", description = "Prestamo encontrado")
    @ApiResponse(responseCode = "404", description = "Prestamo no encontrado")
    @GetMapping("/{id}")
    public Prestamo get(@PathVariable Long id) { return prestamoService.getById(id); }


    // CREATE: enviar JSON con cliente.id y copia.id dentro:
    // { "fechaTope":"2025-10-30", "cliente": { "id":1 }, "copia": { "id":2 } }
    @Operation(summary = "Crea una nuevo prestamo")
    @ApiResponse(responseCode = "201", description = "Prestamo creado correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Prestamo.class)))
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @ApiResponse(responseCode = "409", description = "Prestamo duplicado")
    @PostMapping
    public Prestamo create(@RequestBody Prestamo prestamo) { return prestamoService.create(prestamo); }


    @Operation(summary = "Actualiza un prestamo existente")
    @ApiResponse(responseCode = "200", description = "Prestamo actualizado")
    @ApiResponse(responseCode = "404", description = "Prestamo no encontrado")
    @PutMapping("/{id}")
    public Prestamo update(@PathVariable Long id, @RequestBody Prestamo prestamo) { return prestamoService.update(id, prestamo); }


    @Operation(summary = "Elimina un prestamo por ID")
    @ApiResponse(responseCode = "204", description = "Prestamo eliminado correctamente")
    @ApiResponse(responseCode = "404", description = "Prestamo no encontrado")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { prestamoService.delete(id); }


    @Operation(summary = "Obtiene todos los préstamos asociados a un cliente específico")
    @ApiResponse(responseCode = "200", description = "Prestamos encontrados")
    @ApiResponse(responseCode = "404", description = "Prestamos no encontrado")
    @GetMapping("/por-cliente/{clienteId}")
    public List<Prestamo> porCliente(@PathVariable Long clienteId) { return prestamoService.findByClienteId(clienteId); }

    @Operation(summary = "Devuelve todos los préstamos en los que participó una copia específica")
    @ApiResponse(responseCode = "200", description = "Prestamos encontrados")
    @ApiResponse(responseCode = "404", description = "Prestamos no encontrado")
    @GetMapping("/por-copia/{copiaId}")
    public List<Prestamo> porCopia(@PathVariable Long copiaId) { return prestamoService.findByCopiaId(copiaId); }
}

