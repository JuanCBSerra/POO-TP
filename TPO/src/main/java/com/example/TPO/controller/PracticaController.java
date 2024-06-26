package com.example.TPO.controller;

import com.example.TPO.model.Practica;

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

    public void agregarPractica(Practica practica) {
        practicas.add(practica);
    }

    public boolean modificarPractica(int codigo, Practica practicaActualizada) {
        Optional<Practica> practicaExistente = buscarPracticaPorCodigo(codigo);
        if (practicaExistente.isPresent()) {
            Practica practica = practicaExistente.get();
            practica.setNombre(practicaActualizada.getNombre());
            practica.setGrupo(practicaActualizada.getGrupo());
            practica.setValorCritico(practicaActualizada.getValorCritico());
            practica.setValorReservado(practicaActualizada.getValorReservado());
            practica.setCantidadHoras(practicaActualizada.getCantidadHoras());
            practica.setEstaHabilitada(practicaActualizada.isEstaHabilitada());
            return true;
        }
        return false;
    }

    public boolean deshabilitarPractica(int codigo) {
        Optional<Practica> practicaExistente = buscarPracticaPorCodigo(codigo);
        if (practicaExistente.isPresent()) {
            Practica practica = practicaExistente.get();
            practica.setEstaHabilitada(false);
            return true;
        }
        return false;
    }

    public Optional<Practica> buscarPracticaPorCodigo(int codigo) {
        return practicas.stream()
                .filter(practica -> practica.getCodigo() == codigo)
                .findFirst();
    }

}
