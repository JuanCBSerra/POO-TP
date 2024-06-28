package com.example.TPO;
import com.example.TPO.view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaboratorioClinicoApp extends JFrame {

    private static final long serialVersionUID = 1L;

    public LaboratorioClinicoApp() {
        setTitle("Administración del Laboratorio Clínico");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 10, 10));

        JButton btnAdministrarPacientes = createButton("Administrar Pacientes");
        JButton btnAdministrarSucursales = createButton("Administrar Sucursales");
        JButton btnAdministrarPracticas = createButton("Administrar Prácticas");
        JButton btnAdministrarPeticiones = createButton("Administrar Peticiones");
        JButton btnAdministrarResultados = createButton("Administrar Resultados");
        JButton btnAdministrarUsuarios = createButton("Administrar Usuarios");

        panel.add(btnAdministrarPacientes);
        panel.add(btnAdministrarSucursales);
        panel.add(btnAdministrarPracticas);
        panel.add(btnAdministrarPeticiones);
        panel.add(btnAdministrarResultados);
        panel.add(btnAdministrarUsuarios);

        add(panel);

        btnAdministrarPacientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel administrarPacientesPanel = new JPanel();
                administrarPacientesPanel.setLayout(new BorderLayout());

                JTabbedPane tabbedPane = new JTabbedPane();
                tabbedPane.addTab("Crear", new CrearPacientePanel());
                tabbedPane.addTab("Modificar", new ModificarPacientePanel());
                tabbedPane.addTab("Eliminar", new EliminarPacientePanel());

                // Botón de Volver
                JButton btnVolver = createButton("Volver");
                btnVolver.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mostrarPanel(panel); // Vuelve al panel principal
                    }
                });

                JPanel panelWithBackButton = new JPanel(new BorderLayout());
                panelWithBackButton.add(tabbedPane, BorderLayout.CENTER);
                panelWithBackButton.add(btnVolver, BorderLayout.SOUTH);

                administrarPacientesPanel.add(panelWithBackButton, BorderLayout.CENTER);

                mostrarPanel(administrarPacientesPanel);
            }
        });

        btnAdministrarSucursales.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel administrarSucursalesPanel = new JPanel();
                administrarSucursalesPanel.setLayout(new BorderLayout());

                JTabbedPane tabbedPane = new JTabbedPane();
                tabbedPane.addTab("Crear", new CrearSucursalPanel());
                tabbedPane.addTab("Modificar", new ModificarSucursalPanel());
                tabbedPane.addTab("Eliminar", new EliminarSucursalPanel());

                // Botón de Volver
                JButton btnVolver = createButton("Volver");
                btnVolver.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mostrarPanel(panel); // Vuelve al panel principal
                    }
                });

                JPanel panelWithBackButton = new JPanel(new BorderLayout());
                panelWithBackButton.add(tabbedPane, BorderLayout.CENTER);
                panelWithBackButton.add(btnVolver, BorderLayout.SOUTH);

                administrarSucursalesPanel.add(panelWithBackButton, BorderLayout.CENTER);

                mostrarPanel(administrarSucursalesPanel);
            }
        });

        btnAdministrarPracticas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel administrarPracticasPanel = new JPanel();
                administrarPracticasPanel.setLayout(new BorderLayout());

                JTabbedPane tabbedPane = new JTabbedPane();
                tabbedPane.addTab("Crear", new CrearPracticasPanel());
                tabbedPane.addTab("Modificar", new ModificarPracticasPanel());
                tabbedPane.addTab("Eliminar", new EliminarPracticasPanel());

                // Botón de Volver
                JButton btnVolver = createButton("Volver");
                btnVolver.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mostrarPanel(panel); // Vuelve al panel principal
                    }
                });

                JPanel panelWithBackButton = new JPanel(new BorderLayout());
                panelWithBackButton.add(tabbedPane, BorderLayout.CENTER);
                panelWithBackButton.add(btnVolver, BorderLayout.SOUTH);

                administrarPracticasPanel.add(panelWithBackButton, BorderLayout.CENTER);

                mostrarPanel(administrarPracticasPanel);
            }
        });

        btnAdministrarPeticiones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel administrarPeticionesPanel = new JPanel();
                administrarPeticionesPanel.setLayout(new BorderLayout());

                JTabbedPane tabbedPane = new JTabbedPane();
                tabbedPane.addTab("Crear", new CrearPeticionPanel());
                tabbedPane.addTab("Modificar", new ModificarPeticionPanel());
                tabbedPane.addTab("Eliminar", new EliminarPeticionPanel());

                // Botón de Volver
                JButton btnVolver = createButton("Volver");
                btnVolver.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mostrarPanel(panel); // Vuelve al panel principal
                    }
                });

                JPanel panelWithBackButton = new JPanel(new BorderLayout());
                panelWithBackButton.add(tabbedPane, BorderLayout.CENTER);
                panelWithBackButton.add(btnVolver, BorderLayout.SOUTH);

                administrarPeticionesPanel.add(panelWithBackButton, BorderLayout.CENTER);

                mostrarPanel(administrarPeticionesPanel);
            }
        });

        btnAdministrarResultados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel administrarResultadosPanel = new JPanel();
                administrarResultadosPanel.setLayout(new BorderLayout());

                JTabbedPane tabbedPane = new JTabbedPane();
                tabbedPane.addTab("Crear", new CrearResultadoPanel());
                tabbedPane.addTab("Modificar", new ModificarResultadoPanel());
                tabbedPane.addTab("Eliminar", new EliminarResultadoPanel());

                // Botón de Volver
                JButton btnVolver = createButton("Volver");
                btnVolver.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mostrarPanel(panel); // Vuelve al panel principal
                    }
                });

                JPanel panelWithBackButton = new JPanel(new BorderLayout());
                panelWithBackButton.add(tabbedPane, BorderLayout.CENTER);
                panelWithBackButton.add(btnVolver, BorderLayout.SOUTH);

                administrarResultadosPanel.add(panelWithBackButton, BorderLayout.CENTER);

                mostrarPanel(administrarResultadosPanel);
            }
        });

        btnAdministrarUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel administrarUsuariosPanel = new JPanel();
                administrarUsuariosPanel.setLayout(new BorderLayout());

                JTabbedPane tabbedPane = new JTabbedPane();
                tabbedPane.addTab("Crear", new CrearUsuarioPanel());
                tabbedPane.addTab("Modificar", new ModificarUsuarioPanel());
                tabbedPane.addTab("Eliminar", new EliminarUsuarioPanel());

                // Botón de Volver
                JButton btnVolver = createButton("Volver");
                btnVolver.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mostrarPanel(panel); // Vuelve al panel principal
                    }
                });

                JPanel panelWithBackButton = new JPanel(new BorderLayout());
                panelWithBackButton.add(tabbedPane, BorderLayout.CENTER);
                panelWithBackButton.add(btnVolver, BorderLayout.SOUTH);

                administrarUsuariosPanel.add(panelWithBackButton, BorderLayout.CENTER);

                mostrarPanel(administrarUsuariosPanel);
            }
        });
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(173, 216, 230)); // Light blue background
        button.setFocusPainted(false);
        return button;
    }

    public void mostrarPanel(JPanel nuevoPanel) {
        setContentPane(nuevoPanel);
        revalidate();
        repaint();
    }

}