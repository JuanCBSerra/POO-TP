package com.example.TPO.view;

import com.example.TPO.LaboratorioClinicoApp;
import com.example.TPO.controller.UsuarioController;
import com.example.TPO.model.Usuario;
import com.example.TPO.model.Rol;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Optional;


public class menuLogin extends JFrame {

    private JPanel pnlLogin;
    private JTextField textFieldPassword;
    private JTextField textFieldUsername;
    private JButton loginButton;
    private JButton exitButton;
    private JLabel Nombre;
    private JLabel Contraseña;

    private final UsuarioController usuarioController = UsuarioController.getInstance();

    public menuLogin(String titulo) {
        super(titulo);
        setContentPane(pnlLogin);
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        textFieldPassword.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.GRAY, 1, true),
                new EmptyBorder(5, 15, 5, 15)));
        textFieldPassword.setBackground(new Color(240, 240, 240));
        textFieldUsername.setBackground(new Color(240, 240, 240));

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = textFieldUsername.getText();
                String password = textFieldPassword.getText();
                Optional<Usuario> usuarioAutenticado = usuarioController.autenticarUsuario(userName, password);
                if (usuarioAutenticado.isPresent()) {
                    redirectUser(usuarioAutenticado.get().getRol());
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Correo, nombre o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void redirectUser(Rol rank) {
        switch (rank) {
            case ADMINISTRADOR:
                new LaboratorioClinicoApp().setVisible(true);
                break;
            case LABORATORISTA:
                JOptionPane.showMessageDialog(null, "Bienvenido Laboratorista", "Login", JOptionPane.INFORMATION_MESSAGE);
                break;
            case RECEPCION:
                JOptionPane.showMessageDialog(null, "Bienvenido Recepcionista", "Login", JOptionPane.INFORMATION_MESSAGE);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Rango desconocido", "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }


}
