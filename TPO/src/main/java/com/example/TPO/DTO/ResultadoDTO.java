package com.example.TPO.DTO;

import com.example.TPO.model.Resultado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultadoDTO {

    private String id;
    private String resultado;
    private LocalDate fecha;

    public ResultadoDTO(Resultado resultado){
        this.id = resultado.getId();
        this.resultado = resultado.getResultado();
        this.fecha = resultado.getFecha();
    }

}
