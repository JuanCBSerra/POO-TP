package com.example.TPO.view.peticiones;


import com.example.TPO.controller.PeticionController;
import com.example.TPO.model.Peticion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListarPeticionPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;

    private static final PeticionController peticionController = PeticionController.getInstance();

    public ListarPeticionPanel() {
        setLayout(new BorderLayout());



        // Column names for the table
        String[] columnNames = {"ID",  "Obra Social"};

        // Initialize the table model
        tableModel = new DefaultTableModel(columnNames, 0);

        // Create the table with the table model
        table = new JTable(tableModel);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the scroll pane to this panel
        add(scrollPane, BorderLayout.CENTER);
        //setPeticiones(peticionController.getPeticiones());
    }

    // Method to add a request to the table
    public void addPeticion(String id, String obraSocial) {
        tableModel.addRow(new Object[]{id, obraSocial});
    }

    // Method to set the list of requests
    public void setPeticiones(List<Peticion> peticiones) {
        tableModel.setRowCount(0); // Clear existing data
        if(peticiones.isEmpty()){
            addPeticion("Sin registro", "--");
        }
        for (Peticion peticion : peticiones) {
            addPeticion(peticion.getId(), peticion.getObraSocial());
        }
    }


}