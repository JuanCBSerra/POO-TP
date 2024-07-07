package com.example.TPO.view.sucursales;

import com.example.TPO.DTO.SucursalDTO;
import com.example.TPO.controller.SucursalController;
import com.example.TPO.model.Sucursal;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class EliminarSucursalPanel extends JPanel {

    private final JFormattedTextField numeroBuscarField;
    private final JButton btnEliminar;
    private final JLabel direccionLabel;
    private final JLabel responsableTecnicoLabel;
    private final JFormattedTextField numeroSucursalDerivacionesField;

    private final SucursalController sucursalController = SucursalController.getInstance();

    public EliminarSucursalPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Eliminar Sucursal", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel buscarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10,10));
        buscarPanel.add(new JLabel("Número:"));
        numeroBuscarField = Utils.createFormattedTextField();
        numeroBuscarField.setColumns(15);
        buscarPanel.add(numeroBuscarField);
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(144, 202, 249));
        buscarPanel.add(btnBuscar);

        add(buscarPanel, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        infoPanel.add(new JLabel("Dirección:"));
        direccionLabel = new JLabel();
        infoPanel.add(direccionLabel);

        infoPanel.add(new JLabel("Responsable Técnico:"));
        responsableTecnicoLabel = new JLabel();
        infoPanel.add(responsableTecnicoLabel);

        infoPanel.add(new JLabel("Sucursal a derivar: "));
        numeroSucursalDerivacionesField = Utils.createFormattedTextField();
        numeroSucursalDerivacionesField.setColumns(15);
        infoPanel.add(numeroSucursalDerivacionesField);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(new Color(255, 102, 102));
        infoPanel.add(btnEliminar);

        add(infoPanel, BorderLayout.SOUTH);

        btnEliminar.setEnabled(false);

        btnBuscar.addActionListener(e -> buscarSucursal());

        btnEliminar.addActionListener(e -> eliminarSucursal());
    }

    private void buscarSucursal() {
        int numero = Integer.parseInt(numeroBuscarField.getText());
        Optional<SucursalDTO> sucursal = sucursalController.getSucursal(numero);

        if (sucursal.isPresent()) {
            direccionLabel.setText(sucursal.get().getDireccion());
            responsableTecnicoLabel.setText(sucursal.get().getResponsableTecnico().getUsername());
            btnEliminar.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "Sucursal no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
            btnEliminar.setEnabled(false);
        }
    }

    private void eliminarSucursal() {
        int numero = Integer.parseInt(numeroBuscarField.getText());

        if(numeroSucursalDerivacionesField.getText() == null){
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int numeroSucursalADerivar = Integer.parseInt(numeroSucursalDerivacionesField.getText());

        try{
            sucursalController.eliminarSucursal(numero, numeroSucursalADerivar);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Sucursal eliminada con éxito.");

        direccionLabel.setText("");
        responsableTecnicoLabel.setText("");
        btnEliminar.setEnabled(false);
    }

    private static class Utils {
        public static JFormattedTextField createFormattedTextField() {
            return new JFormattedTextField();
        }
    }
}
