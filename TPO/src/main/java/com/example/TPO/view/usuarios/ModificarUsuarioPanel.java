package com.example.TPO.view.usuarios;

import com.example.TPO.DTO.UsuarioDTO;
import com.example.TPO.Utils;
import com.example.TPO.controller.UsuarioController;
import com.example.TPO.model.Rol;
import com.toedter.calendar.JDateChooser;

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
    private final JDateChooser fechaNacimientoChooser;
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
        btnBuscar.setBackground(new Color(144, 202, 249));
        buscarPanel.add(btnBuscar);

        add(buscarPanel, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridLayout(9, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        formPanel.add(new JLabel("Nombre de usuario:"));
        usernameField = new JTextField();
        formPanel.add(usernameField);

        formPanel.add(new JLabel("Nombre completo:"));
        nombreField = new JTextField();
        formPanel.add(nombreField);

        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        formPanel.add(emailField);

        formPanel.add(new JLabel("Domicilio:"));
        domicilioField = new JTextField();
        formPanel.add(domicilioField);

        formPanel.add(new JLabel("Fecha de Nacimiento:"));
        fechaNacimientoChooser = new JDateChooser();
        formPanel.add(fechaNacimientoChooser);

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
                usernameField, nombreField, emailField,
                domicilioField, fechaNacimientoChooser, rolComboBox, btnGuardar
        };
        for (Component component : components) {
            component.setEnabled(habilitar);
        }
    }

    private void buscarUsuario() {
        String dni = dniBuscarField.getText();
        Optional<UsuarioDTO> usuario = usuarioController.getUsuario(dni);

        if (usuario.isPresent()) {
            UsuarioDTO u = usuario.get();
            usernameField.setText(u.getUsername());
            nombreField.setText(u.getNombre());
            emailField.setText(u.getEmail());
            domicilioField.setText(u.getDomicilio());
            fechaNacimientoChooser.setDate(u.getFechaNacimiento());
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
        String domicilio = domicilioField.getText();
        Date fecNac = fechaNacimientoChooser.getDate();
        Rol rol = (Rol) rolComboBox.getSelectedItem();

        if (username.isEmpty() || nombre.isEmpty() || email.isEmpty() || domicilio.isEmpty() || fecNac == null) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos correctamente.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        usuarioController.modificarUsuario(username, nombre, email, domicilio, null, fecNac, String.valueOf(rol));

        JOptionPane.showMessageDialog(this, "Usuario modificado con éxito.");
        limpiarCampos();
        habilitarFormulario(false);
    }

    private void limpiarCampos() {
        usernameField.setText("");
        nombreField.setText("");
        emailField.setText("");
        domicilioField.setText("");
        dniBuscarField.setText("");
        fechaNacimientoChooser.setDate(null);
        rolComboBox.setSelectedIndex(0);
    }
}
