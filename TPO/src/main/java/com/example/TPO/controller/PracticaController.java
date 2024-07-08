package com.example.TPO.controller;

import com.example.TPO.DTO.PracticaDTO;
import com.example.TPO.model.Practica;
import com.example.TPO.model.ValorCritico;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class PracticaController {
    private static PracticaController instance;
    private final List<Practica> practicas = new ArrayList<>();

    private PracticaController() {
    }

    public static PracticaController getInstance() {
        if (instance == null) {
            instance = new PracticaController();
        }
        return instance;
    }

    public void agregarPractica(int codigo, String nombre, String grupo, ValorCritico valorCritico, boolean valorReservado, int cantidadHoras, boolean habilitada) {
        practicas.add(new Practica(
                codigo,
                nombre,
                grupo,
                valorCritico,
                valorReservado,
                cantidadHoras,
                habilitada
        ));
    }

    public void modificarPractica(int codigo, String nombre, String grupo, ValorCritico valorCritico, boolean valoresReservados, int cantidadHoras, boolean habilitada) {
        Optional<Practica> practicaExistente = buscarPracticaPorCodigo(codigo);
        if (practicaExistente.isPresent()) {
            Practica practica = practicaExistente.get();
            if(nombre != null){
                practica.setNombre(nombre);
            }
            if(grupo != null){
                practica.setGrupo(grupo);
            }
            if(valorCritico != null){
                practica.setValorCritico(valorCritico);
            }
            practica.setValorReservado(valoresReservados);
            practica.setCantidadHoras(cantidadHoras);
            practica.setHabilitada(habilitada);
        }
    }

    public void deshabilitarPractica(int codigo) {
        Optional<Practica> practicaExistente = buscarPracticaPorCodigo(codigo);
        if (practicaExistente.isPresent()) {
            Practica practica = practicaExistente.get();
            practica.setHabilitada(false);
        }
    }

    public boolean estaHabilitada(int codigo) {
        Optional<Practica> practica = buscarPracticaPorCodigo(codigo);
        return practica.map(Practica::isHabilitada).orElse(false);
    }

    protected Optional<Practica> buscarPracticaPorCodigo(int codigo) {
        return practicas.stream()
                .filter(practica -> practica.getCodigo() == codigo)
                .findFirst();
    }

    public Optional<PracticaDTO> getPractica(int codigo) {
        Optional<Practica> practica = buscarPracticaPorCodigo(codigo);
        return practica.map(PracticaDTO::new);
    }
}
