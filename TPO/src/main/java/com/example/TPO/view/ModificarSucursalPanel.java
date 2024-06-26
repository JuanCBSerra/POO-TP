package com.example.TPO.view;

import com.example.TPO.Utils;
import com.example.TPO.controller.SucursalController;
import com.example.TPO.model.Sucursal;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        JPanel buscarPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buscarPanel.add(new JLabel("Numero:"));
        numeroBuscarField = Utils.createFormattedTextField();
        buscarPanel.add(numeroBuscarField);
        JButton btnBuscar = new JButton("Buscar");
        buscarPanel.add(btnBuscar);

        add(buscarPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        formPanel.add(new JLabel("Direccion:"));
        direccionField = new JTextField();
        formPanel.add(direccionField);

        formPanel.add(new JLabel("Responsable Tecnico:"));
        respTecnicoField = new JTextField();
        formPanel.add(respTecnicoField);

        btnGuardar = new JButton("Guardar");
        formPanel.add(btnGuardar);

        add(formPanel, BorderLayout.CENTER);

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
        int numero  = Integer.parseInt(numeroBuscarField.getText());
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

    }

    private void guardarSucursal() {
        int numero = Integer.parseInt(numeroBuscarField.getText());
        String direccion = direccionField.getText();
        String responsableTecnico = respTecnicoField.getText();

        if(direccion.isEmpty() || responsableTecnico.isEmpty()){
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos correctamente.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        sucursalController.modificarSucursal(
                numero,
                direccion,
                responsableTecnico
        );

        JOptionPane.showMessageDialog(this, "Sucursal modificada con éxito.");
        limpiarCampos();
        habilitarFormulario(false);
    }

    private void limpiarCampos() {
        direccionField.setText("");
        respTecnicoField.setText("");
    }
}