package com.example.TPO.view.peticiones;

import com.example.TPO.DTO.PeticionDTO;
import com.example.TPO.DTO.ResultadoDTO;
import com.example.TPO.controller.PeticionController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Optional;

public class ListarResultadosDePeticionPanel extends JPanel {

    private final JTextField idPeticionField;
    private final DefaultTableModel tableModel;

    private static final PeticionController peticionController = PeticionController.getInstance();

    public ListarResultadosDePeticionPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Listar Resultados", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel buscarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buscarPanel.add(new JLabel("Ingrese ID de la petición:"));
        idPeticionField = new JTextField(20);
        buscarPanel.add(idPeticionField);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(144, 202, 249));
        buscarPanel.add(btnBuscar);

        add(buscarPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Resultado", "Fecha"}, 0);
        JTable resultadosTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(resultadosTable);

        add(scrollPane, BorderLayout.CENTER);

        btnBuscar.addActionListener(e -> buscarPeticion());
    }

    private void buscarPeticion() {
        Optional<PeticionDTO> peticionOptional = peticionController.getPeticion(idPeticionField.getText());

        if (peticionOptional.isPresent()) {
            PeticionDTO peticion = peticionOptional.get();
            mostrarResultados(peticion.getResultados());
        } else {
            JOptionPane.showMessageDialog(this, "Petición no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarResultados(List<ResultadoDTO> resultados) {
        tableModel.setRowCount(0);
        for (ResultadoDTO resultado : resultados) {
            tableModel.addRow(new Object[]{resultado.getId(), resultado.getResultado(), resultado.getFecha()});
        }
    }
}
