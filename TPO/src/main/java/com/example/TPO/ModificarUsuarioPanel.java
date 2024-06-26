package com.example.TPO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificarUsuarioPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField nombreField;
    private JTextField apellidoField;
    private JTextField dniField;
    private JTextField fecNacField;
    private JTextField usuarioField;
    private JTextField correoField;
    private JTextField dniBuscarField;
    private JButton btnBuscar;
    private JButton btnGuardar;

    public ModificarUsuarioPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Modificar Usuario", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel buscarPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buscarPanel.add(new JLabel("DNI:"));
        dniBuscarField = new JTextField();
        buscarPanel.add(dniBuscarField);
        btnBuscar = new JButton("Buscar");
        buscarPanel.add(btnBuscar);

        add(buscarPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));


        formPanel.add(new JLabel("Usuario:"));
        usuarioField = new JTextField();
        formPanel.add(usuarioField);
        
        formPanel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        formPanel.add(nombreField);
        
        formPanel.add(new JLabel("Apellido:"));
        apellidoField = new JTextField();
        formPanel.add(apellidoField);
        
        formPanel.add(new JLabel("Correo:"));
        correoField = new JTextField();
        formPanel.add(correoField);

        formPanel.add(new JLabel("DNI:"));
        dniField = new JTextField();
        formPanel.add(dniField);
        
        formPanel.add(new JLabel("Fecha Nacimiento:"));
        fecNacField = new JTextField();
        formPanel.add(fecNacField);

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
        nombreField.setEnabled(habilitar);
        apellidoField.setEnabled(habilitar);
        dniField.setEnabled(habilitar);
        btnGuardar.setEnabled(habilitar);
    }

    private void buscarPaciente() {
        String dni = dniBuscarField.getText();

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

        // Simulación para el ejemplo:
        if (dni.equals("12345678")) {
            nombreField.setText("Juan");
            apellidoField.setText("Pérez");
            dniField.setText("12345678");
            habilitarFormulario(true);
        } else {
            JOptionPane.showMessageDialog(this, "Paciente no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            habilitarFormulario(false);
        }
    }

    private void guardarPaciente() {
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        String dni = dniField.getText();

        // Validaciones básicas
        if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Aquí puedes llamar a los métodos correspondientes para modificar el paciente
        // Por ejemplo: Paciente paciente = new Paciente(nombre, apellido, dni);
        // pacienteService.modificarPaciente(paciente);

        JOptionPane.showMessageDialog(this, "Paciente modificado con éxito.");
    }
}