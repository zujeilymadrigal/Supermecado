package org.example.supermecado.Service;


import org.example.supermecado.Entities.CarritoProducto;
import org.example.supermecado.Repository.CarritoProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarritoProductoService {

    @Autowired
    private CarritoProductoRepository carritoProductoRepository;

    public CarritoProducto agregarProductoACarrito(CarritoProducto carritoProducto) {
        return carritoProductoRepository.save(carritoProducto);
    }

    public List<CarritoProducto> obtenerProductosDelCarrito(Long clienteId) {
        return carritoProductoRepository.findByClienteId(clienteId);
    }

    public void eliminarProductoDelCarrito(Long id) {
        carritoProductoRepository.deleteById(id);
    }

    public void deshacerEliminacionProducto(CarritoProducto carritoProducto) {
        carritoProductoRepository.save(carritoProducto);
    }
}