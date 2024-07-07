package com.example.TPO.DTO;

import com.example.TPO.model.Practica;
import com.example.TPO.model.ValorCritico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PracticaDTO {
    private int codigo;
    private String nombre;
    private String grupo;
    private ValorCritico valorCritico;
    private boolean valorReservado;
    private int cantidadHoras;
    private boolean habilitada;

    public PracticaDTO(Practica practica){
        this.codigo = practica.getCodigo();
        this.nombre = practica.getNombre();
        this.grupo = practica.getGrupo();
        this.valorCritico = practica.getValorCritico();
        this.valorReservado = practica.isValorReservado();
        this.cantidadHoras = practica.getCantidadHoras();;
        this.habilitada = practica.isHabilitada();
    }
}
