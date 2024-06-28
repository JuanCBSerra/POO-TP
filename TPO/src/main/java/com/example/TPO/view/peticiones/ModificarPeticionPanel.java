package com.example.TPO.view.peticiones;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificarPeticionPanel extends JPanel {
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

        JLabel titulo = new JLabel("Modificar Petición", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel buscarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buscarPanel.add(new JLabel("Paciente:"));
        pacienteBuscarField = new JTextField(20);
        buscarPanel.add(pacienteBuscarField);
        btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(144, 202, 249)); // Color celeste
        buscarPanel.add(btnBuscar);

        add(buscarPanel, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridLayout(6,2,8, 8));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Espacios alrededor del panel

        formPanel.add(new JLabel("Obra Social:"));
        obraSocialField = new JTextField();
        formPanel.add(obraSocialField);

        formPanel.add(new JLabel("Fecha de Carga:"));
        fechaCargaField = new JTextField();
        formPanel.add(fechaCargaField);

        formPanel.add(new JLabel("Práctica Asociada:"));
        practicaAsociadaField = new JTextField();
        formPanel.add(practicaAsociadaField);

        formPanel.add(new JLabel("Fecha Estimada de Entrega:"));
        fechaEntregaField = new JTextField();
        formPanel.add(fechaEntregaField);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(144, 238, 144)); // Color verde claro
        formPanel.add(btnGuardar);

        add(formPanel, BorderLayout.SOUTH);

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
        obraSocialField.setEnabled(habilitar);
        fechaCargaField.setEnabled(habilitar);
        practicaAsociadaField.setEnabled(habilitar);
        fechaEntregaField.setEnabled(habilitar);
        btnGuardar.setEnabled(habilitar);
    }

    private void buscarPaciente() {
        // Implementación simulada para buscar el paciente y cargar datos estáticos
        String paciente = pacienteBuscarField.getText();
        obraSocialField.setText("OSDE"); // Datos estáticos para demostración
        fechaCargaField.setText("2024-06-30"); // Datos estáticos para demostración
        practicaAsociadaField.setText("Análisis de Sangre"); // Datos estáticos para demostración
        fechaEntregaField.setText("2024-07-05"); // Datos estáticos para demostración

        // Habilitar el formulario después de buscar al paciente
        habilitarFormulario(true);
    }

    private void guardarPaciente() {
        // Implementación simulada para guardar la petición modificada
        // Podrías implementar aquí la lógica para guardar los datos ingresados

        JOptionPane.showMessageDialog(this, "Petición modificada con éxito.");
        limpiarCampos();
        habilitarFormulario(false);
    }

    private void limpiarCampos() {
        obraSocialField.setText("");
        fechaCargaField.setText("");
        practicaAsociadaField.setText("");
        fechaEntregaField.setText("");
    }
}
