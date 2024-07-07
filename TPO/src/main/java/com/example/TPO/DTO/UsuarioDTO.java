package com.example.TPO.DTO;

import com.example.TPO.model.Rol;
import com.example.TPO.model.Usuario;
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
    private String domicilio;
    private String dni;
    private Date fechaNacimiento;
    private Rol rol;

    public UsuarioDTO(Usuario usuario){
        this.username = usuario.getUsername();
        this.nombre = usuario.getNombre();
        this.email = usuario.getEmail();
        this.domicilio = usuario.getDomicilio();
        this.dni = usuario.getDni();
        this.rol = usuario.getRol();
    }
}
