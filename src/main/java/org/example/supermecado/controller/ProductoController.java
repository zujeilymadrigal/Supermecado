package org.example.supermecado.controller;



import org.example.supermecado.Entities.Producto;
import org.example.supermecado.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;


    @PostMapping("/agregarProducto")
    public String agregarProducto(@RequestBody Producto producto) {
        productoRepository.save(producto);
        return "Producto agregado con éxito: " + producto.getNombre();
    }


    @GetMapping("/{id}")
    public Producto buscarProductoPorId(@PathVariable Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con el ID: " + id));
    }
}
