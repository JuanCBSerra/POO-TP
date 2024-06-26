package com.example.TPO.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValorCriticoNumerico extends ValorCritico{

    private int valorMinimo;
    private int valorMaximo;

    @Override
    public boolean esCritico(String valorResultado){
        int valorResultadoInt = Integer.parseInt(valorResultado);
        return valorResultadoInt < valorMinimo || valorResultadoInt > valorMaximo;
    }

}
