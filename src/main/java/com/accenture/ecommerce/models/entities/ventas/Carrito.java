package com.accenture.ecommerce.models.entities.ventas;
import com.accenture.ecommerce.models.entities.externos.Cliente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "carrito")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Boolean activo = true;
    
    @Column(name = "fecha_de_expiracion")
    private LocalDate fechaExpiracion;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarritoProducto> productos;

    public Carrito(Cliente cliente) {
        this.cliente = cliente;
        this.fechaExpiracion = LocalDate.now().plusDays(10);
    }

    public Carrito() {
        this.fechaExpiracion = LocalDate.now().plusDays(10);
    }

    public void deshabilitar() { activo = false; }
    public void habilitar() { activo = true; }

}
