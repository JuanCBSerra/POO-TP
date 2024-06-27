package com.example.TPO.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EliminarUsuarioPanel extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JTextField dniBuscarField;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JLabel nombreLabel;
    private JLabel apellidoLabel;

    public EliminarUsuarioPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Eliminar Usuario", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel buscarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buscarPanel.add(new JLabel("Nombre de usuario:"));
        dniBuscarField = new JTextField(20);
        buscarPanel.add(dniBuscarField);
        btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(144, 202, 249)); // Color celeste
        buscarPanel.add(btnBuscar);

        add(buscarPanel, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Espacios alrededor del panel

        infoPanel.add(new JLabel("Nombre:"));
        nombreLabel = new JLabel();
        infoPanel.add(nombreLabel);

        infoPanel.add(new JLabel("Apellido:"));
        apellidoLabel = new JLabel();
        infoPanel.add(apellidoLabel);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(new Color(255, 102, 102)); // Rojo claro
        infoPanel.add(btnEliminar);

        add(infoPanel, BorderLayout.SOUTH);

        btnEliminar.setEnabled(false);

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarUsuario();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarUsuario();
            }
        });
    }

    private void buscarUsuario() {
        String nombreUsuario = dniBuscarField.getText();

        // Aquí deberías buscar el usuario por su nombre de usuario y cargar los datos en los labels
        // Por ejemplo: Usuario usuario = usuarioService.buscarPorNombreUsuario(nombreUsuario);
        // if (usuario != null) {
        //     nombreLabel.setText(usuario.getNombre());
        //     apellidoLabel.setText(usuario.getApellido());
        //     btnEliminar.setEnabled(true);
        // } else {
        //     JOptionPane.showMessageDialog(this, "Usuario no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        //     btnEliminar.setEnabled(false);
        // }

        // Simulación para el ejemplo:
        if (nombreUsuario.equals("juanito123")) {
            nombreLabel.setText("Juan");
            apellidoLabel.setText("Pérez");
            btnEliminar.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "Usuario no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            btnEliminar.setEnabled(false);
        }
    }

    private void eliminarUsuario() {
        // Aquí puedes implementar la lógica para eliminar el usuario según el nombre de usuario ingresado
        // Por ejemplo: usuarioService.eliminarUsuario(nombreUsuario);

        JOptionPane.showMessageDialog(this, "Usuario eliminado con éxito.");
        nombreLabel.setText("");
        apellidoLabel.setText("");
        dniBuscarField.setText("");
        btnEliminar.setEnabled(false);
    }
}
