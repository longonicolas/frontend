package com.accenture.ecommerce.models.entities.externos;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

    @Id
    private Long id;

    @Column
    private String nombre;

    @Column
    private Float precio;

}
