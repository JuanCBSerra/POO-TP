package com.example.TPO;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

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
}