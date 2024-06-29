package com.example.TPO.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Practica {

    private int codigo;
    private String nombre;
    private String grupo;
    private ValorCritico valorCritico;
    private boolean valorReservado;
    private int cantidadHoras;
    private boolean estaHabilitada;

}
