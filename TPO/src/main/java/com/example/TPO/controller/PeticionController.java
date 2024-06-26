package com.example.TPO.controller;

import com.example.TPO.model.Paciente;
import com.example.TPO.model.Peticion;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PeticionController {
    private static PeticionController instance;
    private final List<Peticion> peticiones = new ArrayList<>();

    private PeticionController() {
    }

    public static PeticionController getInstance() {
        if (instance == null) {
            instance = new PeticionController();
        }
        return instance;
    }

    public void agregarPeticion(Peticion peticion) {
        peticiones.add(peticion);
    }

    public boolean modificarPeticion(String id, Peticion peticionActualizada) {
        Optional<Peticion> peticionExistente = buscarPeticionPorId(id);
        if (peticionExistente.isPresent()) {
            Peticion peticion = peticionExistente.get();
            peticion.setPaciente(peticionActualizada.getPaciente());
            peticion.setObraSocial(peticionActualizada.getObraSocial());
            peticion.setFechaCarga(peticionActualizada.getFechaCarga());
            peticion.setFechaCalculadaEntrega(peticionActualizada.getFechaCalculadaEntrega());
            peticion.setPracticas(peticionActualizada.getPracticas());
            return true;
        }
        return false;
    }

    public boolean eliminarPeticion(String id) {
        return peticiones.removeIf(peticion -> peticion.getId().equals(id));
    }

    public List<Peticion> obtenerPeticionesPorPaciente(Paciente paciente) {
        return peticiones.stream()
                .filter(peticion -> peticion.getPaciente().equals(paciente))
                .collect(Collectors.toList());
    }

    public Optional<Peticion> buscarPeticionPorId(String id) {
        return peticiones.stream()
                .filter(peticion -> peticion.getId().equals(id))
                .findFirst();
    }

    public List<Peticion> obtenerTodasLasPeticiones() {
        return new ArrayList<>(peticiones);
    }

}
