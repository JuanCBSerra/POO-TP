package com.example.TPO.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sucursal {

    private int numero;
    private String direccion;
    private String responsableTecnico;
    private List<String> peticiones = new ArrayList<>();

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getResponsableTecnico() {
        return responsableTecnico;
    }

    public void setResponsableTecnico(String responsableTecnico) {
        this.responsableTecnico = responsableTecnico;
    }

    public List<String> getPeticiones() {
        return peticiones;
    }

    public void setPeticiones(List<String> peticiones) {
        this.peticiones = peticiones;
    }
}