package com.example.TPO.DTO;

import com.example.TPO.model.Paciente;
import com.example.TPO.model.Peticion;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteDTO {
    private String dni;
    private String nombre;
    private String domicilio;
    private String email;
    private String sexo;
    private List<PeticionDTO> peticiones;
    private int edad;

    public PacienteDTO(Paciente paciente) {
        this.dni = paciente.getDni();
        this.nombre = paciente.getNombre();
        this.domicilio = paciente.getDomicilio();
        this.email = paciente.getEmail();
        this.sexo = paciente.getSexo();
        this.edad = paciente.getEdad();
        this.peticiones = paciente.getPeticiones().stream()
                .map(PeticionDTO::new)
                .collect(Collectors.toList());
    }
}