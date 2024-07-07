package com.example.TPO.controller;

import java.io.*;

import com.example.TPO.DTO.UsuarioDTO;
import com.example.TPO.Utils;
import com.example.TPO.model.Usuario;
import com.example.TPO.model.Rol;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class UsuarioController {

    private static UsuarioController instance;
    private final List<Usuario> usuarios = new ArrayList<>();

    private static final String DB_FILE = new File("").getAbsolutePath() + "/src/db/dbUsuarios.txt";

    private UsuarioController() {
        cargarUsuariosDesdeBD();
    }

    public static UsuarioController getInstance() {
        if (instance == null) {
            instance = new UsuarioController();
        }
        return instance;
    }

    public void agregarUsuario(String username, String nombre, String correo, String password, String domicilio, String dni, Date fechaNac, Rol rol) {
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

    protected Optional<Usuario> buscarUsuarioPorDni(String dni) {
        return usuarios.stream()
                .filter(p -> p.getDni().equals(dni))
                .findFirst();
    }

    public Optional<UsuarioDTO> getUsuario(String dni) {
        Optional<Usuario> usuario = buscarUsuarioPorDni(dni);
        return usuario.map(UsuarioDTO::new);
    }

    public Optional<Usuario> buscarUsuarioPorUsername(String username) {
        return usuarios.stream()
                .filter(p -> p.getUsername().equals(username))
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

    public void modificarUsuario(String username, String nombre, String email, String domicilio, String dni, Date fecNac, String rol) {
        Optional<Usuario> usuarioOpt = buscarUsuarioPorDni(dni);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (username != null) {
                usuario.setUsername(username);
            }
            if (nombre != null){
                usuario.setNombre(nombre);
            }
            if (email != null) {
                usuario.setEmail(email);
            }
            if (domicilio != null) {
                usuario.setDomicilio(domicilio);
            }
            if (fecNac != null) {
                usuario.setFechaNacimiento(fecNac);
            }
            if (rol != null) {
                usuario.setRol(Rol.valueOf(rol));
            }

            eliminarUsuarioDeBD(dni);
            guardarUsuarioEnBD(usuario);
        }
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarios;
    }

    private void guardarUsuarioEnBD(Usuario usuario) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DB_FILE, true))) {
            String linea = String.format("%s,%s,%s,%s,%s,%s,%s,%s",
                    usuario.getUsername(),
                    usuario.getNombre(),
                    usuario.getEmail(),
                    usuario.getPassword(),
                    usuario.getDomicilio(),
                    usuario.getDni(),
                    Utils.formatDate(usuario.getFechaNacimiento()),
                    usuario.getRol());
            writer.write(linea);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al guardar usuario en la base de datos: " + e.getMessage());
        }
    }

    private void cargarUsuariosDesdeBD() {
        File archivo = new File(DB_FILE);
        if (!archivo.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 8) {
                    Usuario usuario = new Usuario(
                            datos[0],
                            datos[1],
                            datos[2],
                            datos[3],
                            datos[4],
                            datos[5],
                            Utils.parseDate(datos[6]),
                            Rol.valueOf(datos[7].toUpperCase())
                    );
                    usuarios.add(usuario);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar usuarios desde la base de datos: " + e.getMessage());
        }
    }

    public static void eliminarUsuarioDeBD(String dni) {
        File archivo = new File(DB_FILE);
        File archivoTemp = new File(archivo.getParent(), "tempDb");

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
            System.err.println("Error al eliminar usuario de la base de datos: " + e.getMessage());
        }

        // Eliminar el archivo original
        if (archivo.delete()) {
            // Renombrar el archivo temporal
            if (!archivoTemp.renameTo(archivo)) {
                System.err.println("Error al renombrar el archivo temporal.");
            }
        } else {
            System.err.println("Error al eliminar el archivo original.");
        }
    }

    public Optional<Usuario> autenticarUsuario(String username, String password) {
        return usuarios.stream()
                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                .findFirst();
    }

}
