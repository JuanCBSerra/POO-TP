package com.example.TPO.view.practicas;

import com.example.TPO.DTO.PracticaDTO;
import com.example.TPO.Utils;
import com.example.TPO.controller.PracticaController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class EliminarPracticasPanel extends JPanel {
    private final JFormattedTextField codigoBuscarField;
    private final JButton btnEliminar;
    private final JLabel nombreLabel;

    private final PracticaController practicaController = PracticaController.getInstance();

    public EliminarPracticasPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Eliminar Práctica", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel buscarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buscarPanel.add(new JLabel("Código Práctica:"));

        codigoBuscarField = Utils.createFormattedTextField();
        codigoBuscarField.setColumns(10);
        buscarPanel.add(codigoBuscarField);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(144, 202, 249));
        buscarPanel.add(btnBuscar);

        add(buscarPanel, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        infoPanel.add(new JLabel("Nombre:"));
        nombreLabel = new JLabel();
        infoPanel.add(nombreLabel);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(new Color(255, 102, 102));
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
            Optional<PracticaDTO> practica = practicaController.getPractica(codigo);

            if (practica.isPresent()) {
                nombreLabel.setText(practica.get().getNombre());
                btnEliminar.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(this, "Práctica no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Número de Práctica inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarPractica() {
        try {
            int codigo = Integer.parseInt(codigoBuscarField.getText());
            practicaController.deshabilitarPractica(codigo);
            JOptionPane.showMessageDialog(this, "Práctica eliminada con éxito.");
            nombreLabel.setText("");
            codigoBuscarField.setValue(null);  // Limpiando el campo después de eliminar
            btnEliminar.setEnabled(false);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Número de Práctica inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
