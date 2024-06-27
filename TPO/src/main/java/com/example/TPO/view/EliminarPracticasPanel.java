package com.example.TPO.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EliminarPracticasPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTextField codigoBuscarField;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JLabel nombreLabel;

    public EliminarPracticasPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Eliminar Practica", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel buscarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buscarPanel.add(new JLabel("Código Practica:"));
        codigoBuscarField = new JTextField(20);
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

        // Deshabilitar botón Eliminar hasta que se busque una práctica
        btnEliminar.setEnabled(false);

        // Agregar ActionListeners
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
        // Aquí puedes implementar la lógica para buscar la práctica por su código
        // por ejemplo:
        // String codigoPractica = codigoBuscarField.getText();
        // Optional<Practica> practica = practicaService.buscarPracticaPorCodigo(codigoPractica);

        // Simulación de búsqueda
        String codigoPractica = codigoBuscarField.getText();
        nombreLabel.setText("Nombre de la práctica: Ejemplo"); // Ejemplo de texto para la etiqueta de nombre
        btnEliminar.setEnabled(true);
    }

    private void eliminarPractica() {
        // Aquí puedes implementar la lógica para eliminar la práctica
        // por ejemplo:
        // String codigoPractica = codigoBuscarField.getText();
        // boolean eliminada = practicaService.eliminarPractica(codigoPractica);

        // Simulación de eliminación
        JOptionPane.showMessageDialog(this, "Práctica eliminada con éxito.");
        nombreLabel.setText("");
        codigoBuscarField.setText("");
        btnEliminar.setEnabled(false);
    }
}