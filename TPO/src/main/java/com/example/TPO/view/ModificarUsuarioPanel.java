package com.example.TPO.view;

import com.example.TPO.controller.UsuarioController;
import com.example.TPO.model.Rol;
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
    private JComboBox<Rol> rolComboBox;
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

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Espacios alrededor del panel

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Row 1: Username
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Nombre de usuario:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        usernameField = new JTextField(20);
        formPanel.add(usernameField, gbc);

        // Row 2: Nombre completo
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Nombre completo:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        nombreField = new JTextField(20);
        formPanel.add(nombreField, gbc);

        // Row 3: Email
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        emailField = new JTextField(20);
        formPanel.add(emailField, gbc);

        // Row 4: Contraseña
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Contraseña:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        passwordField = new JPasswordField(20); // JPasswordField para contraseñas
        formPanel.add(passwordField, gbc);

        // Row 5: Domicilio
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(new JLabel("Domicilio:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        domicilioField = new JTextField(20);
        formPanel.add(domicilioField, gbc);

        // Row 6: DNI
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(new JLabel("DNI:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        dniField = new JTextField(20);
        formPanel.add(dniField, gbc);

        // Row 7: Fecha de Nacimiento
        gbc.gridx = 0;
        gbc.gridy = 6;
        formPanel.add(new JLabel("Fecha de Nacimiento:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        fecNacField = new JTextField(20);
        formPanel.add(fecNacField, gbc);

        // Row 8: Rol
        gbc.gridx = 0;
        gbc.gridy = 7;
        formPanel.add(new JLabel("Rol:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 7;
        rolComboBox = new JComboBox<>(Rol.values());
        formPanel.add(rolComboBox, gbc);

        // Botón Guardar
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(144, 238, 144)); // Color verde claro
        formPanel.add(btnGuardar, gbc);

        add(formPanel, BorderLayout.SOUTH);

        habilitarFormulario(false);

        btnBuscar.addActionListener(e -> buscarUsuario());
        btnGuardar.addActionListener(e -> guardarUsuario());
    }

    private void habilitarFormulario(boolean habilitar) {
        Component[] components = {
                usernameField, nombreField, emailField, passwordField,
                domicilioField, dniField, fecNacField, rolComboBox, btnGuardar
        };
        for (Component component : components) {
            component.setEnabled(habilitar);
        }
    }

    private void buscarUsuario() {
        String dni = dniBuscarField.getText();
        Optional<Usuario> usuario = UsuarioController.getInstance().buscarUsuarioPorDni(dni);

        if (usuario.isPresent()) {
            Usuario u = usuario.get();
            usernameField.setText(u.getUsername());
            nombreField.setText(u.getNombre());
            emailField.setText(u.getEmail());
            passwordField.setText(u.getPassword());
            domicilioField.setText(u.getDomicilio());
            dniField.setText(u.getDni());
            fecNacField.setText(String.valueOf(u.getFechaNacimiento()));
            rolComboBox.setSelectedItem(u.getRol());

            habilitarFormulario(true);
        } else {
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
        Rol rol = (Rol) rolComboBox.getSelectedItem();

        if (nombre.isEmpty() || domicilio.isEmpty() || email.isEmpty() || username.isEmpty() || dni.isEmpty() || fecNac.toString().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos correctamente.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        UsuarioController.getInstance().modificarUsuario(username, nombre, email, password, domicilio, dni, fecNac, String.valueOf(rol));

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
    }
}
