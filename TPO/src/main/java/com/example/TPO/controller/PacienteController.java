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
                new ArrayList<>(),
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
        if (paciente.isPresent()) {
            pacientes.remove(paciente.get());
            return true;
        } else {
            return false;
        }
    }

    public boolean modificarPaciente(String dni, String nombre, String domicilio, String email, String sexo, int edad) {
        Optional<Paciente> pacienteOpt = buscarPacientePorDni(dni);
        if (pacienteOpt.isPresent()) {
            Paciente paciente = pacienteOpt.get();
            if (nombre != null) {
                paciente.setNombre(nombre);
            }
            if (domicilio != null) {
                paciente.setDomicilio(domicilio);
            }
            if (email != null) {
                paciente.setEmail(email);
            }
            if (sexo != null) {
                paciente.setSexo(sexo);
            }
            if (edad >= 0) {
                paciente.setEdad(edad);
            }
            return true;
        }
        return false;
    }

    public List<Paciente> obtenerTodosLosPacientes() {
        return pacientes;
    }
}