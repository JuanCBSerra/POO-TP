package com.example.TPO.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Iterator;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sucursal {

    private int numero;
    private String direccion;
    private Usuario responsableTecnico;
    private List<Peticion> peticiones;

    public void agregarPeticion(Peticion peticion){
        this.peticiones.add(peticion);
    }

    public boolean sePuedeEliminar(){
        boolean sePuedeEliminar = true;
        for(Peticion peticion: peticiones){
            List<Resultado> resultados = peticion.getResultados();
            if (!resultados.isEmpty()) {
                sePuedeEliminar = false;
                break;
            }
        }
        return sePuedeEliminar;
    }

    public void derivarPeticiones(Sucursal sucursalADerivar) {
        Iterator<Peticion> iterator = peticiones.iterator();
        while (iterator.hasNext()) {
            Peticion peticion = iterator.next();
            sucursalADerivar.agregarPeticion(peticion);
            iterator.remove();
        }
    }
}