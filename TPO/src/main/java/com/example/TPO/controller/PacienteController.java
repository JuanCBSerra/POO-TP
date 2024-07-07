package com.example.TPO.controller;

import com.example.TPO.DTO.PacienteDTO;
import com.example.TPO.model.Paciente;
import com.example.TPO.model.Peticion;

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

    protected Optional<Paciente> buscarPacientePorDni(String dni) {
        return pacientes.stream()
                .filter(p -> p.getDni().equals(dni))
                .findFirst();
    }

    protected void agregarPeticionAPaciente(String dniPaciente, Peticion nuevaPeticion) {
        Optional<Paciente> paciente = buscarPacientePorDni(dniPaciente);
        if (paciente.isEmpty()) {
            throw new RuntimeException("Paciente con DNI " + dniPaciente + " no encontrado");
        }
        paciente.get().agregarPeticion(nuevaPeticion);
    }


    public Optional<PacienteDTO> getPaciente(String dni) {
        Optional<Paciente> paciente = buscarPacientePorDni(dni);
        if(paciente.isPresent()) {
            PacienteDTO pacienteDTO = new PacienteDTO(paciente.get());
            return Optional.of(pacienteDTO);
        }else {
            return Optional.empty();
        }
    }

    public boolean eliminarPaciente(String dni) {
        Optional<Paciente> paciente = buscarPacientePorDni(dni);
        if (paciente.isPresent()) {
            pacientes.remove(paciente.get());
            return true;
        } else {
            return false;
        }
    }

    public void modificarPaciente(String dni, String nombre, String domicilio, String email, String sexo, int edad) {
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
        }
    }

}