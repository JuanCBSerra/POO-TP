package com.example.TPO.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificarPeticionPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField pacienteBuscarField;
    private JTextField obraSocialField;
    private JTextField fechaCargaField;
    private JTextField practicaAsociadaField;
    private JTextField fechaEntregaField;
    private JButton btnBuscar;
    private JButton btnGuardar;

    public ModificarPeticionPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Modificar Peticion", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel buscarPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buscarPanel.add(new JLabel("Paciente:"));
        pacienteBuscarField = new JTextField();
        buscarPanel.add(pacienteBuscarField);
        btnBuscar = new JButton("Buscar");
        buscarPanel.add(btnBuscar);

        add(buscarPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        
        
        formPanel.add(new JLabel("Obra Social:"));
        obraSocialField = new JTextField();
        formPanel.add(obraSocialField);

        formPanel.add(new JLabel("Fecha de Carga:"));
        fechaCargaField = new JTextField();
        formPanel.add(fechaCargaField);

        formPanel.add(new JLabel("Practica Asociada:"));
        practicaAsociadaField = new JTextField();
        formPanel.add(practicaAsociadaField);
        
        formPanel.add(new JLabel("Fecha Estimada Entrega:"));
        fechaEntregaField = new JTextField();
        formPanel.add(fechaEntregaField);

        btnGuardar = new JButton("Guardar");
        formPanel.add(btnGuardar);

        add(formPanel, BorderLayout.CENTER);

        // Deshabilitar el panel de formulario hasta que se busque un paciente
        habilitarFormulario(false);

        // Agregar ActionListeners
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPaciente();
            }
        });

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarPaciente();
            }
        });
    }

    private void habilitarFormulario(boolean habilitar) {
    }

    private void buscarPaciente() {
    }

    private void guardarPaciente() {

        JOptionPane.showMessageDialog(this, "Paciente modificado con éxito.");
    }
}