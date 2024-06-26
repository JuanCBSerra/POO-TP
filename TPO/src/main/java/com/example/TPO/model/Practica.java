package com.example.TPO.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Practica {

    private int codigo;
    private String nombre;
    private String grupo;
    private ValorCritico valorCritico;
    private boolean valorReservado;
    private int cantidadHoras;
    private boolean estaHabilitada;

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

    public ValorCritico getValorCritico() {
        return valorCritico;
    }

    public void setValorCritico(ValorCritico valoresCriticos) {
        this.valorCritico = valoresCriticos;
    }

    public boolean getValorReservado() {
        return valorReservado;
    }

    public void setValorReservado(boolean valorReservado) {
        this.valorReservado = valorReservado;
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
