package com.example.TPO;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    private String username;
    private String email;
    private String password;
    private String nombre;
    private String domicilio;
    private String dni;
    private LocalDate fechaNacimiento;
    private Rol rol;
}