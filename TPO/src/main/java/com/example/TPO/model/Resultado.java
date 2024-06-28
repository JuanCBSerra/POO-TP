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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }


}