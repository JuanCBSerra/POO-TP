package com.example.TPO.model;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {

    private String dni;
    private String nombre;
    private String domicilio;
    private String email;
    private String sexo;
    private List<Peticion> peticiones;
    private int edad;

    public void agregarPeticion(Peticion nuevaPeticion){
        this.peticiones.add(nuevaPeticion);
    }
}