package com.example.TPO.view.practicas;

import com.example.TPO.DTO.PracticaDTO;
import com.example.TPO.Utils;
import com.example.TPO.controller.PracticaController;
import com.example.TPO.model.ValorCritico;
import com.example.TPO.model.ValorCriticoNumerico;
import com.example.TPO.model.ValorCriticoString;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.Optional;

public class ModificarPracticasPanel extends JPanel {

    private final JTextField codigoBuscarField;
    private final JTextField codigoField;
    private final JTextField nombreField;
    private final JTextField grupoField;
    private final JTextField valoresCriticosField;
    private final JCheckBox valoresReservadosCheckBox;
    private final JFormattedTextField horasResultadoField;
    private final JCheckBox habilitadaCheckbox;
    private final JButton btnGuardar;

    private final PracticaController practicaController = PracticaController.getInstance();

    private final JRadioButton valorCriticoStringRadio;
    private final JRadioButton valorCriticoNumericoRadio;

    public ModificarPracticasPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Modificar Práctica", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        addFormRow(formPanel, "Código:", codigoBuscarField = new JTextField(20));
        JButton btnBuscar;
        addFormRow(formPanel, "Buscar:", btnBuscar = new JButton("Buscar"));

        formPanel.add(Box.createVerticalStrut(10));

        addFormRow(formPanel, "Código:", codigoField = new JTextField());
        addFormRow(formPanel, "Nombre:", nombreField = new JTextField());
        addFormRow(formPanel, "Grupo:", grupoField = new JTextField());
        addFormRow(formPanel, "Valores Críticos:", valoresCriticosField = new JTextField());
        addFormRow(formPanel, "Habilitada:", habilitadaCheckbox = new JCheckBox());

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

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(144, 238, 144)); // Color verde claro
        btnGuardar.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(btnGuardar);

        add(formPanel, BorderLayout.CENTER);

        habilitarFormulario(false);

        btnBuscar.addActionListener(e -> buscarPractica());
        btnGuardar.addActionListener(e -> guardarPractica());

        valorCriticoStringRadio.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                valorCriticoNumericoRadio.setSelected(false);
            }
        });

        valorCriticoNumericoRadio.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                valorCriticoStringRadio.setSelected(false);
            }
        });
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

    private void habilitarFormulario(boolean habilitar) {
        codigoField.setEnabled(habilitar);
        nombreField.setEnabled(habilitar);
        grupoField.setEnabled(habilitar);
        valoresCriticosField.setEnabled(habilitar);
        valoresReservadosCheckBox.setEnabled(habilitar);
        horasResultadoField.setEnabled(habilitar);
        btnGuardar.setEnabled(habilitar);
        valorCriticoStringRadio.setEnabled(habilitar);
        valorCriticoNumericoRadio.setEnabled(habilitar);
    }

    private void buscarPractica() {
        try {
            int codigo = Integer.parseInt(codigoBuscarField.getText());
            Optional<PracticaDTO> practicaOptional = practicaController.getPractica(codigo);

            if (practicaOptional.isPresent()) {
                PracticaDTO practica = practicaOptional.get();
                codigoField.setText(String.valueOf(practica.getCodigo()));
                nombreField.setText(practica.getNombre());
                grupoField.setText(practica.getGrupo());
                if (practica.getValorCritico() instanceof ValorCriticoString) {
                    valorCriticoStringRadio.setSelected(true);
                    valoresCriticosField.setText(((ValorCriticoString) practica.getValorCritico()).getValorCritico());
                } else if (practica.getValorCritico() instanceof ValorCriticoNumerico valorCriticoNumerico) {
                    valorCriticoNumericoRadio.setSelected(true);
                    valoresCriticosField.setText(valorCriticoNumerico.getValorMinimo() + ", " + valorCriticoNumerico.getValorMaximo());
                }
                valoresReservadosCheckBox.setSelected(practica.isValorReservado());
                habilitadaCheckbox.setSelected(practica.isHabilitada());
                horasResultadoField.setText(String.valueOf(practica.getCantidadHoras()));
                habilitarFormulario(true);
            } else {
                JOptionPane.showMessageDialog(this, "Práctica no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
                limpiarCampos();
                habilitarFormulario(false);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Número de Práctica inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardarPractica() {
        try {
            int codigo = Integer.parseInt(codigoField.getText());
            String nombre = nombreField.getText();
            String grupo = grupoField.getText();
            String valoresCriticosString = valoresCriticosField.getText();
            boolean valoresReservados = valoresReservadosCheckBox.isSelected();
            int horasResultado = Integer.parseInt(horasResultadoField.getText());
            boolean estaHabilitada = habilitadaCheckbox.isSelected();

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

            practicaController.modificarPractica(codigo, nombre, grupo, valorCritico, valoresReservados, horasResultado, estaHabilitada);

            JOptionPane.showMessageDialog(this, "Práctica modificada con éxito.");
            limpiarCampos();
            habilitarFormulario(false);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Número de Práctica inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        codigoField.setText(null);
        nombreField.setText("");
        grupoField.setText("");
        valoresCriticosField.setText("");
        valoresReservadosCheckBox.setSelected(false);
        horasResultadoField.setValue(null);
        valorCriticoStringRadio.setSelected(false);
        valorCriticoNumericoRadio.setSelected(false);
        habilitadaCheckbox.setSelected(false);
    }
}
