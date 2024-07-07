package com.example.TPO.view.resultados;

import com.example.TPO.DTO.ResultadoDTO;
import com.example.TPO.controller.ResultadoController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class EliminarResultadoPanel extends JPanel {

    private final JTextField idBuscarField;
    private final JButton btnEliminar;
    private final JLabel idLabel;
    private final JLabel valorLabel;

    private final ResultadoController resultadoController = ResultadoController.getInstance();

    public EliminarResultadoPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Eliminar Resultado", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel buscarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        buscarPanel.add(new JLabel("ID:"));
        idBuscarField = new JTextField(20);
        buscarPanel.add(idBuscarField);
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(144, 202, 249));
        buscarPanel.add(btnBuscar);

        add(buscarPanel, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Añadir espacio alrededor del panel

        infoPanel.add(new JLabel("ID:"));
        idLabel = new JLabel();
        infoPanel.add(idLabel);

        infoPanel.add(new JLabel("Valor:"));
        valorLabel = new JLabel();
        infoPanel.add(valorLabel);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(new Color(255, 102, 102)); // Rojo claro
        infoPanel.add(btnEliminar);

        add(infoPanel, BorderLayout.SOUTH);

        btnEliminar.setEnabled(false);

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarResultado();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarResultado();
            }
        });
    }

    private void buscarResultado() {
        String id = idBuscarField.getText();

        Optional<ResultadoDTO> resultadoBuscado = resultadoController.getResultado(id);

        if(resultadoBuscado.isPresent()){
            idLabel.setText(id);
            valorLabel.setText(resultadoBuscado.get().getResultado());
            btnEliminar.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "Resultado no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void eliminarResultado() {
        String id = idBuscarField.getText();
        resultadoController.eliminarResultado(id);

        JOptionPane.showMessageDialog(this, "Resultado eliminado con éxito.");
        idLabel.setText("");
        valorLabel.setText("");
        btnEliminar.setEnabled(false);
    }
}
