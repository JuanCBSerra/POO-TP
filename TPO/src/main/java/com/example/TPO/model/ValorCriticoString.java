package com.example.TPO.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValorCriticoString extends ValorCritico{

    private String valorCritico;

    @Override
    public boolean esCritico(String valorResultado){
        return valorResultado.equals(valorCritico);
    }
}
