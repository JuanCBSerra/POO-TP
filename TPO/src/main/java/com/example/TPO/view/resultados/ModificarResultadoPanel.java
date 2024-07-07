package com.example.TPO.view.resultados;

import java.time.LocalDate;
import com.example.TPO.DTO.ResultadoDTO;
import com.example.TPO.controller.PeticionController;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class ModificarResultadoPanel extends JPanel {

    private final JTextField valorField;
    private final JTextField idBuscarField;
    private final JButton btnGuardar;

    private final PeticionController peticionController = PeticionController.getInstance();

    public ModificarResultadoPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Modificar Resultado", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel buscarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buscarPanel.add(new JLabel("ID:"));
        idBuscarField = new JTextField(20);
        buscarPanel.add(idBuscarField);
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(144, 202, 249)); // Color celeste
        buscarPanel.add(btnBuscar);

        add(buscarPanel, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Espacios alrededor del panel


        formPanel.add(new JLabel("Valor:"));
        valorField = new JTextField();
        formPanel.add(valorField);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(144, 238, 144)); // Color verde claro
        formPanel.add(btnGuardar);

        add(formPanel, BorderLayout.SOUTH);

        habilitarFormulario(false);

        btnBuscar.addActionListener(e -> buscarResultado());
        btnGuardar.addActionListener(e -> guardarResultado());
    }

    private void habilitarFormulario(boolean habilitar) {
        valorField.setEnabled(habilitar);
        btnGuardar.setEnabled(habilitar);
    }

    private void buscarResultado(){
        String id = idBuscarField.getText();
        Optional<ResultadoDTO> resultadoBusqueda = peticionController.getResultado(id);

        if (resultadoBusqueda.isPresent()) {
            valorField.setText(resultadoBusqueda.get().getResultado());
            habilitarFormulario(true);
        } else {
            JOptionPane.showMessageDialog(this, "Resultado no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            habilitarFormulario(false);
        }
    }

    private void guardarResultado() {
        String valor = valorField.getText();
        String id = idBuscarField.getText();

        if (valor.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        LocalDate fechaActual = LocalDate.now();

        if (!peticionController.modificarResultado(id, valor,fechaActual)) {
            JOptionPane.showMessageDialog(this, "Error actulizando.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Resultado modificado con éxito.");
    }
}
