package com.example.TPO.DTO;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeticionDTO {

    private String id;
    private PacienteDTO paciente;
    private String obraSocial;
    private Date fechaCarga;
    private Date fechaCalculadaEntrega;
    private List<PracticaDTO> practicas;
    private ArrayList<ResultadoDTO> resultados;

}