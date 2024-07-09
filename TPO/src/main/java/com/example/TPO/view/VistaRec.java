package com.example.TPO.view;

import com.example.TPO.view.pacientes.CrearPacientePanel;
import com.example.TPO.view.peticiones.CrearPeticionPanel;
import com.example.TPO.view.peticiones.ListarPeticionPanel;
import com.example.TPO.view.peticiones.ListarResultadosDePeticionPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VistaRec extends JFrame {

    public VistaRec() {
        setTitle("Recepción del Laboratorio Clínico");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(5, 10, 5, 10));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(new EmptyBorder(5, 10, 5, 10));

        ImageIcon icon = new ImageIcon("src/resources/imagenRecepcion.jpg");
        JLabel imageLabel = new JLabel(icon);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        topPanel.add(imageLabel, BorderLayout.CENTER);

        JLabel rolLabel = new JLabel("Rol: Recepcionista");
        rolLabel.setFont(new Font("Arial", Font.BOLD, 16));
        rolLabel.setHorizontalAlignment(JLabel.CENTER);
        topPanel.add(rolLabel, BorderLayout.SOUTH);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(4, 1, 5, 5));

        JButton btnRegistrarPacientes = createButton("Registrar Pacientes");
        JButton btnRegistrarPeticiones = createButton("Registrar Peticiones");
        JButton btnListarPeticiones = createButton("Listar Peticiones");
        JButton btnConsultarResultados = createButton("Consultar Resultados");
        JButton btnCerrarSesion = createLogoutButton("Cerrar Sesión");

        centerPanel.add(btnRegistrarPacientes);
        centerPanel.add(btnRegistrarPeticiones);
        centerPanel.add(btnListarPeticiones);
        centerPanel.add(btnConsultarResultados);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(btnCerrarSesion, BorderLayout.SOUTH);

        add(mainPanel);

        btnRegistrarPacientes.addActionListener(e -> {
            JPanel registrarPacientesPanel = new JPanel();
            registrarPacientesPanel.setLayout(new BorderLayout());

            JTabbedPane tabbedPane = new JTabbedPane();
            tabbedPane.addTab("Crear", new CrearPacientePanel());

            JButton btnVolver = createButton("Volver");
            btnVolver.addActionListener(e1 -> mostrarPanel(mainPanel));

            JPanel panelWithBackButton = new JPanel(new BorderLayout());
            panelWithBackButton.add(tabbedPane, BorderLayout.CENTER);
            panelWithBackButton.add(btnVolver, BorderLayout.SOUTH);

            registrarPacientesPanel.add(panelWithBackButton, BorderLayout.CENTER);

            mostrarPanel(registrarPacientesPanel);
        });

        btnRegistrarPeticiones.addActionListener(e -> {
            JPanel registrarPeticionesPanel = new JPanel();
            registrarPeticionesPanel.setLayout(new BorderLayout());

            JTabbedPane tabbedPane = new JTabbedPane();
            tabbedPane.addTab("Crear", new CrearPeticionPanel());

            JButton btnVolver = createButton("Volver");
            btnVolver.addActionListener(e1 -> mostrarPanel(mainPanel));

            JPanel panelWithBackButton = new JPanel(new BorderLayout());
            panelWithBackButton.add(tabbedPane, BorderLayout.CENTER);
            panelWithBackButton.add(btnVolver, BorderLayout.SOUTH);

            registrarPeticionesPanel.add(panelWithBackButton, BorderLayout.CENTER);

            mostrarPanel(registrarPeticionesPanel);
        });

        btnListarPeticiones.addActionListener(e -> {
            JPanel listarPeticionesPanel = new JPanel();
            listarPeticionesPanel.setLayout(new BorderLayout());

            JTabbedPane tabbedPane = new JTabbedPane();
            tabbedPane.addTab("Listar", new ListarPeticionPanel());

            JButton btnVolver = createButton("Volver");
            btnVolver.addActionListener(e2 -> mostrarPanel(mainPanel));

            JPanel panelWithBackButton = new JPanel(new BorderLayout());
            panelWithBackButton.add(tabbedPane, BorderLayout.CENTER);
            panelWithBackButton.add(btnVolver, BorderLayout.SOUTH);

            listarPeticionesPanel.add(panelWithBackButton, BorderLayout.CENTER);

            mostrarPanel(listarPeticionesPanel);
        });

        btnConsultarResultados.addActionListener(e -> {
            JPanel consultarResultadosPanel = new JPanel();
            consultarResultadosPanel.setLayout(new BorderLayout());

            JTabbedPane tabbedPane = new JTabbedPane();
            tabbedPane.addTab("Listar Resultados", new ListarResultadosDePeticionPanel());

            JButton btnVolver = createButton("Volver");
            btnVolver.addActionListener(e3 -> mostrarPanel(mainPanel));

            JPanel panelWithBackButton = new JPanel(new BorderLayout());
            panelWithBackButton.add(tabbedPane, BorderLayout.CENTER);
            panelWithBackButton.add(btnVolver, BorderLayout.SOUTH);

            consultarResultadosPanel.add(panelWithBackButton, BorderLayout.CENTER);

            mostrarPanel(consultarResultadosPanel);
        });

        btnCerrarSesion.addActionListener(e -> {
            dispose();

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
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(144, 238, 144));
        button.setForeground(Color.BLACK); // Texto negro
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(60, 179, 113), 2),
                BorderFactory.createEmptyBorder(10, 20, 5, 10)));
        return button;
    }

    private JButton createLogoutButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(255, 99, 71));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(178, 34, 34), 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));
        button.addActionListener(e -> {
            dispose();

            Window[] windows = Window.getWindows();
            for (Window window : windows) {
                if (window instanceof menuLogin) {
                    window.setVisible(true);
                    return;
                }
            }

            new menuLogin("Sistema de Gestión de Laboratorio").setVisible(true);
        });
        return button;
    }

    public void mostrarPanel(JPanel nuevoPanel) {
        setContentPane(nuevoPanel);
        revalidate();
        repaint();
    }

}
