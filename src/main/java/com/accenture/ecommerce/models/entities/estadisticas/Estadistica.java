package com.accenture.ecommerce.models.entities.estadisticas;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Estadistica {

    @Id
    private Long id;

    @Column
    private LocalDate fechaCreacion;

    @Column
    private String tipo;

    @Column
    private String path;

    public Estadistica(String unTipo){
        this.tipo = unTipo;
        fechaCreacion = LocalDate.now();
    }


}