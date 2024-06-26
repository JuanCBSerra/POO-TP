package com.example.TPO.view;

import com.example.TPO.controller.PacienteController;
import com.example.TPO.model.Paciente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class EliminarPacientePanel extends JPanel {
    /**
	 * 
	 */
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

        JPanel buscarPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buscarPanel.add(new JLabel("DNI:"));
        dniBuscarField = new JTextField();
        buscarPanel.add(dniBuscarField);
        btnBuscar = new JButton("Buscar");
        buscarPanel.add(btnBuscar);

        add(buscarPanel, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        infoPanel.add(new JLabel("Nombre:"));
        nombreLabel = new JLabel();
        infoPanel.add(nombreLabel);

        btnEliminar = new JButton("Eliminar");
        infoPanel.add(btnEliminar);

        add(infoPanel, BorderLayout.CENTER);

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
        String dni = dniBuscarField.getText();

        Optional<Paciente> paciente = pacienteController.buscarPacientePorDni(dni);

         if (paciente.isPresent()) {
             nombreLabel.setText(paciente.get().getNombre());
             btnEliminar.setEnabled(true);
         } else {
             JOptionPane.showMessageDialog(this, "Paciente no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
             btnEliminar.setEnabled(false);
         }

    }

    private void eliminarPaciente() {
        String dni = dniBuscarField.getText();

        boolean pacienteEliminado = pacienteController.eliminarPaciente(dni);

        if(pacienteEliminado) {
            JOptionPane.showMessageDialog(this, "Paciente eliminado con éxito.");
        }else{
            JOptionPane.showMessageDialog(this, "No se pudo eliminar al paciente");
        }

        nombreLabel.setText("");
        dniBuscarField.setText("");
        btnEliminar.setEnabled(false);
    }
}