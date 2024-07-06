package com.example.TPO.DTO;

import com.example.TPO.model.Usuario;
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
    private Usuario responsableTecnico;
    private List<PeticionDTO> peticiones;
}
