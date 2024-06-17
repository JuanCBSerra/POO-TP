package com.example.TPO;

import com.example.TPO.Paciente;
import com.example.TPO.Practica;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Peticion {
    private String id;
    private Paciente paciente;
    private String obraSocial;
    private LocalDate fechaCarga;
    private LocalDate fechaCalculadaEntrega;
    private List<Practica> practicas;
}