package com.example.TPO.view.resultados;

import com.example.TPO.Utils;
import com.example.TPO.controller.PeticionController;

import java.time.LocalDate;
import javax.swing.*;
import java.awt.*;

public class CrearResultadoPanel extends JPanel {

    private final JTextField idField;
    private final JTextField valorField;
    private final JTextField numeroPeticionField;
    private final JFormattedTextField idPracticaField;

    private final PeticionController peticionController = PeticionController.getInstance();

    public CrearResultadoPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Crear Resultado", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        addFormRow(formPanel, "ID:", idField = new JTextField(20));
        addFormRow(formPanel, "Valor:", valorField = new JTextField(20));
        addFormRow(formPanel, "Numero Peticion:", numeroPeticionField = new JTextField(20));
        addFormRow(formPanel, "Id Practica asociada:", idPracticaField = Utils.createFormattedTextField());

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(144, 238, 144)); // Verde claro
        btnGuardar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGuardar.addActionListener(e -> guardarResultado());

        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(btnGuardar);

        add(formPanel, BorderLayout.CENTER);
    }

    private void addFormRow(JPanel panel, String labelText, JTextField textField) {
        JPanel row = new JPanel(new BorderLayout(20, 20));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, textField.getPreferredSize().height));
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(120, textField.getPreferredSize().height));
        row.add(label, BorderLayout.WEST);
        row.add(textField, BorderLayout.CENTER);
        panel.add(row);
        panel.add(Box.createVerticalStrut(20));
    }

    private void guardarResultado() {
        String idString = idField.getText();
        String valorString = valorField.getText();
        String idPeticionString = numeroPeticionField.getText();
        String idPracticaString = idPracticaField.getText();

        if (idString.isEmpty() || valorString.isEmpty() || idPeticionString.isEmpty() || idPracticaString.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int idPractica = Integer.parseInt(idPracticaString);
        LocalDate fechaActual = LocalDate.now();
        try{
            peticionController.agregarResultado(idPeticionString,idString,valorString,fechaActual, idPractica);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Resultado creado con éxito.");
        clearFields();
    }

    private void clearFields() {
        idField.setText("");
        valorField.setText("");
        numeroPeticionField.setText("");
        idPracticaField.setValue(null);
    }
}
