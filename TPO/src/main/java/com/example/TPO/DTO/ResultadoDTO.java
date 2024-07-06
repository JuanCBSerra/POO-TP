package com.example.TPO.DTO;

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

}
