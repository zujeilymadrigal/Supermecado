package org.example.supermecado.controller;

import org.example.supermecado.Entities.Caja;
import org.example.supermecado.Entities.Cliente;
import org.example.supermecado.Repository.CajaRepository;
import org.example.supermecado.Repository.ClienteRepository;
import org.example.supermecado.custom.Pila;
import org.example.supermecado.custom.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/caja")
public class CajaController {
    @Autowired
    private CajaRepository cajaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    private Queue<Optional<Cliente>> queue = new Queue<>();

    @PostMapping("/agregar")
    public String agregarCliente(@RequestParam Long clienteId) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        queue.enqueue(cliente); // Usamos enqueue en lugar de offer
        return "Cliente " + cliente.map(Cliente::getNombre).orElse("Desconocido");
    }

    @GetMapping("/atender")
    public Optional<Cliente> atenderCliente(@RequestParam Long cajaId) {
        return queue.dequeue(); // Usamos dequeue en lugar de poll
    }

    @GetMapping("/obtenerFila")
    public List<Optional<Cliente>> obtenerFila(@RequestParam Long cajaId) {
        List<Optional<Cliente>> lista = new ArrayList<>();

        // Sacamos todos los clientes de la fila temporalmente
        while (!queue.isEmpty()) {
            lista.add(queue.dequeue());
        }

        // Regresamos los clientes a la fila
        for (Optional<Cliente> cliente : lista) {
            queue.enqueue(cliente);
        }

        return lista;
    }
}
