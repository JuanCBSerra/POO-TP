package com.example.TPO.view.usuarios;

import com.example.TPO.Utils;
import com.example.TPO.controller.UsuarioController;
import com.example.TPO.model.Rol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;

public class CrearUsuarioPanel extends JPanel {
    @Serial
    private static final long serialVersionUID = 1L;
    private final JTextField usuarioField;
    private final JTextField correoField;
    private final JTextField passwordField;
    private final JTextField nombreField;
    private final JTextField domicilioField;
    private final JTextField dniField;
    private final JTextField fecNacField;
    private final JComboBox<Rol> rolComboBox;

    private final UsuarioController usuarioController = UsuarioController.getInstance();

    public CrearUsuarioPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Crear Usuario", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        addFormRow(formPanel, "Nombre de usuario:", usuarioField = new JTextField(20));
        addFormRow(formPanel, "Nombre completo:", nombreField = new JTextField(20));
        addFormRow(formPanel, "Email:", correoField = new JTextField(20));
        addFormRow(formPanel, "Contraseña:", passwordField = new JPasswordField(20));
        addFormRow(formPanel, "Domicilio:", domicilioField = new JTextField(20));
        addFormRow(formPanel, "DNI:", dniField = new JTextField(20));
        addFormRow(formPanel, "Fecha Nacimiento (YYYY-MM-DD):", fecNacField = new JTextField(20));

        // Crear el JComboBox para el rol
        rolComboBox = new JComboBox<>(Rol.values());
        addFormRow(formPanel, "Rol:", rolComboBox);

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

    private void addFormRow(JPanel panel, String labelText, JComponent component) {
        JPanel row = new JPanel(new BorderLayout(20, 20));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, component.getPreferredSize().height));
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(190, component.getPreferredSize().height));
        row.add(label, BorderLayout.WEST);
        row.add(component, BorderLayout.CENTER);
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
        String fecNac =fecNacField.getText();

        if (rolComboBox == null) {
            JOptionPane.showMessageDialog(this, "Error: el campo de rol no está inicializado correctamente.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Rol rol = (Rol) rolComboBox.getSelectedItem();

        if (nombre.isEmpty() || username.isEmpty() || dni.isEmpty() || password.isEmpty() || domicilio.isEmpty() || correo.isEmpty() || fecNac.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        usuarioController.agregarUsuario(username, nombre, correo, password, domicilio, dni, Utils.parseDate(fecNac), rol);

        JOptionPane.showMessageDialog(this, "Usuario creado con éxito.");
    }
}
