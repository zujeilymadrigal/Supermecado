package org.example.supermecado.controller;


import org.example.supermecado.Entities.Cliente;
import org.example.supermecado.Repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/agregarCliente")
    public String agregarCliente(@RequestBody Cliente cliente) {
        clienteRepository.save(cliente);
        return "Cliente registrado con éxito: " + cliente.getNombre();
    }
}
