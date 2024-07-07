package com.example.TPO.DTO;

import com.example.TPO.model.Sucursal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SucursalDTO {
    private int numero;
    private String direccion;
    private UsuarioDTO responsableTecnico;
    private List<PeticionDTO> peticiones;

    public SucursalDTO(Sucursal sucursal){
        this.numero = sucursal.getNumero();
        this.direccion = sucursal.getDireccion();
        this.responsableTecnico = new UsuarioDTO(sucursal.getResponsableTecnico());
    }
}
