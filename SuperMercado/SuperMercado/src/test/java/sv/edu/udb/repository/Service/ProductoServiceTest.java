package sv.edu.udb.repository.Service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sv.edu.udb.model.Producto;
import sv.edu.udb.repository.ProductoRepository;
import sv.edu.udb.service.ProductoService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ProductoServiceTest {

    @InjectMocks
    private ProductoService productoService;

    @Mock
    private ProductoRepository productoRepository;

    public ProductoServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        Producto producto = new Producto();
        producto.setNombre("Leche");
        producto.setPrecio(1.50);
        when(productoRepository.findAll()).thenReturn(Collections.singletonList(producto));

        List<Producto> productos = productoService.findAll();
        assertEquals(1, productos.size());
        assertEquals("Leche", productos.get(0).getNombre());
    }

    @Test
    public void testSave() {
        Producto producto = new Producto();
        producto.setNombre("Pan");
        producto.setPrecio(0.80);

        when(productoRepository.save(producto)).thenReturn(producto);

        Producto savedProducto = productoService.save(producto);
        assertEquals("Pan", savedProducto.getNombre());
        assertEquals(0.80, savedProducto.getPrecio());
    }

    @Test
    public void testFindById() {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Leche");

        when(productoRepository.findById(1L)).thenReturn(java.util.Optional.of(producto));

        Producto foundProducto = productoService.findById(1L);
        assertEquals("Leche", foundProducto.getNombre());
    }

    @Test
    public void testDelete() {
        Long id = 1L;
        productoService.delete(id);
    }
}
