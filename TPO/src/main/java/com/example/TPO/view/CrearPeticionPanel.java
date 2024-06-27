package com.example.TPO.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearPeticionPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTextField obraSocialField;
    private JTextField fechaCargaField;
    private JTextField practicaAsociadaField;
    private JTextField fechaEntregaField;

    public CrearPeticionPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Crear Peticion", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        addFormRow(formPanel, "Obra Social:", obraSocialField = new JTextField(20));
        addFormRow(formPanel, "Fecha de Carga:", fechaCargaField = new JTextField(20));
        addFormRow(formPanel, "Practica Asociada:", practicaAsociadaField = new JTextField(20));
        addFormRow(formPanel, "Fecha Estimada de Entrega:", fechaEntregaField = new JTextField(20));

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(144, 238, 144)); // Verde claro
        btnGuardar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarPeticion();
            }
        });

        formPanel.add(Box.createVerticalStrut(20)); // Añade un espacio vertical antes del botón
        formPanel.add(btnGuardar);

        add(formPanel, BorderLayout.CENTER);
    }

    private void addFormRow(JPanel panel, String labelText, JTextField textField) {
        JPanel row = new JPanel(new BorderLayout(20, 20));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, textField.getPreferredSize().height));
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(200, textField.getPreferredSize().height));
        row.add(label, BorderLayout.WEST);
        row.add(textField, BorderLayout.CENTER);
        panel.add(row);
        panel.add(Box.createVerticalStrut(20)); // Añade un espacio vertical entre las filas
    }

    private void guardarPeticion() {
        // Aquí puedes llamar a los métodos correspondientes para crear la petición
        // Por ejemplo: Peticion nuevaPeticion = new Peticion(...);
        // peticionService.crearPeticion(nuevaPeticion);

        JOptionPane.showMessageDialog(this, "Peticion creada con exito.");
    }
}
