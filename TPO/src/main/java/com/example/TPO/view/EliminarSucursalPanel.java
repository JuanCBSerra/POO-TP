package com.example.TPO.view;

import com.example.TPO.Utils;
import com.example.TPO.controller.SucursalController;
import com.example.TPO.model.Sucursal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class EliminarSucursalPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JFormattedTextField numeroBuscarField;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JLabel direccionLabel;
    private JLabel responsableTecnicoLabel;

    private final SucursalController sucursalController = SucursalController.getInstance();

    public EliminarSucursalPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Eliminar Sucursal", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel buscarPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buscarPanel.add(new JLabel("Numero:"));
        numeroBuscarField = Utils.createFormattedTextField();
        buscarPanel.add(numeroBuscarField);
        btnBuscar = new JButton("Buscar");
        buscarPanel.add(btnBuscar);

        add(buscarPanel, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        infoPanel.add(new JLabel("Direccion:"));
        direccionLabel = new JLabel();
        infoPanel.add(direccionLabel);

        infoPanel.add(new JLabel("Responsable Tecnico:"));
        responsableTecnicoLabel = new JLabel();
        infoPanel.add(responsableTecnicoLabel);

        btnEliminar = new JButton("Eliminar");
        infoPanel.add(btnEliminar);

        add(infoPanel, BorderLayout.CENTER);

        btnEliminar.setEnabled(false);

        btnBuscar.addActionListener(e -> buscarSucursal());

        btnEliminar.addActionListener(e -> eliminarSucursal());
    }

    private void buscarSucursal() {
        int numero = Integer.parseInt(numeroBuscarField.getText());
        Optional<Sucursal> sucursal = sucursalController.buscarSucursalPorNumero(numero);

        if (sucursal.isPresent()) {
            direccionLabel.setText(sucursal.get().getDireccion());
            responsableTecnicoLabel.setText(sucursal.get().getResponsableTecnico());
            btnEliminar.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "Sucursal no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
            btnEliminar.setEnabled(false);
        }

       
    }

    private void eliminarSucursal() {
        int numero = Integer.parseInt(numeroBuscarField.getText());
        boolean sucursalEliminada = sucursalController.eliminarSucursal(numero);

        if(sucursalEliminada) {
            JOptionPane.showMessageDialog(this, "Sucursal eliminada con éxito.");
        }else{
            JOptionPane.showMessageDialog(this, "No se pudo eliminar a la sucursal");
        }

        direccionLabel.setText("");
        responsableTecnicoLabel.setText("");
        btnEliminar.setEnabled(false);
    }
}