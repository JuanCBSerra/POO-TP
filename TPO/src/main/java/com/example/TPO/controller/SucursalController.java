package com.example.TPO.controller;

import com.example.TPO.model.Sucursal;
import com.example.TPO.model.Usuario;
import lombok.NoArgsConstructor;

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