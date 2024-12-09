package com.accenture.ecommerce;
import com.accenture.ecommerce.dtos.PersonaRankingOutputDTO;
import com.accenture.ecommerce.models.entities.estadisticas.CreadorEstadistica;
import com.accenture.ecommerce.models.entities.estadisticas.MayoresVentasStrategy;
import com.accenture.ecommerce.models.entities.externos.Cliente;
import com.accenture.ecommerce.models.entities.externos.Producto;
import com.accenture.ecommerce.models.entities.externos.Vendedor;
import com.accenture.ecommerce.models.entities.usuarios.Rol;
import com.accenture.ecommerce.models.entities.usuarios.TipoRol;
import com.accenture.ecommerce.models.entities.usuarios.Usuario;
import com.accenture.ecommerce.models.entities.ventas.*;
import com.accenture.ecommerce.models.repositories.usuarios.RolRepository;
import com.accenture.ecommerce.models.repositories.usuarios.UsuarioRepository;
import com.accenture.ecommerce.models.repositories.ventas.*;
import com.accenture.ecommerce.services.usuarios.IUsuarioService;
import com.accenture.ecommerce.services.usuarios.UsuarioService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@SpringBootTest
class EcommerceApplicationTests {
	private static final Logger logger = LoggerFactory.getLogger(EcommerceApplicationTests.class);

	@Autowired
	private CreadorEstadistica creadorEstadistica;

	@Autowired
	private MayoresVentasStrategy mayoresVentasStrategy;
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ProductosRepository productosRepository;

	@Autowired
	private VentasRepository ventasRepository;

	@Autowired
	private ClienteRepository clientesRepository;

    @Autowired
    private VendedoresRepository vendedoresRepository;

    @Autowired
    private CarritosRepository carritosRepository;


	@Autowired
	private IUsuarioService usuarioService;

	 
	@Autowired
	private RolRepository rolRepository;
    @Autowired
    private EstadoVentaRepository estadoVentaRepository;
    @Autowired
    private ProductoVentaRepository productoVentaRepository;

	@Test
	void crearUsuario(){
		Usuario user = new Usuario("usuario", "1234");
		usuarioService.agregarRol("usuario", "CLIENTE");
		usuarioRepository.save(user);
	}

	@Test
	void agregarProductosABase() {
		Producto cocaCola = new Producto(1L, "Coca Cola", 1150F);
		Producto sevenUP = new Producto(2L, "Seven Up", 1050F);
		Producto fernetBranca = new Producto(3L, "Fernet Branca", 10500F);
		productosRepository.save(sevenUP);
		productosRepository.save(cocaCola);
		productosRepository.save(fernetBranca);
	}

	@Test
	void crearCarritoYAgregarProductos(){

		Producto cocaCola = new Producto(1L, "Coca Cola", 1150F);
		Producto sevenUP = new Producto(2L, "Seven Up", 1050F);

		Carrito carritoTest = new Carrito();

		CarritoProducto prod1 = new CarritoProducto(carritoTest, cocaCola, 2);
		CarritoProducto prod2 = new CarritoProducto(carritoTest,  sevenUP, 3);

		List<CarritoProducto> productos = new ArrayList<>();
		productos.add(prod1);
		productos.add(prod2);

		carritoTest.setProductos(productos);

		carritosRepository.save(carritoTest);
	}

    @Test
    void agregarVendedoresYClientes(){
        Cliente cliente1 = new Cliente();
        cliente1.setNombre("Juan Pérez");

        Cliente cliente2 = new Cliente();
        cliente2.setNombre("Ana García");

        Cliente cliente3 = new Cliente();
        cliente3.setNombre("Carlos Rodríguez");

        Cliente cliente4 = new Cliente();
        cliente4.setNombre("María López");

        clientesRepository.save(cliente1);
        clientesRepository.save(cliente2);
        clientesRepository.save(cliente3);
        clientesRepository.save(cliente4);

        Vendedor vendedor1 = new Vendedor();
        vendedor1.setNombre("Pablo Motos");

        Vendedor vendedor2 = new Vendedor();
        vendedor2.setNombre("Pedro");

        Vendedor vendedor3 = new Vendedor();
        vendedor3.setNombre("Jorge Carrascal");

        Vendedor vendedor4 = new Vendedor();
        vendedor4.setNombre("Hugo");

        vendedoresRepository.save(vendedor1);
        vendedoresRepository.save(vendedor2);
        vendedoresRepository.save(vendedor3);
        vendedoresRepository.save(vendedor4);
    }

