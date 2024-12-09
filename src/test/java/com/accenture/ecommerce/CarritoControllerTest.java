package com.accenture.ecommerce;
import com.accenture.ecommerce.auth.JwtAuthenticationFilter;
import com.accenture.ecommerce.dtos.ventasDtos.CarritoOutputDTO;
import com.accenture.ecommerce.dtos.ventasDtos.CarritoProductoDTO;
import com.accenture.ecommerce.models.repositories.ventas.CarritosRepository;
import com.accenture.ecommerce.services.ventas.CarritosService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CarritoControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CarritosRepository carritoRepo;
    @Autowired
    private CarritosService carritoService;
    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Test
    void testCrearCarrito() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/carritos").contentType(MediaType.APPLICATION_JSON)).andReturn();
        String jsonResponse = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        CarritoOutputDTO outputDTO = objectMapper.readValue(jsonResponse, CarritoOutputDTO.class);
        LocalDate tiempoEsperado = LocalDate.from(LocalDateTime.now().plusDays(10).withHour(0).withMinute(0).withSecond(0).withNano(0));
        LocalDate tiempoAsignado = outputDTO.getFechaExpiracion();

        assertNotNull(outputDTO);
        assertEquals(1, outputDTO.getId());
        assertEquals(true, outputDTO.getActivo());
        assertEquals(tiempoEsperado, tiempoAsignado);
        assertEquals(0, outputDTO.getProductos().size());
    }

    @Test
    void testAgregarProducto() throws Exception{
        MvcResult mvcResult = mockMvc.perform(post("/carritos").contentType(MediaType.APPLICATION_JSON)).andReturn();
        String jsonResponse = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        CarritoOutputDTO outputDTO = objectMapper.readValue(jsonResponse, CarritoOutputDTO.class);

        Long idCarrito = outputDTO.getId();
        String productoJson = """
                {
                    "productoId": 3,
                    "cantidad": 1
                }""";

        MvcResult result2 = mockMvc.perform(post("/carritos/{idCarrito}/productos", idCarrito)
                .contentType(MediaType.APPLICATION_JSON).content(productoJson)).andExpect(status().isOk()).andReturn();

        MvcResult result3 = mockMvc.perform(get("/carritos/{idCarrito}", idCarrito))
                .andExpect(status().isOk()).andReturn();

        String jsonFinalResponse = result3.getResponse().getContentAsString();
        CarritoOutputDTO carritoActualizado = objectMapper.readValue(jsonFinalResponse, CarritoOutputDTO.class);
        CarritoProductoDTO productoAgregado = carritoActualizado.getProductos().get(0);

        assertNotNull(carritoActualizado);
        assertEquals(true, carritoActualizado.getActivo());
        assertEquals(1, carritoActualizado.getProductos().size());
        assertEquals(3, productoAgregado.getId());
        assertEquals(1, productoAgregado.getCantidad());

    }
}
