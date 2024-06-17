package com.example.TPO.controller;

import com.example.TPO.Paciente;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PacienteController {
    private static PacienteController instance;
    private final List<Paciente> pacientes = new ArrayList<>();
    private final PeticionController peticionController;

    private PacienteController() {
        // Constructor privado para Singleton
        peticionController = PeticionController.getInstance();
    }

    public static PacienteController getInstance() {
        if (instance == null) {
            instance = new PacienteController();
        }
        return instance;
    }

    public void agregarPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }

    public Optional<Paciente> buscarPacientePorDni(String dni) {
        return pacientes.stream()
                .filter(p -> p.getDni().equals(dni))
                .findFirst();
    }

    public boolean sePuedeEliminarPaciente(Paciente paciente) {
        return peticionController.obtenerPeticionesPorPaciente(paciente).isEmpty();
    }

    public boolean eliminarPaciente(String dni) {
        Optional<Paciente> paciente = buscarPacientePorDni(dni);
        if (paciente.isPresent()) {
            if (!sePuedeEliminarPaciente(paciente.get())) {
                return false;
            }else{
                pacientes.remove(paciente.get());
                return true;
            }
        }
        return false;
    }

    public boolean modificarPaciente(Paciente pacienteActualizado) {
        Optional<Paciente> pacienteExistente = buscarPacientePorDni(pacienteActualizado.getDni());
        if (pacienteExistente.isPresent()) {
            Paciente paciente = pacienteExistente.get();
            paciente.setNombre(pacienteActualizado.getNombre());
            paciente.setDomicilio(pacienteActualizado.getDomicilio());
            paciente.setEmail(pacienteActualizado.getEmail());
            paciente.setSexo(pacienteActualizado.getSexo());
            paciente.setEdad(pacienteActualizado.getEdad());
            return true;
        }
        return false;
    }

    public List<Paciente> obtenerTodosLosPacientes() {
        return new ArrayList<>(pacientes);
    }
}