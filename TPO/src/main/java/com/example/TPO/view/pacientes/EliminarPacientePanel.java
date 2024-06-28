package com.example.TPO.view.pacientes;

import com.example.TPO.controller.PacienteController;
import com.example.TPO.model.Paciente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class EliminarPacientePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private final JTextField dniBuscarField;
    private JButton btnBuscar;
    private final JButton btnEliminar;
    private final JLabel nombreLabel;

    private final PacienteController pacienteController = PacienteController.getInstance();

    public EliminarPacientePanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Eliminar Paciente", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        // Panel para buscar paciente
        JPanel buscarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Alineación y espaciado
        buscarPanel.add(new JLabel("DNI:"));
        dniBuscarField = new JTextField(20);
        buscarPanel.add(dniBuscarField);
        btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(144, 202, 249));
        buscarPanel.add(btnBuscar);

        add(buscarPanel, BorderLayout.CENTER); // Cambiado a BorderLayout.CENTER para que esté debajo del título

        // Panel para mostrar información y eliminar paciente
        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 10, 10)); // GridLayout para organizar elementos
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Espacios alrededor del panel

        infoPanel.add(new JLabel("Nombre:"));
        nombreLabel = new JLabel();
        infoPanel.add(nombreLabel);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(new Color(255, 102, 102)); // Cambio de color a rojo
        infoPanel.add(btnEliminar);

        add(infoPanel, BorderLayout.SOUTH); // Cambiado a BorderLayout.SOUTH para que esté debajo del panel de búsqueda

        btnEliminar.setEnabled(false);

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
        String dni = dniBuscarField.getText();

        Optional<Paciente> paciente = pacienteController.buscarPacientePorDni(dni);

        if (paciente.isPresent()) {
            nombreLabel.setText(paciente.get().getNombre());
            btnEliminar.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "Paciente no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            nombreLabel.setText("");
            btnEliminar.setEnabled(false);
        }
    }

    private void eliminarPaciente() {
        String dni = dniBuscarField.getText();

        boolean pacienteEliminado = pacienteController.eliminarPaciente(dni);

        if (pacienteEliminado) {
            JOptionPane.showMessageDialog(this, "Paciente eliminado con éxito.");
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo eliminar al paciente", "Error", JOptionPane.ERROR_MESSAGE);
        }

        nombreLabel.setText("");
        dniBuscarField.setText("");
        btnEliminar.setEnabled(false);
    }
}
