package com.example.TPO;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Practica {
    private int codigo;
    private String nombre;
    private String grupo;
    private ArrayList<String> valoresCriticos;
    private ArrayList<String> valoresReservados;
    private int cantidadHoras;
    private boolean estaHabilitada;
}
