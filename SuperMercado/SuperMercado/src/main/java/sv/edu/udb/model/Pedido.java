package sv.edu.udb.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cliente;
    private Double total;

    @OneToMany
    @JoinColumn(name = "pedido_id")
    private Set<Producto> productos;

    public Pedido() {}

    public Pedido(String cliente, Set<Producto> productos) {
        this.cliente = cliente;
        this.productos = productos;
        this.total = calcularTotal();
    }

    public Double calcularTotal() {
        return productos.stream().mapToDouble(Producto::getPrecio).sum();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Set<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
        this.total = calcularTotal();
    }
}
