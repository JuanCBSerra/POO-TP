package com.example.TPO.view.usuarios;

import com.example.TPO.DTO.UsuarioDTO;
import com.example.TPO.controller.UsuarioController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class EliminarUsuarioPanel extends JPanel {

    private final JTextField dniBuscarField;
    private final JButton btnEliminar;
    private final JLabel nombreLabel;
    private final JLabel rolLabel;

    public EliminarUsuarioPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Eliminar Usuario", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel buscarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buscarPanel.add(new JLabel("DNI de usuario:"));
        dniBuscarField = new JTextField(20);
        buscarPanel.add(dniBuscarField);
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(144, 202, 249));
        buscarPanel.add(btnBuscar);

        add(buscarPanel, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        infoPanel.add(new JLabel("Nombre:"));
        nombreLabel = new JLabel();
        infoPanel.add(nombreLabel);

        infoPanel.add(new JLabel("Rol:"));
        rolLabel = new JLabel();
        infoPanel.add(rolLabel);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(new Color(255, 102, 102));
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
        String dni = dniBuscarField.getText();

        Optional<UsuarioDTO> usuario = UsuarioController.getInstance().getUsuario(dni);

        if (usuario.isPresent()) {
            nombreLabel.setText(usuario.get().getNombre());
            rolLabel.setText(String.valueOf(usuario.get().getRol()).toUpperCase());
            btnEliminar.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "Usuario no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            nombreLabel.setText("");
            btnEliminar.setEnabled(false);
        }
    }

    private void eliminarUsuario() {
        String dni = dniBuscarField.getText();

        boolean usuarioEliminado = UsuarioController.getInstance().eliminarUsuario(dni);

        if (usuarioEliminado) {
            JOptionPane.showMessageDialog(this, "Usuario eliminado con éxito.");
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo eliminar al usuario", "Error", JOptionPane.ERROR_MESSAGE);
        }

        limpiarCampos();
        btnEliminar.setEnabled(false);
    }

    private void limpiarCampos(){
        nombreLabel.setText("");
        dniBuscarField.setText("");
        rolLabel.setText("");
    }
}
