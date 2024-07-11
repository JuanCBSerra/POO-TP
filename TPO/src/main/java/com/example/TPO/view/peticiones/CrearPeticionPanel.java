package com.example.TPO.view.peticiones;

import com.example.TPO.Utils;
import com.example.TPO.controller.PeticionController;
import com.example.TPO.controller.PracticaController;
import com.toedter.calendar.JDateChooser;


import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Date;

public class CrearPeticionPanel extends JPanel {

    private final JTextField idPeticionField;
    private final JTextField pacienteField;
    private final JTextField obraSocialField;
    private final JDateChooser fechaCargaChooser;
    private final JDateChooser fechaEntregaChooser;
    private final JFormattedTextField numeroSucursalField;
    private final JTextArea practicasAsociadasArea;

    public CrearPeticionPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Crear Peticion", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        addFormRow(formPanel, "ID petición: ", idPeticionField = new JTextField(20));
        addFormRow(formPanel, "DNI paciente: ", pacienteField = new JTextField(20));
        addFormRow(formPanel, "Numero sucursal: ", numeroSucursalField = Utils.createFormattedTextField());
        addFormRow(formPanel, "Obra social:", obraSocialField = new JTextField(20));
        addFormRow(formPanel, "Fecha de carga: ", fechaCargaChooser = new JDateChooser());
        addFormRow(formPanel, "Fecha de entrega: ", fechaEntregaChooser = new JDateChooser());
        addFormRow(formPanel, "IDs prácticas asociadas: (separadas por comas)", practicasAsociadasArea = new JTextArea(1,20));

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(144, 238, 144));
        btnGuardar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGuardar.addActionListener(e -> guardarPeticion());

        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(btnGuardar);

        add(formPanel, BorderLayout.CENTER);
    }

    private void addFormRow(JPanel panel, String labelText, Component component) {
        JPanel row = new JPanel(new BorderLayout(20, 20));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, component.getPreferredSize().height));
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(240, component.getPreferredSize().height));
        row.add(label, BorderLayout.WEST);
        row.add(component, BorderLayout.CENTER);
        panel.add(row);
        panel.add(Box.createVerticalStrut(20));
    }

    private void guardarPeticion() {
        String idPeticionString = idPeticionField.getText();
        String pacienteString = pacienteField.getText();
        String obraSocialString = obraSocialField.getText();
        Date fechaCargaDate = fechaCargaChooser.getDate();
        Date fechaEntregaDate = fechaEntregaChooser.getDate();
        String practicasAsociadasString = practicasAsociadasArea.getText();
        String numeroSucursalText = numeroSucursalField.getText();

        if (idPeticionString.isEmpty() || pacienteString.isEmpty() || obraSocialString.isEmpty() || fechaCargaDate == null || fechaEntregaDate == null || practicasAsociadasString.isEmpty() || numeroSucursalText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int numeroSucursal = Integer.parseInt(numeroSucursalText);
        String[] codigosPracticas = practicasAsociadasString.split(",");

        for (String codigo : codigosPracticas){
            int codigoPractica = Integer.parseInt(codigo);
            if(!PracticaController.getInstance().getPractica(codigoPractica).get().isHabilitada()){
                JOptionPane.showMessageDialog(this, "La práctica " + codigoPractica + " no está habilitada", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        try{
            PeticionController.getInstance().agregarPeticion(pacienteString, numeroSucursal, idPeticionString, obraSocialString, fechaCargaDate, fechaEntregaDate, codigosPracticas);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Peticion creada con exito.");
        clearFields();
    }

    private void clearFields() {
        idPeticionField.setText("");
        pacienteField.setText("");
        obraSocialField.setText("");
        numeroSucursalField.setValue(null);
        fechaCargaChooser.setDate(null);
        fechaEntregaChooser.setDate(null);
        practicasAsociadasArea.setText("");
    }
}
