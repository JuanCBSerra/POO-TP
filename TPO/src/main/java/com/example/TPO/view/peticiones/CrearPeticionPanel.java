package com.example.TPO.view.peticiones;

import com.example.TPO.Utils;
import com.example.TPO.controller.PacienteController;
import com.example.TPO.controller.PeticionController;
import com.example.TPO.controller.PracticaController;
import com.example.TPO.model.Paciente;
import com.example.TPO.model.Peticion;
import com.example.TPO.model.Practica;
import com.example.TPO.model.Resultado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class CrearPeticionPanel extends JPanel {

    private final JTextField idPeticionField;
    private final JTextField pacienteField;
    private final JTextField obraSocialField;
    private final JTextField fechaCargaField;
    private final JTextField fechaEntregaField;
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
        addFormRow(formPanel, "Obra social:", obraSocialField = new JTextField(20));
        addFormRow(formPanel, "Fecha de carga: (YYYY-MM-DD)", fechaCargaField = new JTextField(20));
        addFormRow(formPanel, "Fecha estimada de entrega: (YYYY-MM-DD)", fechaEntregaField = new JTextField(20));
        addFormRow(formPanel, "IDs prácticas asociadas: (separadas por comas)", practicasAsociadasArea = new JTextArea(5, 20));

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(144, 238, 144));
        btnGuardar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarPeticion();
            }
        });

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
        String fechaCargaString = fechaCargaField.getText();
        String fechaEntregaString = fechaEntregaField.getText();
        String practicasAsociadasString = practicasAsociadasArea.getText();

        if (idPeticionString.isEmpty() || pacienteString.isEmpty() || obraSocialString.isEmpty() || fechaCargaString.isEmpty() || fechaEntregaString.isEmpty() || practicasAsociadasString.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Date fechaCarga = Utils.parseDate(fechaCargaString);
        Date fechaEntrega = Utils.parseDate(fechaEntregaString);
        Optional<Paciente> pacienteOpt = PacienteController.getInstance().buscarPacientePorDni(pacienteString);

        if (pacienteOpt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Paciente inexistente.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Paciente paciente = pacienteOpt.get();
        List<Integer> practicas = new ArrayList<>();
        ArrayList<Resultado> resultados = new ArrayList<>();

        String[] practicasArray = practicasAsociadasString.split(",");
        for (String practicaCodigo : practicasArray) {
            practicaCodigo = practicaCodigo.trim();
            if (!practicaCodigo.isEmpty()) {
                Optional<Practica> practicaOpt = PracticaController.getInstance().buscarPracticaPorCodigo(Integer.parseInt(practicaCodigo));
                if (practicaOpt.isPresent()) {
                    practicas.add(Integer.parseInt(practicaCodigo));
                    resultados.add(new Resultado()); // Añadir un resultado vacío o predeterminado
                } else {
                    JOptionPane.showMessageDialog(this, "Práctica con código " + practicaCodigo + " inexistente.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        }

        Peticion peticion = new Peticion(idPeticionString, paciente, obraSocialString, fechaCarga, fechaEntrega, practicas, resultados);
        PeticionController.getInstance().agregarPeticion(peticion);

        JOptionPane.showMessageDialog(this, "Peticion creada con exito.");
        clearFields();
    }

    private void clearFields() {
        idPeticionField.setText("");
        pacienteField.setText("");
        obraSocialField.setText("");
        fechaCargaField.setText("");
        fechaEntregaField.setText("");
        practicasAsociadasArea.setText("");
    }
}
