package org.example.supermecado.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.supermecado.Repository.CajaRepository;
import org.example.supermecado.Entities.Caja;

@Service
public class CajaService {

    @Autowired
    private CajaRepository cajaRepository;

    public Caja agregarCaja(Caja caja) {
        return cajaRepository.save(caja);
    }

    public List<Caja> obtenerCajas() {
        return cajaRepository.findAll();
    }

    public void atenderCliente(Long cajaId) {
        // Lógica para atender al siguiente cliente
        Caja caja = cajaRepository.findById(cajaId)
                .orElseThrow(() -> new IllegalArgumentException("Caja no encontrada"));
        // Simulación de atención al cliente (puedes expandir esto según tu lógica)
        if (!caja.getFilaClientes().isEmpty()) {
            caja.getFilaClientes().remove(0); // Elimina al primer cliente en la fila
            cajaRepository.save(caja);
        }
    }
}
