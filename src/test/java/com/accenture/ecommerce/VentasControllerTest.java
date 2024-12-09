package com.accenture.ecommerce;


import com.accenture.ecommerce.auth.JwtAuthenticationFilter;
import com.accenture.ecommerce.models.repositories.ventas.VentasRepository;
import com.accenture.ecommerce.services.ventas.VentasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class VentasControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private VentasRepository ventasRepo;
    @Autowired
    private VentasService ventaService;
    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;


}
