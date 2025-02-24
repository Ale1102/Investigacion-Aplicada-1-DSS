package sv.edu.udb.repository.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import sv.edu.udb.controller.ProductoController;
import sv.edu.udb.model.Producto;
import sv.edu.udb.service.ProductoService;



@WebMvcTest(ProductoController.class)
public class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testCreateProducto() throws Exception {
        Producto producto = new Producto();
        producto.setNombre("Leche");
        producto.setPrecio(1.50);

        when(productoService.save(any(Producto.class))).thenReturn(producto);

        mockMvc.perform(post("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"nombre\": \"Leche\", \"precio\": 1.50 }"))
                .andExpect(status().isOk());
    }
}
