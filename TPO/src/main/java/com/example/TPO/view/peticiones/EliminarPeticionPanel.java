package com.example.TPO.view.peticiones;

import com.example.TPO.controller.PeticionController;
import com.example.TPO.model.Peticion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Optional;

public class EliminarPeticionPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTextField idPeticionField;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JLabel obraSocialLabel;

    public EliminarPeticionPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Eliminar Peticion", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel buscarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buscarPanel.add(new JLabel("Ingrese ID Petición:"));
        idPeticionField = new JTextField(20);
        buscarPanel.add(idPeticionField);
        btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(144, 202, 249));
        buscarPanel.add(btnBuscar);

        add(buscarPanel, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        infoPanel.add(new JLabel("Obra Social:"));
        obraSocialLabel = new JLabel();
        infoPanel.add(obraSocialLabel);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(new Color(255, 102, 102));
        infoPanel.add(btnEliminar);

        add(infoPanel, BorderLayout.SOUTH);

        // Deshabilitar botón Eliminar hasta que se busque un paciente
        btnEliminar.setEnabled(false);

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPeticion();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarPeticion();
            }
        });
    }

    private void buscarPeticion() {

        try{
            Optional<Peticion> peticionOptional = PeticionController.getInstance().buscarPeticionPorId(idPeticionField.getText());

            if(peticionOptional.isPresent()) {

                Peticion peticion = peticionOptional.get();
                obraSocialLabel.setText(peticion.getObraSocial());
                btnEliminar.setEnabled(true);
            }
            else {
                JOptionPane.showMessageDialog(this, "Petición no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Número de petición inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarPeticion() {
        try {
            String idPeticion = idPeticionField.getText();
            PeticionController.getInstance().eliminarPeticion(idPeticion);
            JOptionPane.showMessageDialog(this, "Petición eliminada con éxito.");
            btnEliminar.setEnabled(false);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Número de petición inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
