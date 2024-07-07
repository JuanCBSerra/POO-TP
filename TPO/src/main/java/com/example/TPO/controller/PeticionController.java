package com.example.TPO.controller;

import com.example.TPO.DTO.PeticionDTO;
import com.example.TPO.DTO.ResultadoDTO;
import com.example.TPO.model.Peticion;
import com.example.TPO.model.Practica;
import com.example.TPO.model.Resultado;

import java.time.LocalDate;
import java.util.*;

public class PeticionController {
    private static PeticionController instance;
    private final List<Peticion> peticiones = new ArrayList<>();
    private final List<Resultado> resultados = new ArrayList<>();

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

    public void agregarResultado(String idPeticion, String idResultado, String resultado, LocalDate fecha) {
        Resultado nuevoResultado = new Resultado(
                idResultado,
                resultado,
                fecha
        );
        Peticion peticion = buscarPeticionPorId(idPeticion).orElseThrow(() -> new RuntimeException("No existe la peticion"));
        peticion.agregarResultado(nuevoResultado);
        resultados.add(nuevoResultado);
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

    protected Optional<Resultado> buscarResultadoPorId(String id) {
        return resultados.stream()
                .filter(resultado -> resultado.getId().equals(id))
                .findFirst();
    }

    public void eliminarResultado(String id) {
        Resultado resultado = buscarResultadoPorId(id).orElseThrow(() -> new RuntimeException("No existe el resultado"));
        Peticion peticion = buscarPeticionConResultado(id);
        peticion.eliminarResultado(resultado);
        resultados.remove(resultado);
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

    public Optional<ResultadoDTO> getResultado(String id) {
        Optional<Resultado> resultado = buscarResultadoPorId(id);
        return resultado.map(ResultadoDTO::new);
    }

    public boolean modificarResultado(String id, String resultadoValue,LocalDate fecha) {
        Optional<Resultado> resultadoExistente = buscarResultadoPorId(id);

        if (resultadoExistente.isPresent()) {
            Resultado resultado = resultadoExistente.get();
            resultado.setId(id);
            resultado.setResultado(resultadoValue);
            return true;
        }
        return false;
    }

    private Peticion buscarPeticionConResultado(String idResultado){
        for (Peticion peticion : peticiones) {
            for (Resultado resultado : peticion.getResultados()) {
                if (Objects.equals(resultado.getId(), idResultado)) {
                    return peticion;
                }
            }
        }
        throw new RuntimeException("La peticion no existe");
    }
}
