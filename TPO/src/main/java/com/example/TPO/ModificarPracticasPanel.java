package com.example.TPO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificarPracticasPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField codigoBuscarField;
	private JTextField codigoField;
    private JTextField nombreField;
    private JTextField grupoField;
    private JTextField valoresCriticosField;
    private JTextField valoresReservadosField;
    private JTextField horasResultadoField;
    private JButton btnBuscar;
    private JButton btnGuardar;
    
    public ModificarPracticasPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Modificar Practica", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel buscarPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buscarPanel.add(new JLabel("CODIGO:"));
        codigoBuscarField = new JTextField();
        buscarPanel.add(codigoBuscarField);
        btnBuscar = new JButton("Buscar");
        buscarPanel.add(btnBuscar);

        add(buscarPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        
    	formPanel.add(new JLabel("Codigo:"));
        codigoField = new JTextField();
        formPanel.add(codigoField);

        formPanel.add(new JLabel("Nombre :"));
        nombreField = new JTextField();
        formPanel.add(nombreField);

        formPanel.add(new JLabel("Grupo:"));
        grupoField = new JTextField();
        formPanel.add(grupoField);
        
        formPanel.add(new JLabel("Valores Criticos:"));
        valoresCriticosField = new JTextField();
        formPanel.add(valoresCriticosField);

        formPanel.add(new JLabel("Valores Reservados:"));
        valoresReservadosField = new JTextField();
        formPanel.add(valoresReservadosField);

        formPanel.add(new JLabel("Horas Resultado:"));
        horasResultadoField = new JTextField();
        formPanel.add(horasResultadoField);


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