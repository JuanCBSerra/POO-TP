package com.example.TPO.model;

import com.example.TPO.model.Peticion;
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

    public String getResultado(){
        boolean esValorReservado = practica.isValorReservado();
        if(esValorReservado){
            return "Retirar por sucursal";
        }else{
            return resultado;
        }
    }


}