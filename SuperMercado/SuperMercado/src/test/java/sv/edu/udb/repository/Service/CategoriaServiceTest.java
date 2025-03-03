package sv.edu.udb.repository.Service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sv.edu.udb.model.Categoria;
import sv.edu.udb.repository.CategoriaRepository;
import sv.edu.udb.service.CategoriaService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CategoriaServiceTest {

    @InjectMocks
    private CategoriaService categoriaService;

    @Mock
    private CategoriaRepository categoriaRepository;

    public CategoriaServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        Categoria categoria = new Categoria();
        categoria.setNombre("Lácteos");
        when(categoriaRepository.findAll()).thenReturn(Collections.singletonList(categoria));

        List<Categoria> categorias = categoriaService.findAll();
        assertEquals(1, categorias.size());
        assertEquals("Lácteos", categorias.get(0).getNombre());
    }

    @Test
    public void testSave() {
        Categoria categoria = new Categoria();
        categoria.setNombre("Panadería");

        when(categoriaRepository.save(categoria)).thenReturn(categoria);

        Categoria savedCategoria = categoriaService.save(categoria);
        assertEquals("Panadería", savedCategoria.getNombre());
    }

    @Test
    public void testFindById() {
        Categoria categoria = new Categoria();
        categoria.setId(1L);
        categoria.setNombre("Congelados");

        when(categoriaRepository.findById(1L)).thenReturn(java.util.Optional.of(categoria));

        Categoria foundCategoria = categoriaService.findById(1L);
        assertEquals("Congelados", foundCategoria.getNombre());
    }

    @Test
    public void testDelete() {
        Long id = 1L;
        categoriaService.delete(id);
    }
}
