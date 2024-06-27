package com.example.TPO.controller;

import java.io.*;
import com.example.TPO.model.Usuario;
import com.example.TPO.model.Rol;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioController {

    private static UsuarioController instance;
    private final List<Usuario> usuarios = new ArrayList<>();

    private UsuarioController() {
    }

    public static UsuarioController getInstance() {
        if (instance == null) {
            instance = new UsuarioController();
        }
        return instance;
    }

    public void agregarUsuario(String username, String nombre, String correo, String password, String domicilio, String dni, LocalDate fechaNac, Rol rol) {
        Usuario nuevoUsuario = new Usuario(
                username,
                nombre,
                correo,
                password,
                domicilio,
                dni,
                fechaNac,
                rol
        );
        usuarios.add(nuevoUsuario);
        guardarUsuarioEnBD(nuevoUsuario);
    }

    public Optional<Usuario> buscarUsuarioPorDni(String dni) {
        return usuarios.stream()
                .filter(p -> p.getDni().equals(dni))
                .findFirst();
    }

    public boolean eliminarUsuario(String dni) {
        Optional<Usuario> usuario = buscarUsuarioPorDni(dni);
        if (usuario.isPresent()) {
            usuarios.remove(usuario.get());
            eliminarUsuarioDeBD(dni);
            return true;
        } else {
            return false;
        }
    }

    public boolean modificarUsuario(String username, String nombre,String email,String password,String domicilio,String dni,LocalDate fecNac,String rol) {
        Optional<Usuario> usuarioOpt = buscarUsuarioPorDni(dni);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (username != null) {
                usuario.setUsername(nombre);
            }
            if (username != null){
                usuario.setNombre(nombre);
            }
            if (email != null) {
                usuario.setEmail(email);
            }
            if (password != null) {
                usuario.setPassword(password);
            }
            if (domicilio != null) {
                usuario.setDomicilio(domicilio);
            }
            if (dni != null) {
                usuario.setDni(dni);
            }
            if (fecNac != null) {
                usuario.setFechaNacimiento(fecNac);
            }
            if (rol != null) {
                usuario.setRol(Rol.valueOf(rol));
            }

            eliminarUsuarioDeBD(dni);
            guardarUsuarioEnBD(usuario);
            return true;
        }
        return false;
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarios;
    }

    private void guardarUsuarioEnBD(Usuario usuario) {
        String rutaArchivo = "TPO/src/db/dbUsuarios.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            String linea = String.format("%s,%s,%s,%s,%s",
                    usuario.getNombre(),
                    usuario.getEmail(),
                    usuario.getPassword(),
                    usuario.getRol().toString().toLowerCase(),
                    usuario.getDni());
            writer.write(linea);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void eliminarUsuarioDeBD(String dni) {
        String rutaArchivo = "TPO/src/db/dbUsuarios.txt";
        File archivo = new File(rutaArchivo);
        File archivoTemp = new File("TPO/src/db/dbUsuariosTemp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo));
             BufferedWriter writer = new BufferedWriter(new FileWriter(archivoTemp))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                if (!linea.contains(dni)) {
                    writer.write(linea);
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Eliminar el archivo original
        if (archivo.delete()) {
            // Renombrar el archivo temporal
            archivoTemp.renameTo(archivo);
        }
    }
}
