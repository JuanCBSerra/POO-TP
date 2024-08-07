package com.example.TPO.controller;

import com.example.TPO.DTO.SucursalDTO;
import com.example.TPO.model.Peticion;
import com.example.TPO.model.Sucursal;
import com.example.TPO.model.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SucursalController {

    private static SucursalController instance;
    private final List<Sucursal> sucursales = new ArrayList<>();
    private final UsuarioController usuarioController = UsuarioController.getInstance();

    public static SucursalController getInstance() {
        if (instance == null) {
            instance = new SucursalController();
        }
        return instance;
    }

    public void agregarSucursal(int numero, String direccion, String responsableTecnicoUsername) throws Exception {
        Optional<Usuario> responsableTecnicoOpt = usuarioController.buscarUsuarioPorUsername(responsableTecnicoUsername);
        if(responsableTecnicoOpt.isPresent()){
            Sucursal nuevaSucursal = new Sucursal(
                    numero,
                    direccion,
                    responsableTecnicoOpt.get(),
                    new ArrayList<>()
            );
            sucursales.add(nuevaSucursal);
        }else throw new Exception();

    }

    protected Optional<Sucursal> buscarSucursalPorNumero(int numero) {
        return sucursales.stream()
                .filter(p -> p.getNumero() == numero)
                .findFirst();
    }

    protected void agregarPeticionASucursal(int numeroSucursal, Peticion nuevaPeticion) {
        Optional<Sucursal> sucursal = buscarSucursalPorNumero(numeroSucursal);
        if (sucursal.isEmpty()) {
            throw new RuntimeException("Sucursal con numero " + numeroSucursal + " no encontrada.");
        }
        sucursal.get().agregarPeticion(nuevaPeticion);
    }

    public Optional<SucursalDTO> getSucursal(int numero){
        Optional<Sucursal> sucursal  = buscarSucursalPorNumero(numero);
        return sucursal.map(SucursalDTO::new);
    }

    public void eliminarSucursal(int numeroSucursal, int numeroSucursalDerivaciones) {
        Sucursal sucursal = buscarSucursalPorNumero(numeroSucursal)
                .orElseThrow(() -> new RuntimeException("Sucursal con número " + numeroSucursal + " no encontrada"));

        Sucursal sucursalADerivar = buscarSucursalPorNumero(numeroSucursalDerivaciones)
                .orElseThrow(() -> new RuntimeException("Sucursal para derivaciones con número " + numeroSucursalDerivaciones + " no encontrada"));

        if (!sucursal.sePuedeEliminar()) {
            throw new RuntimeException("La sucursal con número " + numeroSucursal + " no se puede eliminar");
        }

        sucursal.derivarPeticiones(sucursalADerivar);
        sucursales.remove(sucursal);
    }

    public void modificarSucursal(int numero, String direccion, String responsableTecnicoUsername) throws Exception {
        Optional<Sucursal> sucursalOpt = buscarSucursalPorNumero(numero);
        if (sucursalOpt.isPresent()) {
            Sucursal sucursal = sucursalOpt.get();
            if (direccion != null) {
                sucursal.setDireccion(direccion);
            }
            if (responsableTecnicoUsername != null) {
                Optional<Usuario> responsableTecnicoOpt = usuarioController.buscarUsuarioPorUsername(responsableTecnicoUsername);
                if(responsableTecnicoOpt.isPresent()){
                    sucursal.setResponsableTecnico(responsableTecnicoOpt.get());
                }else throw new Exception();
            }
        } else throw new Exception();
    }

}