package com.example.TPO.view.sucursales;

import com.example.TPO.controller.SucursalController;
import com.example.TPO.model.Sucursal;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Optional;

public class ModificarSucursalPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private final JFormattedTextField numeroBuscarField;
    private final JTextField direccionField;
    private final JTextField respTecnicoField;
    private final JButton btnGuardar;

    private final SucursalController sucursalController = SucursalController.getInstance();

    public ModificarSucursalPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Modificar Sucursal", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel buscarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buscarPanel.add(new JLabel("Número:"));
        numeroBuscarField = Utils.createFormattedTextField(); // Utilizando método de utilidad para crear campo formateado
        numeroBuscarField.setColumns(15);
        buscarPanel.add(numeroBuscarField);
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(144, 202, 249)); // Color celeste
        buscarPanel.add(btnBuscar);

        add(buscarPanel, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Espacios alrededor del panel

        formPanel.add(new JLabel("Dirección:"));
        direccionField = new JTextField();
        formPanel.add(direccionField);

        formPanel.add(new JLabel("Responsable Técnico:"));
        respTecnicoField = new JTextField();
        formPanel.add(respTecnicoField);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(144, 238, 144)); // Color verde claro
        formPanel.add(btnGuardar);

        add(formPanel, BorderLayout.SOUTH);

        habilitarFormulario(false);

        btnBuscar.addActionListener(e -> buscarSucursal());
        btnGuardar.addActionListener(e -> guardarSucursal());
    }

    private void habilitarFormulario(boolean habilitar) {
        direccionField.setEnabled(habilitar);
        respTecnicoField.setEnabled(habilitar);
        btnGuardar.setEnabled(habilitar);
    }

    private void buscarSucursal() {
        try {
            int numero = Integer.parseInt(numeroBuscarField.getText());
            Optional<Sucursal> sucursal = sucursalController.buscarSucursalPorNumero(numero);

            if (sucursal.isPresent()) {
                direccionField.setText(sucursal.get().getDireccion());
                respTecnicoField.setText(sucursal.get().getResponsableTecnico());
                habilitarFormulario(true);
            } else {
                JOptionPane.showMessageDialog(this, "Sucursal no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
                limpiarCampos();
                habilitarFormulario(false);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Número de sucursal inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardarSucursal() {
        try {
            int numero = Integer.parseInt(numeroBuscarField.getText());
            String direccion = direccionField.getText();
            String responsableTecnico = respTecnicoField.getText();

            if (direccion.isEmpty() || responsableTecnico.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos correctamente.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            sucursalController.modificarSucursal(numero, direccion, responsableTecnico);

            JOptionPane.showMessageDialog(this, "Sucursal modificada con éxito.");
            limpiarCampos();
            habilitarFormulario(false);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Número de sucursal inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        direccionField.setText("");
        respTecnicoField.setText("");
    }

    // Método de utilidad para crear un JFormattedTextField con formato de número entero
    private static class Utils {
        public static JFormattedTextField createFormattedTextField() {
            NumberFormat format = NumberFormat.getIntegerInstance();
            format.setGroupingUsed(false); // No usar separadores de miles
            format.setMinimumIntegerDigits(1); // Mínimo de dígitos enteros permitidos

            return new JFormattedTextField(format);
        }
    }
}
