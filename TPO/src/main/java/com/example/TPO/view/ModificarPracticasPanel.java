package com.example.TPO.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificarPracticasPanel extends JPanel {
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

        JLabel titulo = new JLabel("Modificar Práctica", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel buscarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buscarPanel.add(new JLabel("Código:"));
        codigoBuscarField = new JTextField(20);
        buscarPanel.add(codigoBuscarField);
        btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(144, 202, 249)); // Color celeste
        buscarPanel.add(btnBuscar);

        add(buscarPanel, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10)); // Ajustado a 7 filas para coincidir con el número de campos
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20)); // Espacios alrededor del panel

        formPanel.add(new JLabel("Código:"));
        codigoField = new JTextField();
        formPanel.add(codigoField);

        formPanel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        formPanel.add(nombreField);

        formPanel.add(new JLabel("Grupo:"));
        grupoField = new JTextField();
        formPanel.add(grupoField);

        formPanel.add(new JLabel("Valores Críticos:"));
        valoresCriticosField = new JTextField();
        formPanel.add(valoresCriticosField);

        formPanel.add(new JLabel("Valores Reservados:"));
        valoresReservadosField = new JTextField();
        formPanel.add(valoresReservadosField);

        formPanel.add(new JLabel("Horas Resultado:"));
        horasResultadoField = new JTextField();
        formPanel.add(horasResultadoField);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(144, 238, 144)); // Color verde
        formPanel.add(btnGuardar);

        add(formPanel, BorderLayout.SOUTH);

        habilitarFormulario(false);

        btnBuscar.addActionListener(e -> buscarPractica());
        btnGuardar.addActionListener(e -> guardarPractica());
    }

    private void habilitarFormulario(boolean habilitar) {
        codigoField.setEnabled(habilitar);
        nombreField.setEnabled(habilitar);
        grupoField.setEnabled(habilitar);
        valoresCriticosField.setEnabled(habilitar);
        valoresReservadosField.setEnabled(habilitar);
        horasResultadoField.setEnabled(habilitar);
        btnGuardar.setEnabled(habilitar);
    }

    private void buscarPractica() {
        String codigo = codigoBuscarField.getText();
        // Aquí implementa la lógica para buscar la práctica según el código
        // Puedes usar un controlador o servicio similar al PacienteController
        // Ejemplo ficticio:
        // Practica practica = practicaController.buscarPracticaPorCodigo(codigo);
        // Luego actualizas los campos del formulario con los datos encontrados

        habilitarFormulario(true); // Habilita el formulario después de la búsqueda
    }

    private void guardarPractica() {
        // Aquí implementa la lógica para guardar la práctica modificada
        // Puedes usar un controlador o servicio similar al PacienteController
        // Ejemplo ficticio:
        // practicaController.modificarPractica(codigo, nombre, grupo, valoresCriticos, valoresReservados, horasResultado);

        JOptionPane.showMessageDialog(this, "Práctica modificada con éxito.");
        limpiarCampos();
        habilitarFormulario(false);
    }

    private void limpiarCampos() {
        codigoField.setText("");
        nombreField.setText("");
        grupoField.setText("");
        valoresCriticosField.setText("");
        valoresReservadosField.setText("");
        horasResultadoField.setText("");
    }
}
