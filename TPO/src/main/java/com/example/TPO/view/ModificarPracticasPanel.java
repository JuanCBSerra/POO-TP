package com.example.TPO.view;

import com.example.TPO.controller.PracticaController;
import com.example.TPO.controller.SucursalController;
import com.example.TPO.model.Practica;
import com.example.TPO.model.Sucursal;
import com.example.TPO.model.ValorCritico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

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

    private final PracticaController practicaController = PracticaController.getInstance();

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
        try {
            int codigo = Integer.parseInt(codigoBuscarField.getText());
            Optional<Practica> practica = practicaController.buscarPracticaPorCodigo(codigo);

            if (practica.isPresent()) {
                nombreField.setText(practica.get().getNombre());
                grupoField.setText(practica.get().getGrupo());
                valoresCriticosField.setText("Test");
                valoresReservadosField.setText("True");
                horasResultadoField.setText("1234");
                habilitarFormulario(true);
            } else {
                JOptionPane.showMessageDialog(this, "Practica no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
                limpiarCampos();
                habilitarFormulario(false);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Número de Practica inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardarPractica() {
        try {
            String codigoString = codigoField.getText();
            String nombreString = nombreField.getText();
            String grupoString = grupoField.getText();
            String valoresCriticosString = valoresCriticosField.getText();
            String valoresReservadosString =  valoresReservadosField.getText();
            String horasResultadoString = horasResultadoField.getText();

            if (codigoString.isEmpty() || nombreString.isEmpty() || grupoString.isEmpty() || valoresCriticosString.isEmpty() || valoresReservadosString.isEmpty() || horasResultadoString.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int codigo = Integer.parseInt(codigoString);
            int horasResultado = Integer.parseInt(horasResultadoString);
            ValorCritico valorCritico = new ValorCritico() {
                @Override
                public boolean esCritico(String valorResultado) {
                    return false;
                }
            };
            Boolean valorReservado = Boolean.TRUE;
            Boolean estaHabilitada = Boolean.TRUE;

            Practica practica = new Practica(codigo,nombreString,grupoString,valorCritico,valorReservado,horasResultado,estaHabilitada);

            practicaController.modificarPractica(codigo, practica);

            JOptionPane.showMessageDialog(this, "Practica modificada con éxito.");
            limpiarCampos();
            habilitarFormulario(false);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Número de Pratica inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
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
