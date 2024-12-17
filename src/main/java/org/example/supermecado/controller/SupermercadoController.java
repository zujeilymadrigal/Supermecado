package org.example.supermecado.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.supermecado.Entities.CarritoProducto;
import org.example.supermecado.Entities.Producto;
import org.example.supermecado.Repository.CarritoProductoRepository;
import org.example.supermecado.Repository.ProductoRepository;

@RestController
@RequestMapping("/supermercado")
public class SupermercadoController {

    @Autowired
    private CarritoProductoRepository carritoProductoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @PostMapping("/comprar/{clienteId}")
    public ResponseEntity<String> procesarCompra(@PathVariable Long clienteId) {

        List<CarritoProducto> productosEnCarrito = carritoProductoRepository.findByClienteId(clienteId);

        if (productosEnCarrito.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El carrito está vacío. No se puede procesar la compra.");
        }

        Double total=0.0;

        for (CarritoProducto item : productosEnCarrito) {
            Producto producto = item.getProducto();
            total+=item.getProducto().getPrecio()*item.getCantidad();

            if (producto.getStock() < item.getCantidad()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Stock insuficiente para el producto: " + producto.getNombre());
            }


            producto.setStock(producto.getStock() - item.getCantidad());
            productoRepository.save(producto);


            carritoProductoRepository.delete(item);
        }



        return ResponseEntity.ok("Compra procesada exitosamente."+"total: "+total);
    }
}
