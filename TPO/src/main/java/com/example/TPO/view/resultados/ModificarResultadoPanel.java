package com.example.TPO.view.resultados;

import com.example.TPO.model.Resultado;
import com.example.TPO.controller.ResultadoController;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class ModificarResultadoPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private JTextField valorField;
    private JTextField idBuscarField;
    private JButton btnBuscar;
    private JButton btnGuardar;

    private final ResultadoController resultadoController = ResultadoController.getInstance();

    public ModificarResultadoPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Modificar Resultado", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel buscarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buscarPanel.add(new JLabel("ID:"));
        idBuscarField = new JTextField(20);
        buscarPanel.add(idBuscarField);
        btnBuscar = new JButton("Buscar");
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

        // Deshabilitar el panel de formulario hasta que se busque un paciente
        habilitarFormulario(false);

        // Agregar ActionListeners
        btnBuscar.addActionListener(e -> buscarResultado());
        btnGuardar.addActionListener(e -> guardarResultado());
    }

    private void habilitarFormulario(boolean habilitar) {
        valorField.setEnabled(habilitar);
        btnGuardar.setEnabled(habilitar);
    }

    private void buscarResultado(){
        String id = idBuscarField.getText();

        // Implementación simulada para buscar el paciente y cargar datos estáticos

        Optional<Resultado> resultadoBusqueda = resultadoController.buscarResultadoPorId(id);


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

        // Validaciones básicas
        if (valor.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Resultado resultadoActualizado = new Resultado();

        resultadoActualizado.setId(id);
        resultadoActualizado.setResultado(valor);


        if (!resultadoController.modificarResultado(id, resultadoActualizado)) {
            JOptionPane.showMessageDialog(this, "Error actulizando.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Resultado modificado con éxito.");
    }
}
