package com.example.TPO.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resultado {

    private String id;
    private String resultado;
    private LocalDate fecha;
    private Practica practica;

    public String getResultadoSiNoEsReservado(){
        boolean esValorReservado = practica.isValorReservado();
        if(esValorReservado){
            return "Retirar por sucursal";
        }else{
            return resultado;
        }
    }

    public String getActualResultado(){
        return resultado;
    }


}