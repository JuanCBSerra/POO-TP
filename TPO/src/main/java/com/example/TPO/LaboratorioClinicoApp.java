package com.example.TPO;
import com.example.TPO.view.pacientes.CrearPacientePanel;
import com.example.TPO.view.pacientes.EliminarPacientePanel;
import com.example.TPO.view.pacientes.ModificarPacientePanel;
import com.example.TPO.view.peticiones.CrearPeticionPanel;
import com.example.TPO.view.peticiones.EliminarPeticionPanel;
import com.example.TPO.view.peticiones.ModificarPeticionPanel;
import com.example.TPO.view.practicas.CrearPracticasPanel;
import com.example.TPO.view.practicas.EliminarPracticasPanel;
import com.example.TPO.view.practicas.ModificarPracticasPanel;
import com.example.TPO.view.resultados.CrearResultadoPanel;
import com.example.TPO.view.resultados.EliminarResultadoPanel;
import com.example.TPO.view.peticiones.ListarResultadosDePeticionPanel;
import com.example.TPO.view.resultados.ModificarResultadoPanel;
import com.example.TPO.view.sucursales.CrearSucursalPanel;
import com.example.TPO.view.sucursales.EliminarSucursalPanel;
import com.example.TPO.view.sucursales.ModificarSucursalPanel;
import com.example.TPO.view.usuarios.CrearUsuarioPanel;
import com.example.TPO.view.usuarios.EliminarUsuarioPanel;
import com.example.TPO.view.usuarios.ModificarUsuarioPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaboratorioClinicoApp extends JFrame {

    public LaboratorioClinicoApp() {
        setTitle("Administración del Laboratorio Clínico");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1, 10, 10));

        JButton btnAdministrarPacientes = createButton("Administrar Pacientes");
        JButton btnAdministrarSucursales = createButton("Administrar Sucursales");
        JButton btnAdministrarPracticas = createButton("Administrar Prácticas");
        JButton btnAdministrarPeticiones = createButton("Administrar Peticiones");
        JButton btnAdministrarResultados = createButton("Administrar Resultados");
        JButton btnAdministrarUsuarios = createButton("Administrar Usuarios");
        JButton btnListarPeticionConResulados = createButton("Listar resultados de Peticion");

        panel.add(btnAdministrarPacientes);
        panel.add(btnAdministrarSucursales);
        panel.add(btnAdministrarPracticas);
        panel.add(btnAdministrarPeticiones);
        panel.add(btnAdministrarResultados);
        panel.add(btnAdministrarUsuarios);
        panel.add(btnListarPeticionConResulados);

        add(panel);

        btnAdministrarPacientes.addActionListener(e -> {
            JPanel administrarPacientesPanel = new JPanel();
            administrarPacientesPanel.setLayout(new BorderLayout());

            JTabbedPane tabbedPane = new JTabbedPane();
            tabbedPane.addTab("Crear", new CrearPacientePanel());
            tabbedPane.addTab("Modificar", new ModificarPacientePanel());
            tabbedPane.addTab("Eliminar", new EliminarPacientePanel());

            JButton btnVolver = createButton("Volver");
            btnVolver.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarPanel(panel);
                }
            });

            JPanel panelWithBackButton = new JPanel(new BorderLayout());
            panelWithBackButton.add(tabbedPane, BorderLayout.CENTER);
            panelWithBackButton.add(btnVolver, BorderLayout.SOUTH);

            administrarPacientesPanel.add(panelWithBackButton, BorderLayout.CENTER);

            mostrarPanel(administrarPacientesPanel);
        });

        btnAdministrarSucursales.addActionListener(e -> {
            JPanel administrarSucursalesPanel = new JPanel();
            administrarSucursalesPanel.setLayout(new BorderLayout());

            JTabbedPane tabbedPane = new JTabbedPane();
            tabbedPane.addTab("Crear", new CrearSucursalPanel());
            tabbedPane.addTab("Modificar", new ModificarSucursalPanel());
            tabbedPane.addTab("Eliminar", new EliminarSucursalPanel());

            JButton btnVolver = createButton("Volver");
            btnVolver.addActionListener(e14 -> {
                mostrarPanel(panel);
            });

            JPanel panelWithBackButton = new JPanel(new BorderLayout());
            panelWithBackButton.add(tabbedPane, BorderLayout.CENTER);
            panelWithBackButton.add(btnVolver, BorderLayout.SOUTH);

            administrarSucursalesPanel.add(panelWithBackButton, BorderLayout.CENTER);

            mostrarPanel(administrarSucursalesPanel);
        });

        btnAdministrarPracticas.addActionListener(e -> {
            JPanel administrarPracticasPanel = new JPanel();
            administrarPracticasPanel.setLayout(new BorderLayout());

            JTabbedPane tabbedPane = new JTabbedPane();
            tabbedPane.addTab("Crear", new CrearPracticasPanel());
            tabbedPane.addTab("Modificar", new ModificarPracticasPanel());
            tabbedPane.addTab("Eliminar", new EliminarPracticasPanel());

            // Botón de Volver
            JButton btnVolver = createButton("Volver");
            btnVolver.addActionListener(e15 -> {
                mostrarPanel(panel);
            });

            JPanel panelWithBackButton = new JPanel(new BorderLayout());
            panelWithBackButton.add(tabbedPane, BorderLayout.CENTER);
            panelWithBackButton.add(btnVolver, BorderLayout.SOUTH);

            administrarPracticasPanel.add(panelWithBackButton, BorderLayout.CENTER);

            mostrarPanel(administrarPracticasPanel);
        });

        btnAdministrarPeticiones.addActionListener(e -> {
            JPanel administrarPeticionesPanel = new JPanel();
            administrarPeticionesPanel.setLayout(new BorderLayout());

            JTabbedPane tabbedPane = new JTabbedPane();
            tabbedPane.addTab("Crear", new CrearPeticionPanel());
            tabbedPane.addTab("Modificar", new ModificarPeticionPanel());
            tabbedPane.addTab("Eliminar", new EliminarPeticionPanel());

            JButton btnVolver = createButton("Volver");
            btnVolver.addActionListener(e1 -> {
                mostrarPanel(panel);
            });

            JPanel panelWithBackButton = new JPanel(new BorderLayout());
            panelWithBackButton.add(tabbedPane, BorderLayout.CENTER);
            panelWithBackButton.add(btnVolver, BorderLayout.SOUTH);

            administrarPeticionesPanel.add(panelWithBackButton, BorderLayout.CENTER);

            mostrarPanel(administrarPeticionesPanel);
        });

        btnAdministrarResultados.addActionListener(e -> {
            JPanel administrarResultadosPanel = new JPanel();
            administrarResultadosPanel.setLayout(new BorderLayout());

            JTabbedPane tabbedPane = new JTabbedPane();
            tabbedPane.addTab("Crear", new CrearResultadoPanel());
            tabbedPane.addTab("Modificar", new ModificarResultadoPanel());
            tabbedPane.addTab("Eliminar", new EliminarResultadoPanel());

            JButton btnVolver = createButton("Volver");
            btnVolver.addActionListener(e12 -> {
                mostrarPanel(panel);
            });

            JPanel panelWithBackButton = new JPanel(new BorderLayout());
            panelWithBackButton.add(tabbedPane, BorderLayout.CENTER);
            panelWithBackButton.add(btnVolver, BorderLayout.SOUTH);

            administrarResultadosPanel.add(panelWithBackButton, BorderLayout.CENTER);

            mostrarPanel(administrarResultadosPanel);
        });

        btnAdministrarUsuarios.addActionListener(e -> {
            JPanel administrarUsuariosPanel = new JPanel();
            administrarUsuariosPanel.setLayout(new BorderLayout());

            JTabbedPane tabbedPane = new JTabbedPane();
            tabbedPane.addTab("Crear", new CrearUsuarioPanel());
            tabbedPane.addTab("Modificar", new ModificarUsuarioPanel());
            tabbedPane.addTab("Eliminar", new EliminarUsuarioPanel());

            JButton btnVolver = createButton("Volver");
            btnVolver.addActionListener(e13 -> {
                mostrarPanel(panel); // Vuelve al panel principal
            });

            JPanel panelWithBackButton = new JPanel(new BorderLayout());
            panelWithBackButton.add(tabbedPane, BorderLayout.CENTER);
            panelWithBackButton.add(btnVolver, BorderLayout.SOUTH);

            administrarUsuariosPanel.add(panelWithBackButton, BorderLayout.CENTER);

            mostrarPanel(administrarUsuariosPanel);
        });

        btnListarPeticionConResulados.addActionListener(e -> {
            JPanel listarPeticionConResultadosPanel = new JPanel();
            listarPeticionConResultadosPanel.setLayout(new BorderLayout());

            JTabbedPane tabbedPane = new JTabbedPane();
            tabbedPane.addTab("Listar Resultados", new ListarResultadosDePeticionPanel());

            JButton btnVolver = createButton("Volver");
            btnVolver.addActionListener(e13 -> {
                mostrarPanel(panel);
            });

            JPanel panelWithBackButton = new JPanel(new BorderLayout());
            panelWithBackButton.add(tabbedPane, BorderLayout.CENTER);
            panelWithBackButton.add(btnVolver, BorderLayout.SOUTH);

            listarPeticionConResultadosPanel.add(panelWithBackButton, BorderLayout.CENTER);

            mostrarPanel(listarPeticionConResultadosPanel);
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