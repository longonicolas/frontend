package com.accenture.ecommerce.services.ventas;
import com.accenture.ecommerce.clients.EmpleadosClient;
import com.accenture.ecommerce.clients.ProductosClient;
import com.accenture.ecommerce.dtos.*;
import com.accenture.ecommerce.dtos.ventasDtos.*;
import com.accenture.ecommerce.exceptions.FacturaNoPermitida;
import com.accenture.ecommerce.exceptions.VentaNoEncontradaException;
import com.accenture.ecommerce.models.entities.externos.Cliente;
import com.accenture.ecommerce.models.entities.externos.Producto;
import com.accenture.ecommerce.models.entities.externos.Vendedor;
import com.accenture.ecommerce.models.entities.ventas.*;
import com.accenture.ecommerce.models.repositories.ventas.*;
import com.accenture.ecommerce.services.pagos.ProcesamientoPagoService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class VentasService implements IVentasService {
    @Autowired
    private VentasRepository repositorioDeVentas;
    @Autowired
    private EstadoVentaRepository repositorioDeEstados;
    @Autowired
    private FacturasRepository facturasRepository;
    @Autowired
    private ProductoVentaRepository productoVentaRepository;
    @Autowired
    private ProductosClient productosClient;
    @Autowired
    private ProductosService productosService;
    @Autowired
    private EmpleadosClient empleadosClient;
    @Autowired
    private EmpleadoService empleadoService;
    @Autowired
    private VendedoresRepository vendedoresRepository;
    @Autowired
    private ProcesamientoPagoService procesamientoPagoService;
    @Autowired
    private PagosRepository pagosRepository;


    public List<VentaOutputDTO> buscarTodasLasVentas() {
        return this.repositorioDeVentas.findAll().stream().map(VentaOutputDTO::new).toList();
    }

    public VentaOutputDTO buscarVenta(Long id) {
       Optional<Venta> unaVenta = this.repositorioDeVentas.findById(id);
       if (unaVenta.isPresent()){
           return new VentaOutputDTO(unaVenta.get());
       } else {
           throw new VentaNoEncontradaException("Venta #" + id + " no encontrado");
       }
    }

    public VentaOutputDTO crearVenta(Carrito carrito, Cliente cliente, CompraInputDTO compra) {
        ResponseEntity<EmpleadoRequestDTO> nuevoEmpleado = empleadosClient.getEmpleado(carrito.getCliente().getId());
        Vendedor vendedor = empleadoService.getEmpleado(nuevoEmpleado);
        vendedoresRepository.save(vendedor);

        List<CarritoProducto> carritoProductos = carrito.getProductos();
        List<ProductoVenta> productosVenta = carritoProductos.stream().map(this::convertirCarritoProductoAProducto).toList();
        Float importe = (float) productosVenta.stream().mapToDouble(p -> p.getPrecioUnitario() * p.getCantidad()).sum();

        EstadoVenta estado = new EstadoVenta();
        repositorioDeEstados.save(estado);

        String transaccionId = procesamientoPagoService.procesarPago(importe);
        Pago pago = new Pago(compra.getMedioDePago(), LocalDate.now(), transaccionId, importe);
        pagosRepository.save(pago);

        EstadoVenta estadoPago = new EstadoVenta(Estado.PAGADO, Estado.A_PAGAR);
        repositorioDeEstados.save(estadoPago);

        LocalDate fechaGeneracion = LocalDate.now(ZoneId.of("GMT-3"));

        Venta nuevaVenta = Venta
                .builder()
                .estadoVenta(estado)
                .fechaGeneracion(fechaGeneracion)
                .fechaEntrega(null)
                .cliente(cliente)
                .vendedor(vendedor)
                .importe(importe)
                .pago(pago)
                .build();
        this.repositorioDeVentas.save(nuevaVenta);
        return new VentaOutputDTO(nuevaVenta);
   }

    public void cancelarVenta(Long id) {
        // TODO: independientemente del motivo? O solo para casos como estado a pagar?
    }

    public void cambiarEstadoVenta(Long id, Estado nuevoEstado){
       Optional<Venta> unaVenta = this.repositorioDeVentas.findById(id);
       if(unaVenta.isPresent()) {
           EstadoVenta nuevoEstadoVenta = new EstadoVenta(nuevoEstado, unaVenta.get().getEstadoVenta().getEstadoActual());
           unaVenta.get().setEstadoVenta(nuevoEstadoVenta);
           repositorioDeEstados.save(nuevoEstadoVenta);
           repositorioDeVentas.save(unaVenta.get());
       }
       else throw new VentaNoEncontradaException("Venta #" + id + " no encontrado");
   }

    public EstadoVentaOutputDTO buscarEstadoVenta(Long id){
        Optional<Venta> venta = this.repositorioDeVentas.findById(id);
        if(venta.isPresent()){
            return new EstadoVentaOutputDTO(venta.get().getEstadoVenta(), venta.get().getId());
        }
        throw new VentaNoEncontradaException("Venta #" + id + " no encontrado");
    }

    public ProductoVenta convertirCarritoProductoAProducto(CarritoProducto carritoProducto) {
        Long productoId = carritoProducto.getProducto().getId();
        ResponseEntity<ProductoRequestDTO> productoRequestDTO = productosClient.getProducto(productoId);
        Producto productoActualizado = productosService.getProducto(productoRequestDTO, productoId);

        ProductoVenta producto = ProductoVenta
                .builder()
                .producto(productoActualizado)
                .cantidad(carritoProducto.getCantidad())
                .precioUnitario(productoActualizado.getPrecio())
                .build();
        productoVentaRepository.save(producto);
       return producto;
   }

    @SneakyThrows
    public FacturaOutputDTO generarFactura(Long id) {
        System.out.println("Generando factura para venta " + id);
        Optional<Venta> ventaFinalizada = this.repositorioDeVentas.findById(id);

        if (ventaFinalizada.isPresent()) {
            GeneradorDeFacturas generador = new GeneradorDeFacturas();
            Venta venta = ventaFinalizada.get();
            cambiarEstadoVenta(venta.getId(), Estado.PAGADO);
            if (!venta.getEstadoVenta().getEstadoActual().equals(Estado.A_PAGAR)) {
                Factura nuevaFactura = Factura
                        .builder()
                        .fecha(LocalDate.now(ZoneId.of("GMT-3")))
                        .venta(venta)
                        .path("src/main/java/out/" + id + ".pdf")
                        .build();

                facturasRepository.save(nuevaFactura);
                generador.generarFactura(venta, id + ".pdf");

                return new FacturaOutputDTO(nuevaFactura);
            }
            else {
                throw new FacturaNoPermitida("Venta #" + id + " no pagada");
            }
        }
        else throw new VentaNoEncontradaException("Venta #" + id + " no encontrado");
    }
}
