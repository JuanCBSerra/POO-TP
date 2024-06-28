package com.example.TPO.view;

import com.example.TPO.controller.PracticaController;
import com.example.TPO.controller.SucursalController;
import com.example.TPO.model.Practica;
import com.example.TPO.model.ValorCritico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearPracticasPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField codigoField;
    private JTextField nombreField;
    private JTextField grupoField;
    private JTextField valoresCriticosField;
    private JTextField valoresReservadosField;
    private JTextField horasResultadoField;

    private final PracticaController practicaController = PracticaController.getInstance();

    public CrearPracticasPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Crear Practica", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        addFormRow(formPanel, "Codigo:", codigoField = new JTextField(20));
        addFormRow(formPanel, "Nombre:", nombreField = new JTextField(20));
        addFormRow(formPanel, "Grupo:", grupoField = new JTextField(20));
        addFormRow(formPanel, "Valores Criticos:", valoresCriticosField = new JTextField(20));
        addFormRow(formPanel, "Valores Reservados:", valoresReservadosField = new JTextField(20));
        addFormRow(formPanel, "Horas Resultado:", horasResultadoField = new JTextField(20));

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(144, 238, 144)); // Verde claro
        btnGuardar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarPractica();
            }
        });

        formPanel.add(Box.createVerticalStrut(10)); // Añade un espacio vertical antes del botón
        formPanel.add(btnGuardar);

        add(formPanel, BorderLayout.CENTER);
    }

    private void addFormRow(JPanel panel, String labelText, JTextField textField) {
        JPanel row = new JPanel(new BorderLayout(10, 10));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, textField.getPreferredSize().height));
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(150, textField.getPreferredSize().height));
        row.add(label, BorderLayout.WEST);
        row.add(textField, BorderLayout.CENTER);
        panel.add(row);
        panel.add(Box.createVerticalStrut(10)); // Añade un espacio vertical entre las filas
    }

    private void guardarPractica() {
        String codigoString = codigoField.getText();
        String nombreString = nombreField.getText();
        String grupoString = grupoField.getText();
        String valoresCriticosString = valoresCriticosField.getText();
        String valoresReservadosString =  valoresReservadosField.getText();
        String horasResultadoString = horasResultadoField.getText();

        if (codigoString.isEmpty() || nombreString.isEmpty() || grupoString.isEmpty() || valoresCriticosString.isEmpty() || valoresReservadosString.isEmpty() || horasResultadoString.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int codigo = Integer.parseInt(codigoString);
        int horasResultado = Integer.parseInt(horasResultadoString);

        ValorCritico valorCritico = new ValorCritico() {
            @Override
            public boolean esCritico(String valorResultado) {
                return false;
            }
        };
        Boolean valorReservado = Boolean.TRUE;
        Boolean estaHabilitada = Boolean.TRUE;
        Practica practica = new Practica(codigo,nombreString,grupoString,valorCritico,valorReservado,horasResultado,estaHabilitada);

        practicaController.agregarPractica(practica);

        JOptionPane.showMessageDialog(this, "Practica creada con éxito.");
        clearFields();
    }

    private void clearFields() {
        codigoField.setText("");
        nombreField.setText("");
        grupoField.setText("");
        valoresCriticosField.setText("");
        valoresReservadosField.setText("");
        horasResultadoField.setText("");
    }
}
