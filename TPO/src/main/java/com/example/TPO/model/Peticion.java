package com.example.TPO.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Peticion {

    private String id;
    private String obraSocial;
    private Date fechaCarga;
    private Date fechaCalculadaEntrega;
    private List<Practica> practicas;
    private ArrayList<Resultado> resultados;

}