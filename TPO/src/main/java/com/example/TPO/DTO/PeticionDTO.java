package com.example.TPO.DTO;

import com.example.TPO.model.Peticion;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeticionDTO {

    private String id;
    private String obraSocial;
    private Date fechaCarga;
    private Date fechaCalculadaEntrega;
    private List<PracticaDTO> practicas;
    private ArrayList<ResultadoDTO> resultados;

    public PeticionDTO(Peticion peticion) {
        this.id = peticion.getId();
        this.obraSocial = peticion.getObraSocial();
        this.fechaCarga = peticion.getFechaCarga();
        this.fechaCalculadaEntrega = peticion.getFechaCalculadaEntrega();
        this.practicas = peticion.getPracticas().stream().map(PracticaDTO::new).collect(Collectors.toList());
    }
}