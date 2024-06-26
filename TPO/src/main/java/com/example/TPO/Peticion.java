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

    public Peticion(String id, Paciente paciente, String obraSocial, LocalDate fechaCarga, LocalDate fechaCalculadaEntrega, List<Practica> practicas) {
        this.id = id;
        this.paciente = paciente;
        this.obraSocial = obraSocial;
        this.fechaCarga = fechaCarga;
        this.fechaCalculadaEntrega = fechaCalculadaEntrega;
        this.practicas = practicas;
    }

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

    public LocalDate getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(LocalDate fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    public LocalDate getFechaCalculadaEntrega() {
        return fechaCalculadaEntrega;
    }

    public void setFechaCalculadaEntrega(LocalDate fechaCalculadaEntrega) {
        this.fechaCalculadaEntrega = fechaCalculadaEntrega;
    }

    public List<Practica> getPracticas() {
        return practicas;
    }

    public void setPracticas(List<Practica> practicas) {
        this.practicas = practicas;
    }
}