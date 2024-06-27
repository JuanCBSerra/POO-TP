package com.example.TPO.view;

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
        // Aquí puedes llamar a los métodos correspondientes para crear la práctica
        // Por ejemplo: Practica nuevaPractica = new Practica(...);
        // practicaService.crearPractica(nuevaPractica);

        JOptionPane.showMessageDialog(this, "Práctica creada con éxito.");
    }
}
