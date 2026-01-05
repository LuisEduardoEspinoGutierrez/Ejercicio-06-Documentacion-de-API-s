package com.upiiz.Practica346.services;

import com.upiiz.Practica346.entities.Cliente;
import com.upiiz.Practica346.repositories.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepo;

    public List<Cliente> getAll() { return clienteRepo.findAll(); }

    public Cliente getById(Long id) {
        return clienteRepo.findById(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    public Cliente create(Cliente cliente) { return clienteRepo.save(cliente); }

    public Cliente update(Long id, Cliente clienteDetails) {
        Cliente existing = getById(id);
        existing.setDni(clienteDetails.getDni());
        existing.setNombre(clienteDetails.getNombre());
        existing.setApellido1(clienteDetails.getApellido1());
        existing.setApellido2(clienteDetails.getApellido2());
        existing.setDireccion(clienteDetails.getDireccion());
        existing.setEmail(clienteDetails.getEmail());
        return clienteRepo.save(existing);
    }

    public void delete(Long id) { clienteRepo.deleteById(id); }
}

