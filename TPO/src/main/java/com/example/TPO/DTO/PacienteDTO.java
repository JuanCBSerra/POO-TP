package com.example.TPO.DTO;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteDTO {

    private String dni;
    private String nombre;
    private String domicilio;
    private String email;
    private String sexo;
    private List<String> peticiones;
    private int edad;

}