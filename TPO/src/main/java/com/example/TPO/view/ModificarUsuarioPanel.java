package com.example.TPO.view;

import com.example.TPO.controller.UsuarioController;
import com.example.TPO.model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Optional;

public class ModificarUsuarioPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private JTextField usernameField;
    private JTextField nombreField;
    private JTextField emailField;
    private JTextField passwordField;
    private JTextField domicilioField;
    private JTextField dniField;
    private JTextField fecNacField;
    private JTextField rolField;
    private JTextField dniBuscarField;
    private JButton btnBuscar;
    private JButton btnGuardar;

    public ModificarUsuarioPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Modificar Usuario", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel buscarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buscarPanel.add(new JLabel("DNI:"));
        dniBuscarField = new JTextField(20);
        buscarPanel.add(dniBuscarField);
        btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(144, 202, 249)); // Color celeste
        buscarPanel.add(btnBuscar);

        add(buscarPanel, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridLayout(8, 2, 3, 3));
        formPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20)); // Espacios alrededor del panel

        formPanel.add(new JLabel("Nombre de usuario:"));
        usernameField = new JTextField();
        formPanel.add(usernameField);

        formPanel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        formPanel.add(nombreField);

        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        formPanel.add(emailField);

        formPanel.add(new JLabel("Contraseña:"));
        passwordField = new JTextField();
        formPanel.add(passwordField);

        formPanel.add(new JLabel("Domicilio:"));
        domicilioField = new JTextField();
        formPanel.add(domicilioField);

        formPanel.add(new JLabel("DNI:"));
        dniField = new JTextField();
        formPanel.add(dniField);

        formPanel.add(new JLabel("Fecha Nacimiento:"));
        fecNacField = new JTextField();
        formPanel.add(fecNacField);

        formPanel.add(new JLabel("Rol:"));
        rolField = new JTextField();
        formPanel.add(rolField);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(144, 238, 144)); // Color verde claro
        formPanel.add(btnGuardar);

        add(formPanel, BorderLayout.SOUTH);

        habilitarFormulario(false);

        btnBuscar.addActionListener(e -> buscarUsuario());
        btnGuardar.addActionListener(e -> guardarUsuario());
    }

    private void habilitarFormulario(boolean habilitar) {
        usernameField.setEnabled(habilitar);
        nombreField.setEnabled(habilitar);
        emailField.setEnabled(habilitar);
        passwordField.setEnabled(habilitar);
        domicilioField.setEnabled(habilitar);
        dniField.setEnabled(habilitar);
        fecNacField.setEnabled(habilitar);
        rolField.setEnabled(habilitar);
        btnGuardar.setEnabled(habilitar);
    }

    private void buscarUsuario() {
        String dni = dniBuscarField.getText();
        Optional<Usuario> usuario = UsuarioController.getInstance().buscarUsuarioPorDni(dni);

        if(usuario.isPresent()){
            Usuario u = usuario.get();
            usernameField.setText(u.getUsername());
            nombreField.setText(u.getNombre());
            emailField.setText(u.getEmail());
            passwordField.setText(u.getPassword());
            domicilioField.setText(u.getDomicilio());
            dniField.setText(u.getDni());
            fecNacField.setText(String.valueOf(u.getFechaNacimiento()));
            rolField.setText(String.valueOf(u.getRol()));

            habilitarFormulario(true);
        }
        else {
            JOptionPane.showMessageDialog(this, "Usuario no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            limpiarCampos();
            habilitarFormulario(false);
        }

    }

    private void guardarUsuario() {
        String username = usernameField.getText();
        String nombre = nombreField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String domicilio = domicilioField.getText();
        String dni = dniField.getText();
        LocalDate fecNac = LocalDate.parse(fecNacField.getText());
        String rol = rolField.getText();

        if (nombre.isEmpty() || domicilio.isEmpty() || email.isEmpty() || username.isEmpty() || dni.isEmpty() || rol.isEmpty() || fecNac.toString().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos correctamente.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        UsuarioController.getInstance().modificarUsuario(username, nombre, email, password, domicilio, dni, fecNac, rol);

        JOptionPane.showMessageDialog(this, "Usuario modificado con éxito.");
        limpiarCampos();
        habilitarFormulario(false);
    }

    private void limpiarCampos() {
        usernameField.setText("");
        nombreField.setText("");
        emailField.setText("");
        passwordField.setText("");
        domicilioField.setText("");
        dniField.setText("");
        fecNacField.setText("");
        rolField.setText("");
    }
}
