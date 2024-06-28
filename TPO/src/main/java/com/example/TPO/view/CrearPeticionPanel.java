package com.example.TPO.view;

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

import static java.util.Date.*;

public class CrearPeticionPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTextField idPeticionField;
    private JTextField pacienteField;
    private JTextField obraSocialField;
    private JTextField fechaCargaField;
    private JTextField practicaAsociadaField;
    private JTextField fechaEntregaField;
    private JTextField resultadosField;


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
        addFormRow(formPanel, "ID practica asociada:", practicaAsociadaField = new JTextField(20));
//        addFormRow(formPanel, "Resultados:", resultadosField = new JTextField(20));


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
        label.setPreferredSize(new Dimension(240, textField.getPreferredSize().height));
        row.add(label, BorderLayout.WEST);
        row.add(textField, BorderLayout.CENTER);
        panel.add(row);
        panel.add(Box.createVerticalStrut(20)); // Añade un espacio vertical entre las filas
    }

    private void guardarPeticion() {

        String idPeticionString = idPeticionField.getText();
        String pacienteString = pacienteField.getText();
        String obraSocialString = obraSocialField.getText();
        String fechaCargaString = fechaCargaField.getText();
        String fechaEntregaString = fechaEntregaField.getText();
        String practicaAsociadaString = practicaAsociadaField.getText();

        if (idPeticionString.isEmpty() || pacienteString.isEmpty() || obraSocialString.isEmpty() || fechaCargaString.isEmpty() || fechaEntregaString.isEmpty() || practicaAsociadaString.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Date fechaCarga = Utils.parseDate(fechaCargaString);
        Date fechaEntrega = Utils.parseDate(fechaEntregaString);
        Optional<Paciente> pacienteOpt = PacienteController.getInstance().buscarPacientePorDni(pacienteString);
        Optional<Practica> practicaOpt = PracticaController.getInstance().buscarPracticaPorCodigo(Integer.parseInt(practicaAsociadaString));
        Resultado resultado = new Resultado();

        // Validaciones de que exista paciente y práctica

        if(PacienteController.getInstance().buscarPacientePorDni(pacienteString).orElse(null) == null) {
            JOptionPane.showMessageDialog(this, "Paciente inexistente.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(PracticaController.getInstance().buscarPracticaPorCodigo(Integer.parseInt(practicaAsociadaString)).orElse(null) == null) {
            JOptionPane.showMessageDialog(this, "Práctica inexistente.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (pacienteOpt.isPresent() && practicaOpt.isPresent()) {
            Paciente paciente = pacienteOpt.get();
            List<Integer> practicas = new ArrayList<>();
            practicas.add(Integer.parseInt(practicaAsociadaString));
            ArrayList<Resultado> resultados = new ArrayList<>();
            resultados.add(resultado);

            Peticion peticion = new Peticion(idPeticionString, paciente, obraSocialString, fechaCarga, fechaEntrega, practicas, resultados);

            PeticionController.getInstance().agregarPeticion(peticion);

            JOptionPane.showMessageDialog(this, "Peticion creada con exito.");

            clearFields();

        }
    }

    private void clearFields () {
        idPeticionField.setText("");
        pacienteField.setText("");
        obraSocialField.setText("");
        fechaCargaField.setText("");
        fechaEntregaField.setText("");
        practicaAsociadaField.setText("");
//            resultadosField.setText("");
    }
}
