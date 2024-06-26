package com.example.TPO.controller;

import com.example.TPO.model.Paciente;
import com.example.TPO.model.Sucursal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SucursalController {

    private static SucursalController instance;
    private final List<Sucursal> sucursales = new ArrayList<>();

    private SucursalController() {
    }

    public static SucursalController getInstance() {
        if (instance == null) {
            instance = new SucursalController();
        }
        return instance;
    }

    public void agregarSucursal(int numero, String direccion, String responsableTecnico){
        Sucursal nuevaSucursal = new Sucursal(
                numero,
                direccion,
                responsableTecnico,
                new ArrayList<>()
        );
        sucursales.add(nuevaSucursal);
    }

    public Optional<Sucursal> buscarSucursalPorNumero(int numero) {
        return sucursales.stream()
                .filter(p -> p.getNumero() == numero)
                .findFirst();
    }

    public boolean eliminarSucursal(int numero) {
        Optional<Sucursal> sucursal = buscarSucursalPorNumero(numero);
        if (sucursal.isPresent()) {
            sucursales.remove(sucursal.get());
            return true;
        } else {
            return false;
        }
    }

    public boolean modificarSucursal(int numero, String direccion, String responsableTecnico) {
        Optional<Sucursal> sucursalOpt = buscarSucursalPorNumero(numero);
        if (sucursalOpt.isPresent()) {
            Sucursal sucursal = sucursalOpt.get();
            if (direccion != null) {
                sucursal.setDireccion(direccion);
            }
            if (responsableTecnico != null) {
                sucursal.setResponsableTecnico(responsableTecnico);
            }
            return true;
        }
        return false;
    }

    public List<Sucursal> obtenerTodasLasSucursales() {
        return sucursales;
    }
}