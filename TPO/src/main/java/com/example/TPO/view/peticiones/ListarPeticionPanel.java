package com.example.TPO.view.peticiones;


import com.example.TPO.controller.PeticionController;
import com.example.TPO.model.Peticion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class ListarPeticionPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;

    private static final PeticionController peticionController = PeticionController.getInstance();

    public ListarPeticionPanel() {
        setLayout(new BorderLayout());



        // Column names for the table
        String[] columnNames = {"ID", "Obra Social","Fecha de Carga","Fecha de entrega","Valor CRITICO"};

        // Initialize the table model
        tableModel = new DefaultTableModel(columnNames, 0);

        // Create the table with the table model
        table = new JTable(tableModel);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the scroll pane to this panel
        add(scrollPane, BorderLayout.CENTER);
        setPeticiones(peticionController.buscarPeticionesConValoresCriticos());
    }

    // Method to add a request to the table
    public void addPeticion(String id, String obraSocial, String fechaCarga, String fechaEntrega, String valorCritico) {
        tableModel.addRow(new Object[]{id, obraSocial, fechaCarga, fechaEntrega, valorCritico});
    }

    // Method to set the list of requests
    public void setPeticiones(List<Peticion> peticiones) {
        tableModel.setRowCount(0); // Clear existing data
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        if(peticiones.isEmpty()){
            addPeticion("--", "--", "--", "--", "--");
        }
        for (Peticion peticion : peticiones) {
            addPeticion(peticion.getId(), peticion.getObraSocial(),dateFormat.format(peticion.getFechaCarga()),dateFormat.format(peticion.getFechaCalculadaEntrega()),"SI");
        }
    }


}