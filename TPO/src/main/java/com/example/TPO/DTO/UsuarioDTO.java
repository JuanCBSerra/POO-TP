package com.example.TPO.DTO;

import com.example.TPO.model.Rol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private String username;
    private String nombre;
    private String email;
    private String password;
    private String domicilio;
    private String dni;
    private Date fechaNacimiento;
    private Rol rol;
}
