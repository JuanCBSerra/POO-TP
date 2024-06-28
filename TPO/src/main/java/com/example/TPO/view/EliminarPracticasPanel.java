package com.example.TPO.view;

import com.example.TPO.Utils;
import com.example.TPO.controller.PracticaController;
import com.example.TPO.model.Practica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class EliminarPracticasPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private JFormattedTextField codigoBuscarField;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JLabel nombreLabel;

    private final PracticaController practicaController = PracticaController.getInstance();

    public EliminarPracticasPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Eliminar Practica", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel buscarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buscarPanel.add(new JLabel("Código Practica:"));
        codigoBuscarField = Utils.createFormattedTextField();
        buscarPanel.add(codigoBuscarField);
        btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(144, 202, 249));
        buscarPanel.add(btnBuscar);

        add(buscarPanel, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Añadir espacio alrededor del panel

        infoPanel.add(new JLabel("Nombre:"));
        nombreLabel = new JLabel();
        infoPanel.add(nombreLabel);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(new Color(255, 102, 102)); // Rojo claro
        infoPanel.add(btnEliminar);

        add(infoPanel, BorderLayout.SOUTH);

        btnEliminar.setEnabled(false);

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPractica();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarPractica();
            }
        });
    }


    private void buscarPractica() {
        try {
            int codigo = Integer.parseInt(codigoBuscarField.getText());
            Optional<Practica> practica = practicaController.buscarPracticaPorCodigo(codigo);

            if (practica.isPresent()) {
                nombreLabel.setText(practica.get().getNombre());
                btnEliminar.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(this, "Practica no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);

            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Número de Practica inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void eliminarPractica() {
        int codigo = Integer.parseInt(codigoBuscarField.getText());
        practicaController.deshabilitarPractica(codigo);
        JOptionPane.showMessageDialog(this, "Práctica eliminada con éxito.");
        nombreLabel.setText("");
        codigoBuscarField.setText("");
        btnEliminar.setEnabled(false);
    }
}
