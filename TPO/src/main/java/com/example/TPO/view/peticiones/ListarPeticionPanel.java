package com.example.TPO.view.peticiones;


import com.example.TPO.DTO.PeticionDTO;
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

        String[] columnNames = {"ID", "Obra Social","Fecha de Carga","Fecha de entrega","Valor CRITICO"};

        tableModel = new DefaultTableModel(columnNames, 0);

        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);
        setPeticiones(peticionController.buscarPeticionesConValoresCriticos());
    }

    public void addPeticion(String id, String obraSocial, String fechaCarga, String fechaEntrega, String valorCritico) {
        tableModel.addRow(new Object[]{id, obraSocial, fechaCarga, fechaEntrega, valorCritico});
    }

    public void setPeticiones(List<PeticionDTO> peticiones) {
        tableModel.setRowCount(0);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        if(peticiones.isEmpty()){
            addPeticion("--", "--", "--", "--", "--");
        }
        for (PeticionDTO peticion : peticiones) {
            addPeticion(peticion.getId(), peticion.getObraSocial(),dateFormat.format(peticion.getFechaCarga()),dateFormat.format(peticion.getFechaCalculadaEntrega()),"SI");
        }
    }
}