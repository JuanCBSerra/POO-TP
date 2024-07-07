package com.example.TPO.controller;

import com.example.TPO.DTO.PeticionDTO;
import com.example.TPO.model.Peticion;
import com.example.TPO.model.Practica;

import java.util.*;

public class PeticionController {
    private static PeticionController instance;
    private final List<Peticion> peticiones = new ArrayList<>();
    private static final PracticaController practicaController = PracticaController.getInstance();
    private static final PacienteController pacienteController = PacienteController.getInstance();
    private static final SucursalController sucursalController = SucursalController.getInstance();

    private PeticionController() {}

    public static PeticionController getInstance() {
        if (instance == null) {
            instance = new PeticionController();
        }
        return instance;
    }

    public void agregarPeticion(String dniPaciente, int numeroSucursal, String idPeticionString, String obraSocialString, Date fechaCarga, Date fechaEntrega, String[] codigosPracticas) {
        List<Practica> practicas = obtenerPracticasPorCodigos(codigosPracticas);
        Peticion nuevaPeticion = new Peticion(
                idPeticionString,
                obraSocialString,
                fechaCarga,
                fechaEntrega,
                practicas,
                new ArrayList<>()
        );
        pacienteController.agregarPeticionAPaciente(dniPaciente, nuevaPeticion);
        sucursalController.agregarPeticionASucursal(numeroSucursal, nuevaPeticion);
        this.peticiones.add(nuevaPeticion);
    }

    public void modificarPeticion(String id, String obraSocial, Date fechaCalculadaEntrega, String[] codigosPracticas) {
        Optional<Peticion> peticionExistente = buscarPeticionPorId(id);
        if (peticionExistente.isPresent()) {
            Peticion peticion = peticionExistente.get();
            if (obraSocial != null) {
                peticion.setObraSocial(obraSocial);
            }
            if (fechaCalculadaEntrega != null) {
                peticion.setFechaCalculadaEntrega(fechaCalculadaEntrega);
            }
            List<Practica> practicas = obtenerPracticasPorCodigos(codigosPracticas);
            peticion.setPracticas(practicas);
        } else {
            throw new IllegalArgumentException("Petición no encontrada: " + id);
        }
    }

    public void eliminarPeticion(String id) {
        peticiones.removeIf(peticion -> peticion.getId().equals(id));
    }

    protected Optional<Peticion> buscarPeticionPorId(String id) {
        return peticiones.stream()
                .filter(peticion -> peticion.getId().equals(id))
                .findFirst();
    }

    public Optional<PeticionDTO> getPeticion(String id) {
        Optional<Peticion> peticion = buscarPeticionPorId(id);
        return peticion.map(PeticionDTO::new);
    }

    private List<Practica> obtenerPracticasPorCodigos(String[] codigosPracticas) {
        List<Practica> practicas = new ArrayList<>();
        for (String practicaCodigo : codigosPracticas) {
            practicaCodigo = practicaCodigo.trim();
            if (!practicaCodigo.isEmpty()) {
                Optional<Practica> practicaOpt = practicaController.buscarPracticaPorCodigo(Integer.parseInt(practicaCodigo));
                practicaOpt.ifPresent(practicas::add);
            }
        }
        return practicas;
    }
}
