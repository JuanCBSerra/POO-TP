package com.example.TPO.view.practicas;

import com.example.TPO.Utils;
import com.example.TPO.controller.PracticaController;
import com.example.TPO.model.ValorCritico;
import com.example.TPO.model.ValorCriticoNumerico;
import com.example.TPO.model.ValorCriticoString;

import javax.swing.*;
import java.awt.*;

public class CrearPracticasPanel extends JPanel {

    private final JFormattedTextField codigoField;
    private final JTextField nombreField;
    private final JTextField grupoField;
    private final JTextField valoresCriticosField;
    private final JCheckBox valoresReservadosCheckBox;
    private final JFormattedTextField horasResultadoField;
    private final JCheckBox habilitadaCheckbox;

    private final PracticaController practicaController = PracticaController.getInstance();

    private final JRadioButton valorCriticoStringRadio;
    private final JRadioButton valorCriticoNumericoRadio;

    public CrearPracticasPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Crear Practica", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        addFormRow(formPanel, "Codigo:", codigoField = Utils.createFormattedTextField());
        addFormRow(formPanel, "Nombre:", nombreField = new JTextField(20));
        addFormRow(formPanel, "Grupo:", grupoField = new JTextField(20));
        addFormRow(formPanel, "Valores Criticos:", valoresCriticosField = new JTextField(20));

        JPanel valorCriticoPanel = new JPanel();
        valorCriticoPanel.setLayout(new BoxLayout(valorCriticoPanel, BoxLayout.Y_AXIS));
        valorCriticoPanel.setBorder(BorderFactory.createTitledBorder("Tipo de Valor Crítico"));

        valorCriticoStringRadio = new JRadioButton("Valor Crítico String");
        valorCriticoNumericoRadio = new JRadioButton("Valor Crítico Numérico");

        ButtonGroup valorCriticoGroup = new ButtonGroup();
        valorCriticoGroup.add(valorCriticoStringRadio);
        valorCriticoGroup.add(valorCriticoNumericoRadio);

        valorCriticoPanel.add(valorCriticoStringRadio);
        valorCriticoPanel.add(valorCriticoNumericoRadio);

        formPanel.add(valorCriticoPanel);

        addFormRow(formPanel, "Valores Reservados:", valoresReservadosCheckBox = new JCheckBox());
        addFormRow(formPanel, "Horas Resultado:", horasResultadoField = Utils.createFormattedTextField());
        addFormRow(formPanel, "Habilitada:", habilitadaCheckbox = new JCheckBox());

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(144, 238, 144));
        btnGuardar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGuardar.addActionListener(e -> guardarPractica());

        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(btnGuardar);

        add(formPanel, BorderLayout.CENTER);
    }

    private void addFormRow(JPanel panel, String labelText, JComponent component) {
        JPanel row = new JPanel(new BorderLayout(10, 10));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, component.getPreferredSize().height));
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(150, component.getPreferredSize().height));
        row.add(label, BorderLayout.WEST);
        row.add(component, BorderLayout.CENTER);
        panel.add(row);
        panel.add(Box.createVerticalStrut(10));
    }

    private void guardarPractica() {
        String codigoString = codigoField.getText();
        String nombreString = nombreField.getText();
        String grupoString = grupoField.getText();
        String valoresCriticosString = valoresCriticosField.getText();
        boolean valoresReservados = valoresReservadosCheckBox.isSelected();
        String horasResultadoString = horasResultadoField.getText();
        boolean habilitada = habilitadaCheckbox.isSelected();

        if (codigoString.isEmpty() || nombreString.isEmpty() || grupoString.isEmpty() || valoresCriticosString.isEmpty() || horasResultadoString.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int codigo = Integer.parseInt(codigoString);
        int horasResultado = Integer.parseInt(horasResultadoString);

        ValorCritico valorCritico = null;

        if (valorCriticoStringRadio.isSelected()) {
            valorCritico = new ValorCriticoString(valoresCriticosString);
        } else if (valorCriticoNumericoRadio.isSelected()) {
            String[] valores = valoresCriticosString.split(",");
            if (valores.length == 2) {
                try {
                    int valorMinimo = Integer.parseInt(valores[0].trim());
                    int valorMaximo = Integer.parseInt(valores[1].trim());
                    valorCritico = new ValorCriticoNumerico(valorMinimo, valorMaximo);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Formato inválido para valores críticos numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Formato inválido para valores críticos numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        if (valorCritico == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un tipo de valor crítico.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        practicaController.agregarPractica(codigo, nombreString, grupoString, valorCritico, valoresReservados, horasResultado, habilitada);

        JOptionPane.showMessageDialog(this, "Practica creada con éxito.");
        clearFields();
    }

    private void clearFields() {
        codigoField.setValue(null);
        nombreField.setText("");
        grupoField.setText("");
        valoresCriticosField.setText("");
        valoresReservadosCheckBox.setSelected(false);
        horasResultadoField.setValue(null);
        habilitadaCheckbox.setSelected(false);
        valorCriticoStringRadio.setSelected(false);
        valorCriticoNumericoRadio.setSelected(false);
    }
}
