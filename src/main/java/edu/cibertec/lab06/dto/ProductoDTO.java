package edu.cibertec.lab06.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {

    private long id;
    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;
    @Digits(integer = 5, fraction = 2, message = "El precio debe ser numero")
    @Min(value = 1, message = "El precio debe ser mayor que 0")
    private double precio;
    @Min(value = 1, message = "El stock debe ser mayor que 0")
    @Digits(integer = 4,fraction = 0, message = "El stock debe ser un numero entero")
    private int stock;
}
