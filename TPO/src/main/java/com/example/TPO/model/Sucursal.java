package com.example.TPO.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sucursal {

    private int numero;
    private String direccion;
    private Usuario responsableTecnico;
    private List<Peticion> peticiones;

}