package com.example.TPO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificarSucursalPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField numeroBuscarField;
    private JTextField numeroField;
    private JTextField direccionField;
    private JTextField respTecnicoField;
    private JButton btnBuscar;
    private JButton btnGuardar;

    public ModificarSucursalPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Modificar Sucursal", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel buscarPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buscarPanel.add(new JLabel("Numero:"));
        numeroBuscarField = new JTextField();
        buscarPanel.add(numeroBuscarField);
        btnBuscar = new JButton("Buscar");
        buscarPanel.add(btnBuscar);

        add(buscarPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        formPanel.add(new JLabel("Numero:"));
        numeroField = new JTextField();
        formPanel.add(numeroField);

        formPanel.add(new JLabel("Direccion:"));
        direccionField = new JTextField();
        formPanel.add(direccionField);

        formPanel.add(new JLabel("Responsable Tecnico:"));
        respTecnicoField = new JTextField();
        formPanel.add(respTecnicoField);

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
        
        btnGuardar.setEnabled(habilitar);
    }

    private void buscarPaciente() {

        // Aquí deberías buscar el paciente por su DNI y cargar los datos en los campos
        // Por ejemplo: Paciente paciente = pacienteService.buscarPorDni(dni);
        // if (paciente != null) {
        //     nombreField.setText(paciente.getNombre());
        //     apellidoField.setText(paciente.getApellido());
        //     dniField.setText(paciente.getDni());
        //     habilitarFormulario(true);
        // } else {
        //     JOptionPane.showMessageDialog(this, "Paciente no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        //     habilitarFormulario(false);
        // }

      
    }

    private void guardarPaciente() {
        

        // Aquí puedes llamar a los métodos correspondientes para modificar el paciente
        // Por ejemplo: Paciente paciente = new Paciente(nombre, apellido, dni);
        // pacienteService.modificarPaciente(paciente);

        JOptionPane.showMessageDialog(this, "Paciente modificado con éxito.");
    }
}