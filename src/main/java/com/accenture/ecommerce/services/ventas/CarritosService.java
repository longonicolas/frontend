package com.accenture.ecommerce.services.ventas;
import com.accenture.ecommerce.clients.ProductosClient;
import com.accenture.ecommerce.dtos.*;
import com.accenture.ecommerce.dtos.ventasDtos.*;
import com.accenture.ecommerce.exceptions.*;
import com.accenture.ecommerce.models.entities.externos.Cliente;
import com.accenture.ecommerce.models.entities.ventas.Carrito;
import com.accenture.ecommerce.models.entities.ventas.CarritoProducto;
import com.accenture.ecommerce.models.entities.externos.Producto;
import com.accenture.ecommerce.models.entities.ventas.Pago;
import com.accenture.ecommerce.models.repositories.ventas.CarritosRepository;
import com.accenture.ecommerce.models.repositories.ventas.ClienteRepository;
import com.accenture.ecommerce.models.repositories.ventas.ProductosRepository;
import com.accenture.ecommerce.services.pagos.ProcesamientoPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class CarritosService implements ICarritosService {

    @Autowired
    private CarritosRepository carritosRepository;
    @Autowired
    private ProductosRepository productosRepository;
    @Autowired
    private ProductosClient productosClient;
    @Autowired
    private ProductosService productosService;
    @Autowired
    private IVentasService ventasService;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProcesamientoPagoService procesamientoPagoService;

    @Override
    public CarritoOutputDTO buscarCarrito(Long carritoId) {
        Optional<Carrito> carrito = carritosRepository.findById(carritoId);
        if (carrito.isPresent()) {
            if (carrito.get().getActivo()) return new CarritoOutputDTO(carrito.get());
            else throw new CarritoDeshabilitadoException("El carrito #" + carritoId + " está deshabilitado");
        } else {
            throw new CarritoNoEncontradoException("Carrito #" + carritoId + " no encontrado");
        }
    }

    @Override
    public CarritoOutputDTO agregarCarrito(CarritoNuevoInputDTO carritoNuevoInputDTO) {
        Cliente cliente = new Cliente(carritoNuevoInputDTO.getClienteId(), carritoNuevoInputDTO.getNombre());
        clienteRepository.save(cliente);
        Carrito carritoNuevo = new Carrito(cliente);
        carritosRepository.save(carritoNuevo);
        return new CarritoOutputDTO(carritoNuevo);
    }

    @Override
    public void agregarProducto(Long carritoId, ItemNuevoInputDTO itemNuevoInputDTO) {
        Optional<Carrito> carrito = carritosRepository.findById(carritoId);
        if (carrito.isPresent()) {
            Long productoId = itemNuevoInputDTO.getProductoId();
            Integer cantidad = itemNuevoInputDTO.getCantidad();
            Carrito carritoActual = carrito.get();
            List<CarritoProducto> productos = carritoActual.getProductos();
            CarritoProducto carritoProducto = productos.stream().filter(cp -> cp.getProducto().getId().equals(productoId)).findFirst().orElse(null);
            if (carritoProducto == null) {
                Optional<Producto> producto = productosRepository.findById(productoId);
                if (producto.isEmpty()) {
                    ResponseEntity<ProductoRequestDTO> productoRequestDTO = productosClient.getProducto(productoId);
                    Producto productoNuevo = productosService.getProducto(productoRequestDTO, productoId);
                    productosRepository.save(productoNuevo);
                    producto = productosRepository.findById(productoId);
                }
                CarritoProducto nuevoCarritoProducto = new CarritoProducto(carritoActual, producto.get(), cantidad);
                carritoActual.getProductos().add(nuevoCarritoProducto);
                carritosRepository.save(carritoActual);
            }
            else {
                throw new ProductoYaAgregadoException("Producto #" + productoId + " ya existe en la carrito");
            }
        } else {
            throw new CarritoNoEncontradoException("Carrito #" + carritoId + " no encontrado");
        }
    }

    @Override
    public void actualizarCantidad(Long carritoId, ItemUpdateInputDTO itemUpdateInputDTO) {
        Optional<Carrito> carrito = carritosRepository.findById(carritoId);
        if (carrito.isPresent()) {
            Long itemId = itemUpdateInputDTO.getItemId();
            Integer nuevaCantidad = itemUpdateInputDTO.getNuevaCantidad();
            Carrito carritoActual = carrito.get();
            List<CarritoProducto> productos = carritoActual.getProductos();
            CarritoProducto carritoProducto = productos.stream().filter(cp -> cp.getId().equals(itemId)).findFirst().orElse(null);
            if (carritoProducto == null) {
                throw new CarritoProductoNoEncontradoException("Producto no encontrado en carrito #" + carritoId);
            } else {
                carritoProducto.setCantidad(nuevaCantidad);
                carritosRepository.save(carritoActual);
            }
        } else {
            throw new CarritoNoEncontradoException("Carrito #" + carritoId + " no encontrado");
        }
    }

    @Override
    public void eliminarProducto(Long carritoId, Long itemId) {
        Optional<Carrito> carrito = carritosRepository.findById(carritoId);
        if (carrito.isPresent()) {
            Carrito carritoActual = carrito.get();
            List<CarritoProducto> productos = carritoActual.getProductos();
            CarritoProducto carritoProducto = productos.stream().filter(cp -> cp.getId().equals(itemId)).findFirst().orElse(null);
            if (carritoProducto == null) {
                throw new CarritoProductoNoEncontradoException("Item #" + itemId + " no encontrado en carrito #" + carritoId);
            }
            else {
                carritoActual.getProductos().remove(carritoProducto);
                carritosRepository.save(carritoActual);
            }
        }
    }

    @Override
    public void deshabilitarCarrito(Long carritoId) {
        Optional<Carrito> carrito = carritosRepository.findById(carritoId);
        if (carrito.isPresent()) {
            carrito.get().deshabilitar();
            carritosRepository.save(carrito.get());
        } else {
            throw new CarritoNoEncontradoException("Carrito #" + carritoId + " no encontrado");
        }
    }

    @Override
    public void habilitarCarrito(Long carritoId) {
        Optional<Carrito> carrito = carritosRepository.findById(carritoId);
        if (carrito.isPresent()) {
            carrito.get().habilitar();
            carritosRepository.save(carrito.get());
        } else {
            throw new CarritoNoEncontradoException("Carrito #" + carritoId + " no encontrado");
        }
    }

    @Override
    public VentaOutputDTO finalizarCompra(Long carritoId, CompraInputDTO compraInputDTO) {
       Optional<Carrito> carritoOptional = carritosRepository.findById(carritoId);
       if (carritoOptional.isEmpty()) {
           throw new CarritoNoEncontradoException("Carrito #" + carritoId + " no encontrado");
       }
       Carrito carrito =  carritoOptional.get();
       if (!carrito.getActivo()) {
           throw new CarritoDeshabilitadoException("El carrito #" + carritoId + " está deshabilitado");
       }
       if (carrito.getProductos().isEmpty()) {
           throw new CarritoVacioException("El carrito #" + carritoId + "está vacío");
       }

       return ventasService.crearVenta(carrito, carrito.getCliente(), compraInputDTO);
    }
}