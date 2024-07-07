package com.example.TPO.controller;

import com.example.TPO.DTO.ResultadoDTO;
import com.example.TPO.model.Resultado;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ResultadoController {
    private static ResultadoController instance;
    private final List<Resultado> resultados = new ArrayList<>();

    private ResultadoController() {
        // Constructor privado para Singleton
    }

    public static ResultadoController getInstance() {
        if (instance == null) {
            instance = new ResultadoController();
        }
        return instance;
    }

    public void agregarResultado(String id,String resultado,LocalDate fecha) {
        resultados.add(new Resultado(id,
                resultado,
                fecha)
        );
    }

    public boolean eliminarResultado(String id) {
        return resultados.removeIf(resultado -> resultado.getId().equals(id));
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

    protected Optional<Resultado> buscarResultadoPorId(String id) {
        return resultados.stream()
                .filter(resultado -> resultado.getId().equals(id))
                .findFirst();
    }

    public Optional<ResultadoDTO> getResultado(String id) {
        Optional<Resultado> resultado = buscarResultadoPorId(id);
        return resultado.map(ResultadoDTO::new);
    }

}
