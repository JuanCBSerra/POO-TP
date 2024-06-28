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
    private Paciente paciente;
    private String obraSocial;
    private Date fechaCarga;
    private Date fechaCalculadaEntrega;
    private List<Integer> practicas;
    private ArrayList<Resultado> resultados;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(String obraSocial) {
        this.obraSocial = obraSocial;
    }

    public Date getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(Date fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    public Date getFechaCalculadaEntrega() {
        return fechaCalculadaEntrega;
    }

    public void setFechaCalculadaEntrega(Date fechaCalculadaEntrega) {
        this.fechaCalculadaEntrega = fechaCalculadaEntrega;
    }

    public List<Integer> getPracticas() {
        return practicas;
    }

    public void setPracticas(List<Integer> practicas) {
        this.practicas = practicas;
    }
}