	@Test
	void agregarVenta(){
		// Creación de clientes
		Cliente cliente1 = new Cliente();
		cliente1.setNombre("Juan Pérez");

		Cliente cliente2 = new Cliente();
		cliente2.setNombre("Ana García");

		Cliente cliente3 = new Cliente();
		cliente3.setNombre("Carlos Rodríguez");

		Cliente cliente4 = new Cliente();
		cliente4.setNombre("María López");

		clientesRepository.save(cliente1);
		clientesRepository.save(cliente2);
		clientesRepository.save(cliente3);
		clientesRepository.save(cliente4);

		// Creación de vendedores
		Vendedor vendedor1 = new Vendedor();
		vendedor1.setNombre("Pablo Motos");

		Vendedor vendedor2 = new Vendedor();
		vendedor2.setNombre("Pedro");

		Vendedor vendedor3 = new Vendedor();
		vendedor3.setNombre("Jorge Carrascal");

		Vendedor vendedor4 = new Vendedor();
		vendedor4.setNombre("Hugo");

		vendedoresRepository.save(vendedor1);
		vendedoresRepository.save(vendedor2);
		vendedoresRepository.save(vendedor3);
		vendedoresRepository.save(vendedor4);

// Creación de productos
		Producto cocaCola = new Producto(1L, "Coca Cola", 1150F);
		Producto sevenUP = new Producto(2L, "Seven Up", 1050F);
		Producto fernetBranca = new Producto(3L, "Fernet Branca", 10500F);

// Creación de productos de venta
		ProductoVenta prod1 = new ProductoVenta(1L, cocaCola, null, 2, 1150F);
		ProductoVenta prod2 = new ProductoVenta(2L, sevenUP, null, 1, 1050F);
		ProductoVenta prod3 = new ProductoVenta(3L, fernetBranca, null, 1, 10500F);

// Guardar productos de venta de manera correcta
		productoVentaRepository.save(prod1);
		productoVentaRepository.save(prod2);
		productoVentaRepository.save(prod3);

// Crear estado de venta
		EstadoVenta estado1 = new EstadoVenta(Estado.A_PAGAR, Estado.A_PAGAR);
		estadoVentaRepository.save(estado1);

// Crear venta
		Venta venta1 = new Venta();

// Asignar productos a la venta
		List<ProductoVenta> productos = new ArrayList<>();
		productos.add(prod1);
		productos.add(prod2);
		productos.add(prod3);

// Asignar estado de venta
		venta1.setEstadoVenta(estado1);

// Asignar cliente y vendedor
		venta1.setCliente(clientesRepository.findById(1L).get());
		venta1.setVendedor(vendedoresRepository.findById(1L).get());

// Fecha y medio de pago
		venta1.setFechaGeneracion(LocalDate.now());
		//venta1.setMedioDePago(MedioDePago.EFECTIVO);

// Asignar productos a la venta (se hace antes de guardar)
		venta1.setProductos(productos);

// Persistir la venta
		ventasRepository.save(venta1);

// Actualizar productos de venta para asociarlos con la venta
		prod1.setVenta(venta1);
		prod2.setVenta(venta1);
		prod3.setVenta(venta1);

// Guardar productos con la relación establecida
		productoVentaRepository.save(prod1);
		productoVentaRepository.save(prod2);
		productoVentaRepository.save(prod3);


		// -------- //


        ProductoVenta p1 = new ProductoVenta(1L, cocaCola, null, 2, 1150F);
        ProductoVenta p2 = new ProductoVenta(2L, sevenUP, null, 1, 1050F);
        ProductoVenta p3 = new ProductoVenta(3L, fernetBranca, null, 1, 10500F);
        ProductoVenta p4 = new ProductoVenta(2L, sevenUP, null, 1, 1050F);
        ProductoVenta p5 = new ProductoVenta(3L, fernetBranca, null, 1, 10500F);
		productoVentaRepository.save(p1);
		productoVentaRepository.save(p2);
		productoVentaRepository.save(p3);
		productoVentaRepository.save(p4);
		productoVentaRepository.save(p5);

		EstadoVenta estado2 = new EstadoVenta(Estado.A_PAGAR, Estado.A_PAGAR);
		estadoVentaRepository.save(estado2);

		Venta venta2 = new Venta();
        List<ProductoVenta> productos2 = new ArrayList<>();
        productos.add(p1);
        productos.add(p2);
        productos.add(p3);
		productos.add(p4);
		productos.add(p5);

        venta2.setProductos(productos2);
		venta2.setEstadoVenta(estado2);
		venta2.setCliente(clientesRepository.findById(1L).get());
		venta2.setVendedor(vendedoresRepository.findById(2L).get());
		venta2.setFechaGeneracion(LocalDate.now());
		//venta2.setMedioDePago(MedioDePago.EFECTIVO);

        ventasRepository.save(venta2);
		p1.setVenta(venta2);
		p2.setVenta(venta2);
		p3.setVenta(venta2);
		p4.setVenta(venta2);
		p5.setVenta(venta2);
		productoVentaRepository.save(p1);
		productoVentaRepository.save(p2);
		productoVentaRepository.save(p3);
		productoVentaRepository.save(p4);
		productoVentaRepository.save(p5);



		ProductoVenta pp = new ProductoVenta(1L, cocaCola, null, 2, 1150F);
		productoVentaRepository.save(pp);
		EstadoVenta estado3 = new EstadoVenta(Estado.A_PAGAR, Estado.A_PAGAR);
		estadoVentaRepository.save(estado3);
		Venta venta3 = new Venta();
		List<ProductoVenta> productos3 = new ArrayList<>();
		productos.add(pp);
		venta3.setProductos(productos3);
		venta3.setEstadoVenta(estado3);
		venta3.setCliente(clientesRepository.findById(1L).get());
		venta3.setVendedor(vendedoresRepository.findById(2L).get());
		venta3.setFechaGeneracion(LocalDate.now());
		//venta3.setMedioDePago(MedioDePago.EFECTIVO);
		ventasRepository.save(venta3);
		p1.setVenta(venta3);
		productoVentaRepository.save(pp);



		creadorEstadistica.setEstrategia(mayoresVentasStrategy);
		List< PersonaRankingOutputDTO> ranking = creadorEstadistica.crearEstadistica(LocalDate.now().minusDays(1), LocalDate.now(), 2);
		//logger.info("Ranking de vendedores:");
		//ranking.forEach(dto -> logger.info(dto.toString()));
	}


}

