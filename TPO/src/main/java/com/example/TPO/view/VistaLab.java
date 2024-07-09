package com.example.TPO.view;

import com.example.TPO.view.menuLogin;
import com.example.TPO.view.resultados.CrearResultadoPanel;
import com.example.TPO.view.resultados.EliminarResultadoPanel;
import com.example.TPO.view.resultados.ModificarResultadoPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VistaLab extends JFrame {

    public VistaLab() {
        setTitle("Administración del Laboratorio Clínico");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        ImageIcon icon = new ImageIcon("src/resources/imagenLaboratorista.jpg");
        JLabel imageLabel = new JLabel(icon);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        topPanel.add(imageLabel, BorderLayout.CENTER);

        JLabel rolLabel = new JLabel("Rol: Laboratorista");
        rolLabel.setFont(new Font("Arial", Font.BOLD, 16));
        rolLabel.setHorizontalAlignment(JLabel.CENTER);
        topPanel.add(rolLabel, BorderLayout.SOUTH);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 20, 20)); // Cambiado a 2 filas

        JButton btnAdministrarResultados = createButton("Administrar Resultados");
        JButton btnCerrarSesion = createLogoutButton("Cerrar Sesión");

        panel.add(btnAdministrarResultados);
        panel.add(btnCerrarSesion);

        mainPanel.add(panel, BorderLayout.CENTER);

        add(mainPanel);

        btnAdministrarResultados.addActionListener(e -> {
            JPanel administrarResultadosPanel = new JPanel();
            administrarResultadosPanel.setLayout(new BorderLayout());

            JTabbedPane tabbedPane = new JTabbedPane();
            tabbedPane.addTab("Crear", new CrearResultadoPanel());
            tabbedPane.addTab("Modificar", new ModificarResultadoPanel());
            tabbedPane.addTab("Eliminar", new EliminarResultadoPanel());

            JButton btnVolver = createButton("Volver");
            btnVolver.addActionListener(e1 -> mostrarPanel(mainPanel));

            JPanel panelWithBackButton = new JPanel(new BorderLayout());
            panelWithBackButton.add(tabbedPane, BorderLayout.CENTER);
            panelWithBackButton.add(btnVolver, BorderLayout.SOUTH);

            administrarResultadosPanel.add(panelWithBackButton, BorderLayout.CENTER);

            mostrarPanel(administrarResultadosPanel);
        });

        btnCerrarSesion.addActionListener(e -> {
            dispose();
            showLoginMenu();
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                showLoginMenu();
            }
        });
    }

    private void showLoginMenu() {
        SwingUtilities.invokeLater(() -> {
            Window[] windows = Window.getWindows();
            for (Window window : windows) {
                if (window instanceof menuLogin) {
                    window.setVisible(true);
                    return;
                }
            }
            new menuLogin("Sistema de Gestión de Laboratorio").setVisible(true);
        });
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(8, 64, 89));
        button.setFocusPainted(false);
        button.setMargin(new Insets(8, 12, 8, 12));
        return button;
    }

    private JButton createLogoutButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(255, 99, 71));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setMargin(new Insets(8, 12, 8, 12));
        button.addActionListener(e -> {
            dispose();
            showLoginMenu();
        });
        return button;
    }

    public void mostrarPanel(JPanel nuevoPanel) {
        setContentPane(nuevoPanel);
        revalidate();
        repaint();
    }
}
