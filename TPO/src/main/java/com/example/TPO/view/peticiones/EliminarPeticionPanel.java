package com.example.TPO.view.peticiones;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EliminarPeticionPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTextField pacienteBuscarField;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JLabel obraSocialLabel;

    public EliminarPeticionPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Eliminar Peticion", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel buscarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buscarPanel.add(new JLabel("Paciente:"));
        pacienteBuscarField = new JTextField(20);
        buscarPanel.add(pacienteBuscarField);
        btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(144, 202, 249));
        buscarPanel.add(btnBuscar);

        add(buscarPanel, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Añadir espacio alrededor del panel

        infoPanel.add(new JLabel("Obra Social:"));
        obraSocialLabel = new JLabel();
        infoPanel.add(obraSocialLabel);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(new Color(255, 102, 102)); // Rojo claro
        infoPanel.add(btnEliminar);

        add(infoPanel, BorderLayout.SOUTH);

        // Deshabilitar botón Eliminar hasta que se busque un paciente
        btnEliminar.setEnabled(false);

        // Agregar ActionListeners
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPaciente();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarPaciente();
            }
        });
    }

    private void buscarPaciente() {
        // Aquí puedes implementar la lógica para buscar al paciente por su nombre o ID
        // por ejemplo:
        // String pacienteNombre = pacienteBuscarField.getText();
        // Optional<Paciente> paciente = pacienteService.buscarPacientePorNombre(pacienteNombre);

        // Simulación de búsqueda
        String pacienteNombre = pacienteBuscarField.getText();
        obraSocialLabel.setText("Obra Social: Ejemplo"); // Ejemplo de texto para la etiqueta de obra social
        btnEliminar.setEnabled(true);
    }

    private void eliminarPaciente() {
        // Aquí puedes implementar la lógica para eliminar al paciente
        // por ejemplo:
        // String pacienteNombre = pacienteBuscarField.getText();
        // boolean eliminado = pacienteService.eliminarPaciente(pacienteNombre);

        // Simulación de eliminación
        JOptionPane.showMessageDialog(this, "Peticion eliminada con éxito.");
        obraSocialLabel.setText("");
        pacienteBuscarField.setText("");
        btnEliminar.setEnabled(false);
    }
}
