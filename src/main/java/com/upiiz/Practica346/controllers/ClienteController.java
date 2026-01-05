package com.upiiz.Practica346.controllers;

import com.upiiz.Practica346.entities.Cliente;
import com.upiiz.Practica346.entities.Pelicula;
import com.upiiz.Practica346.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Clientes", description = "Operaciones CRUD sobre los clientes")
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "Obtiene todos los clientes")
    @ApiResponse(responseCode = "200", description = "Clientes encontrados correctamente")
    @GetMapping
    public List<Cliente> all() { return clienteService.getAll(); }

    @Operation(summary = "Obtiene un cliente por ID")
    @ApiResponse(responseCode = "200", description = "Cliente encontrado")
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    @GetMapping("/{id}")
    public Cliente get(@PathVariable Long id) { return clienteService.getById(id); }


    @Operation(summary = "Crea una nuevo cliente")
    @ApiResponse(responseCode = "201", description = "Cliente creado correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Cliente.class)))
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @ApiResponse(responseCode = "409", description = "Cliente duplicado")
    @PostMapping
    public Cliente create(@RequestBody Cliente cliente) { return clienteService.create(cliente); }


    @Operation(summary = "Actualiza un cliente existente")
    @ApiResponse(responseCode = "200", description = "Cliente actualizado")
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    @PutMapping("/{id}")
    public Cliente update(@PathVariable Long id, @RequestBody Cliente cliente) { return clienteService.update(id, cliente); }

    @Operation(summary = "Elimina un cliente por ID")
    @ApiResponse(responseCode = "204", description = "Cliente eliminado correctamente")
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { clienteService.delete(id); }
}



