package sv.edu.udb.repository.Service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sv.edu.udb.model.Pedido;
import sv.edu.udb.repository.PedidoRepository;
import sv.edu.udb.service.PedidoService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PedidoServiceTest {
    @InjectMocks
    private PedidoService pedidoService;

    @Mock
    private PedidoRepository pedidoRepository;

    public PedidoServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        Pedido pedido = new Pedido();
        pedido.setCliente("Juan");
        when(pedidoRepository.findAll()).thenReturn(Collections.singletonList(pedido));

        List<Pedido> pedidos = pedidoService.findAll();
        assertEquals(1, pedidos.size());
        assertEquals("Juan", pedidos.get(0).getCliente());
    }

    @Test
    public void testSave() {
        Pedido pedido = new Pedido();
        pedido.setCliente("Maria");

        when(pedidoRepository.save(pedido)).thenReturn(pedido);

        Pedido savedPedido = pedidoService.save(pedido);
        assertEquals("Maria", savedPedido.getCliente());
    }

    @Test
    public void testFindById() {
        Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setCliente("Luis");

        when(pedidoRepository.findById(1L)).thenReturn(java.util.Optional.of(pedido));

        Pedido foundPedido = pedidoService.findById(1L);
        assertEquals("Luis", foundPedido.getCliente());
    }

    @Test
    public void testDelete() {
        Long id = 1L;
        pedidoService.delete(id);
    }
}
