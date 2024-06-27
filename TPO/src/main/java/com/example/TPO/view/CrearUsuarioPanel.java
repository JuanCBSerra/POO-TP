package com.example.TPO.view;

import com.example.TPO.controller.UsuarioController;
import com.example.TPO.model.Rol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class CrearUsuarioPanel extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JTextField usuarioField;
    private JTextField correoField;
    private JTextField passwordField;
    private JTextField nombreField;
    private JTextField domicilioField;
    private JTextField dniField;
    private JTextField fecNacField;
    private JTextField rolField;

    public CrearUsuarioPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Crear Usuario", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        addFormRow(formPanel, "Usuario:", usuarioField = new JTextField(20));
        addFormRow(formPanel, "Nombre:", nombreField = new JTextField(20));
        addFormRow(formPanel, "Correo:", correoField = new JTextField(20));
        addFormRow(formPanel, "Contraseña:", passwordField = new JTextField(20));
        addFormRow(formPanel, "Domicilio:", domicilioField = new JTextField(20));
        addFormRow(formPanel, "DNI:", dniField = new JTextField(20));
        addFormRow(formPanel, "Fecha Nacimiento (YYYY-MM-DD):", fecNacField = new JTextField(20));
        addFormRow(formPanel, "Rol:", rolField = new JTextField(20));

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGuardar.setBackground(new Color(144, 238, 144));
        formPanel.add(btnGuardar);

        add(formPanel, BorderLayout.CENTER);

        // Agregar ActionListener al botón Guardar
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarUsuario();
            }
        });
    }

    private void addFormRow(JPanel panel, String labelText, JTextField textField) {
        JPanel row = new JPanel(new BorderLayout(20, 20));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, textField.getPreferredSize().height));
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(190, textField.getPreferredSize().height));
        row.add(label, BorderLayout.WEST);
        row.add(textField, BorderLayout.CENTER);
        panel.add(row);
        panel.add(Box.createVerticalStrut(20)); // Añade un espacio vertical entre las filas
    }

    private void guardarUsuario() {
        String username = usuarioField.getText();
        String nombre = nombreField.getText();
        String correo = correoField.getText();
        String password = passwordField.getText();
        String domicilio = domicilioField.getText();
        String dni = dniField.getText();
        LocalDate fecNac = LocalDate.parse(fecNacField.getText());
        Rol rol = Rol.valueOf(rolField.getText().toLowerCase());

        // Validaciones básicas
        if (nombre.isEmpty() || username.isEmpty() || dni.isEmpty() || password.isEmpty() || domicilio.isEmpty() || dni.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        UsuarioController.getInstance().agregarUsuario(username,nombre,correo,password,domicilio,dni,fecNac,rol);

        JOptionPane.showMessageDialog(this, "Usuario creado con éxito.");
    }
}
