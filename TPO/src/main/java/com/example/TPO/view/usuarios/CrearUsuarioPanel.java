package com.example.TPO.view.usuarios;

import com.example.TPO.Utils;
import com.example.TPO.controller.UsuarioController;
import com.example.TPO.model.Rol;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import java.util.Date;

public class CrearUsuarioPanel extends JPanel {
    @Serial
    private static final long serialVersionUID = 1L;
    private final JTextField usuarioField;
    private final JTextField correoField;
    private final JTextField passwordField;
    private final JTextField nombreField;
    private final JTextField domicilioField;
    private final JTextField dniField;
    private final JDateChooser fechaNacimientoChooser;
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
        addFormRow(formPanel, "Fecha de nacimiento: ", fechaNacimientoChooser = new JDateChooser());

        rolComboBox = new JComboBox<>(Rol.values());
        addFormRow(formPanel, "Rol:", rolComboBox);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGuardar.setBackground(new Color(144, 238, 144));
        formPanel.add(btnGuardar);

        add(formPanel, BorderLayout.CENTER);

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
        panel.add(Box.createVerticalStrut(20));
    }

    private void guardarUsuario() {
        String username = usuarioField.getText();
        String nombre = nombreField.getText();
        String correo = correoField.getText();
        String password = passwordField.getText();
        String domicilio = domicilioField.getText();
        String dni = dniField.getText();
        Date fecNac =fechaNacimientoChooser.getDate();

        if (rolComboBox == null) {
            JOptionPane.showMessageDialog(this, "Error: el campo de rol no está inicializado correctamente.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Rol rol = (Rol) rolComboBox.getSelectedItem();

        if (nombre.isEmpty() || username.isEmpty() || dni.isEmpty() || password.isEmpty() || domicilio.isEmpty() || correo.isEmpty() || fecNac == null) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        usuarioController.agregarUsuario(username, nombre, correo, password, domicilio, dni, fecNac, rol);

        JOptionPane.showMessageDialog(this, "Usuario creado con éxito.");

        clearFields();

    }

    private void clearFields() {
        usuarioField.setText("");
        nombreField.setText("");
        correoField.setText("");
        passwordField.setText("");
        domicilioField.setText("");
        dniField.setText("");
        fechaNacimientoChooser.setDate(null);
        rolComboBox.setSelectedIndex(0);
    }
}
