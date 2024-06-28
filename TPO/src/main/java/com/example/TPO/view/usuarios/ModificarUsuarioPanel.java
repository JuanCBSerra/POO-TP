package com.example.TPO.view.usuarios;

import com.example.TPO.Utils;
import com.example.TPO.controller.UsuarioController;
import com.example.TPO.model.Rol;
import com.example.TPO.model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.Optional;

public class ModificarUsuarioPanel extends JPanel {

    private final JTextField dniBuscarField;
    private final JTextField usernameField;
    private final JTextField nombreField;
    private final JTextField emailField;
    private final JTextField domicilioField;
    private final JTextField passwordField;
    private final JTextField fecNacField;
    private final JComboBox<Rol> rolComboBox;
    private final JButton btnGuardar;

    private final UsuarioController usuarioController = UsuarioController.getInstance();

    public ModificarUsuarioPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Modificar Usuario", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel buscarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buscarPanel.add(new JLabel("DNI:"));
        dniBuscarField = new JTextField(20);
        buscarPanel.add(dniBuscarField);
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(144, 202, 249)); // Color celeste
        buscarPanel.add(btnBuscar);

        add(buscarPanel, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridLayout(10, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20)); // Espacios alrededor del panel

        formPanel.add(new JLabel("Nombre de usuario:"));
        usernameField = new JTextField();
        formPanel.add(usernameField);

        formPanel.add(new JLabel("Nombre completo:"));
        nombreField = new JTextField();
        formPanel.add(nombreField);

        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        formPanel.add(emailField);

        formPanel.add(new JLabel("Contraseña:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);

        formPanel.add(new JLabel("Domicilio:"));
        domicilioField = new JTextField();
        formPanel.add(domicilioField);

        formPanel.add(new JLabel("Fecha de Nacimiento:"));
        fecNacField = Utils.createDateFormattedField();
        formPanel.add(fecNacField);

        formPanel.add(new JLabel("Rol:"));
        rolComboBox = new JComboBox<>(Rol.values());
        formPanel.add(rolComboBox);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(144, 238, 144));
        formPanel.add(btnGuardar);

        add(formPanel, BorderLayout.SOUTH);

        habilitarFormulario(false);

        btnBuscar.addActionListener(e -> buscarUsuario());
        btnGuardar.addActionListener(e -> guardarUsuario());
    }

    private void habilitarFormulario(boolean habilitar) {
        Component[] components = {
                usernameField, nombreField, emailField, passwordField,
                domicilioField, fecNacField, rolComboBox, btnGuardar
        };
        for (Component component : components) {
            component.setEnabled(habilitar);
        }
    }

    private void buscarUsuario() {
        String dni = dniBuscarField.getText();
        Optional<Usuario> usuario = usuarioController.buscarUsuarioPorDni(dni);

        if (usuario.isPresent()) {
            Usuario u = usuario.get();
            usernameField.setText(u.getUsername());
            nombreField.setText(u.getNombre());
            emailField.setText(u.getEmail());
            passwordField.setText(u.getPassword());
            domicilioField.setText(u.getDomicilio());
            fecNacField.setText(Utils.formatDate(u.getFechaNacimiento()));
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
        Date fecNac = Utils.parseDate(fecNacField.getText());
        Rol rol = (Rol) rolComboBox.getSelectedItem();

        if (username.isEmpty() || nombre.isEmpty() || email.isEmpty() || password.isEmpty() || domicilio.isEmpty() || fecNac == null) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos correctamente.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        usuarioController.modificarUsuario(username, nombre, email, password, domicilio, null, fecNac, String.valueOf(rol));

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
        dniBuscarField.setText("");
        fecNacField.setText("");
        rolComboBox.setSelectedIndex(0); // Assuming default selection is set to index 0
    }
}
