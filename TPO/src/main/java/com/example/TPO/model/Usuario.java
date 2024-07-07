package com.example.TPO.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    private String username;
    private String nombre;
    private String email;
    private String password;
    private String domicilio;
    private String dni;
    private Date fechaNacimiento;
    private Rol rol;

}