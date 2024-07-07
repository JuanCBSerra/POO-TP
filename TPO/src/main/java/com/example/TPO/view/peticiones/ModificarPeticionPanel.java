package com.example.TPO.view.peticiones;

import com.example.TPO.DTO.PeticionDTO;
import com.example.TPO.DTO.PracticaDTO;
import com.example.TPO.Utils;
import com.example.TPO.controller.PeticionController;
import com.example.TPO.controller.PracticaController;
import com.example.TPO.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class ModificarPeticionPanel extends JPanel {

    private final JTextField idPeticionField;
    private final JTextField obraSocialField;
    private final JTextField practicaAsociadaField;
    private final JTextField fechaEntregaField;
    private final JButton btnGuardar;

    private static final PeticionController peticionController = PeticionController.getInstance();

    public ModificarPeticionPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Modificar Petición", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel buscarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buscarPanel.add(new JLabel("Ingrese ID de la petición:"));
        idPeticionField = new JTextField(20);
        buscarPanel.add(idPeticionField);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(144, 202, 249)); // Color celeste
        buscarPanel.add(btnBuscar);

        add(buscarPanel, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 8, 8));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        formPanel.add(new JLabel("Obra Social:"));
        obraSocialField = new JTextField();
        formPanel.add(obraSocialField);

        formPanel.add(new JLabel("Práctica Asociada:"));
        practicaAsociadaField = new JTextField();
        formPanel.add(practicaAsociadaField);

        formPanel.add(new JLabel("Fecha Estimada de Entrega:"));
        fechaEntregaField = new JTextField();
        formPanel.add(fechaEntregaField);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(144, 238, 144));
        formPanel.add(btnGuardar);

        add(formPanel, BorderLayout.SOUTH);

        habilitarFormulario(false);

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPeticion();
            }
        });

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarPeticion();
            }
        });
    }

    private void habilitarFormulario(boolean habilitar) {
        obraSocialField.setEnabled(habilitar);
        practicaAsociadaField.setEnabled(habilitar);
        fechaEntregaField.setEnabled(habilitar);
        btnGuardar.setEnabled(habilitar);
    }

    private void buscarPeticion() {
        try {
            Optional<PeticionDTO> peticionOptional = peticionController.getPeticion(idPeticionField.getText());

            if (peticionOptional.isPresent()) {
                PeticionDTO peticion = peticionOptional.get();
                obraSocialField.setText(peticion.getObraSocial());

                List<PracticaDTO> practicas = peticion.getPracticas();
                StringBuilder practicasString = new StringBuilder();
                for (int i = 0; i < practicas.size(); i++) {
                    practicasString.append(practicas.get(i));
                    if (i < practicas.size() - 1) {
                        practicasString.append(", ");
                    }
                }
                practicaAsociadaField.setText(practicasString.toString());

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                String formattedDate = dateFormat.format(peticion.getFechaCalculadaEntrega());
                fechaEntregaField.setText(formattedDate);
                habilitarFormulario(true);

            } else {
                JOptionPane.showMessageDialog(this, "Petición no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Número de petición inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardarPeticion() {
        String id = idPeticionField.getText();
        String obraSocial = obraSocialField.getText();
        String practicaAsociada = practicaAsociadaField.getText();
        String fechaEntrega = fechaEntregaField.getText();

        if (id.isEmpty() || obraSocial.isEmpty() || practicaAsociada.isEmpty() || fechaEntrega.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos correctamente.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String[] practicasArray = practicaAsociada.split(",");

        PeticionController.getInstance().modificarPeticion(id, obraSocial, Utils.parseDate(fechaEntrega), practicasArray);

        JOptionPane.showMessageDialog(this, "Petición modificada con éxito.");
        limpiarCampos();
        habilitarFormulario(false);
    }

    private void limpiarCampos() {
        obraSocialField.setText("");
        practicaAsociadaField.setText("");
        fechaEntregaField.setText("");
    }
}
