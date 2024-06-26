package com.example.TPO.view;

import com.example.TPO.Utils;
import com.example.TPO.controller.SucursalController;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class CrearSucursalPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private final JFormattedTextField numeroField;
	private final JTextField direccionField;
	private final JTextField respTecnicoField;

	private final SucursalController sucursalController = SucursalController.getInstance();

    public CrearSucursalPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Crear Sucursal", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        formPanel.add(new JLabel("Numero:"));
        numeroField = Utils.createFormattedTextField();
        formPanel.add(numeroField);

        formPanel.add(new JLabel("Direccion:"));
        direccionField = new JTextField();
        formPanel.add(direccionField);

        formPanel.add(new JLabel("Responsable Tecnico:"));
        respTecnicoField = new JTextField();
        formPanel.add(respTecnicoField);

        JButton btnGuardar = new JButton("Guardar");
        formPanel.add(btnGuardar);

        add(formPanel, BorderLayout.CENTER);

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarPaciente();
            }
        });
    }

    private void guardarPaciente() {
        String numeroString = numeroField.getText();
        String direccion = direccionField.getText();
        String responsableTecnico = respTecnicoField.getText();

        if (numeroString.isEmpty() || direccion.isEmpty() || responsableTecnico.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int numero = Integer.parseInt(numeroString);

        sucursalController.agregarSucursal(
                numero,
                direccion,
                responsableTecnico
        );

        JOptionPane.showMessageDialog(this, "Sucursal creada con éxito.");
        clearFields();
    }

    private void clearFields() {
        numeroField.setValue(null);
        direccionField.setText("");
        respTecnicoField.setText("");
    }

}