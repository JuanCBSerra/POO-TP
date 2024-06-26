package com.example.TPO;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Practica {
    private int codigo;
    private String nombre;
    private String grupo;
    private ArrayList<String> valoresCriticos;
    private ArrayList<String> valoresReservados;
    private int cantidadHoras;
    private boolean estaHabilitada;

    public Practica(int codigo, String nombre, String grupo, ArrayList<String> valoresCriticos, ArrayList<String> valoresReservados, int cantidadHoras, boolean estaHabilitada) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.grupo = grupo;
        this.valoresCriticos = valoresCriticos;
        this.valoresReservados = valoresReservados;
        this.cantidadHoras = cantidadHoras;
        this.estaHabilitada = estaHabilitada;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public ArrayList<String> getValoresCriticos() {
        return valoresCriticos;
    }

    public void setValoresCriticos(ArrayList<String> valoresCriticos) {
        this.valoresCriticos = valoresCriticos;
    }

    public ArrayList<String> getValoresReservados() {
        return valoresReservados;
    }

    public void setValoresReservados(ArrayList<String> valoresReservados) {
        this.valoresReservados = valoresReservados;
    }

    public int getCantidadHoras() {
        return cantidadHoras;
    }

    public void setCantidadHoras(int cantidadHoras) {
        this.cantidadHoras = cantidadHoras;
    }

    public boolean isEstaHabilitada() {
        return estaHabilitada;
    }

    public void setEstaHabilitada(boolean estaHabilitada) {
        this.estaHabilitada = estaHabilitada;
    }



}
