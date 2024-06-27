package com.example.TPO.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificarUsuarioPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private JTextField nombreField;
    private JTextField apellidoField;
    private JTextField dniField;
    private JTextField fecNacField;
    private JTextField usuarioField;
    private JTextField correoField;
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

        formPanel.add(new JLabel("Usuario:"));
        usuarioField = new JTextField();
        formPanel.add(usuarioField);

        formPanel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        formPanel.add(nombreField);

        formPanel.add(new JLabel("Apellido:"));
        apellidoField = new JTextField();
        formPanel.add(apellidoField);

        formPanel.add(new JLabel("Correo:"));
        correoField = new JTextField();
        formPanel.add(correoField);

        formPanel.add(new JLabel("DNI:"));
        dniField = new JTextField();
        formPanel.add(dniField);

        formPanel.add(new JLabel("Fecha Nacimiento:"));
        fecNacField = new JTextField();
        formPanel.add(fecNacField);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(144, 238, 144)); // Color verde claro
        formPanel.add(btnGuardar);

        add(formPanel, BorderLayout.SOUTH);

        habilitarFormulario(false);

        btnBuscar.addActionListener(e -> buscarPaciente());
        btnGuardar.addActionListener(e -> guardarPaciente());
    }

    private void habilitarFormulario(boolean habilitar) {
        usuarioField.setEnabled(habilitar);
        nombreField.setEnabled(habilitar);
        apellidoField.setEnabled(habilitar);
        correoField.setEnabled(habilitar);
        dniField.setEnabled(habilitar);
        fecNacField.setEnabled(habilitar);
        btnGuardar.setEnabled(habilitar);
    }

    private void buscarPaciente() {
        String dni = dniBuscarField.getText();

        // Aquí deberías implementar la lógica para buscar el usuario por su DNI
        // y cargar los datos en los campos correspondientes.

        // Simulación para el ejemplo:
        if (dni.equals("12345678")) {
            usuarioField.setText("usuario123");
            nombreField.setText("Juan");
            apellidoField.setText("Pérez");
            correoField.setText("juan.perez@example.com");
            dniField.setText("12345678");
            fecNacField.setText("1990-01-01");
            habilitarFormulario(true);
        } else {
            JOptionPane.showMessageDialog(this, "Usuario no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            habilitarFormulario(false);
        }
    }

    private void guardarPaciente() {
        String usuario = usuarioField.getText();
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        String correo = correoField.getText();
        String dni = dniField.getText();
        String fechaNacimiento = fecNacField.getText();

        // Validaciones básicas
        if (usuario.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || dni.isEmpty() || fechaNacimiento.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos correctamente.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Aquí puedes implementar la lógica para guardar o modificar el usuario
        // Por ejemplo: userService.guardarUsuario(usuario, nombre, apellido, correo, dni, fechaNacimiento);

        JOptionPane.showMessageDialog(this, "Usuario modificado con éxito.");
    }
}
