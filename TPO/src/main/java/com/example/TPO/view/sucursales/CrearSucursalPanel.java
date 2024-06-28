package com.example.TPO.view.sucursales;

import com.example.TPO.Utils;
import com.example.TPO.controller.SucursalController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        addFormRow(formPanel, "Numero:", numeroField = Utils.createFormattedTextField());
        addFormRow(formPanel, "Direccion:", direccionField = new JTextField(20));
        addFormRow(formPanel, "Responsable Tecnico:", respTecnicoField = new JTextField(20));

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGuardar.setBackground(new Color(144, 238, 144));
        formPanel.add(btnGuardar);

        add(formPanel, BorderLayout.CENTER);

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarSucursal();
            }
        });
    }

    private void addFormRow(JPanel panel, String labelText, JComponent textField) {
        JPanel row = new JPanel(new BorderLayout(20, 20));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, textField.getPreferredSize().height));
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(160, textField.getPreferredSize().height));
        row.add(label, BorderLayout.WEST);
        row.add(textField, BorderLayout.CENTER);
        panel.add(row);
        panel.add(Box.createVerticalStrut(20)); // Añade un espacio vertical entre las filas
    }

    private void guardarSucursal() {
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
