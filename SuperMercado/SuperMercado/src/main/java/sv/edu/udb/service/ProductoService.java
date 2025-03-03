package sv.edu.udb.service;


import org.springframework.stereotype.Service;
import sv.edu.udb.model.Producto;
import sv.edu.udb.repository.ProductoRepository;

import java.util.List;

//Los servicios contienen la lógica de negocio necesaria.
// Se define los métodos para manejar la creación, actualización, eliminación y consulta de productos y pedidos
@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto findById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        productoRepository.deleteById(id);
    }

}
