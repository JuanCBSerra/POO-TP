package com.example.TPO.controller;

import com.example.TPO.model.Paciente;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PacienteController {

    private static PacienteController instance;
    private final List<Paciente> pacientes = new ArrayList<>();

    private PacienteController() {
    }

    public static PacienteController getInstance() {
        if (instance == null) {
            instance = new PacienteController();
        }
        return instance;
    }

    public void agregarPaciente(String dni, String nombre, String domicilio, String email, String sexo, int edad) {
        Paciente nuevoPaciente = new Paciente(
                dni,
                nombre,
                domicilio,
                email,
                sexo,
                new ArrayList<String>(),
                edad
        );

        pacientes.add(nuevoPaciente);
    }

    public Optional<Paciente> buscarPacientePorDni(String dni) {
        return pacientes.stream()
                .filter(p -> p.getDni().equals(dni))
                .findFirst();
    }

//    public boolean sePuedeEliminarPaciente(Paciente paciente) {
//        return peticionController.obtenerPeticionesPorPaciente(paciente).isEmpty();
//    }

    public boolean eliminarPaciente(String dni) {
        Optional<Paciente> paciente = buscarPacientePorDni(dni);
        //            if (!sePuedeEliminarPaciente(paciente.get())) {
        //                return false;
        //            }else{
        //                pacientes.remove(paciente.get());
        //                return true;
        //            }
        paciente.ifPresent(pacientes::remove);
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
        return pacientes;
    }
}