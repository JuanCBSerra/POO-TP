package com.example.TPO.view.peticiones;

import com.example.TPO.DTO.PeticionDTO;
import com.example.TPO.controller.PeticionController;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class EliminarPeticionPanel extends JPanel {
    private final JTextField idPeticionField;
    private final JButton btnEliminar;
    private final JLabel obraSocialLabel;
    private static final PeticionController peticionController = PeticionController.getInstance();

    public EliminarPeticionPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Eliminar Peticion", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel buscarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buscarPanel.add(new JLabel("Ingrese ID Petición:"));
        idPeticionField = new JTextField(20);
        buscarPanel.add(idPeticionField);
        JButton btnBuscar = new JButton("Buscar");
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

        btnEliminar.setEnabled(false);

        btnBuscar.addActionListener(e -> buscarPeticion());

        btnEliminar.addActionListener(e -> eliminarPeticion());
    }

    private void buscarPeticion() {
        Optional<PeticionDTO> peticionOptional = peticionController.getPeticion(idPeticionField.getText());
        if(peticionOptional.isPresent()) {
            PeticionDTO peticion = peticionOptional.get();
            obraSocialLabel.setText(peticion.getObraSocial());
            btnEliminar.setEnabled(true);
        }
        else {
            JOptionPane.showMessageDialog(this, "Petición no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarPeticion() {
        String idPeticion = idPeticionField.getText();
        peticionController.eliminarPeticion(idPeticion);
        JOptionPane.showMessageDialog(this, "Petición eliminada con éxito.");
        btnEliminar.setEnabled(false);
    }
}
