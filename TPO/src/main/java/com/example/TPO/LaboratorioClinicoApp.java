package com.example.TPO;

import com.example.TPO.view.menuLogin;
import com.example.TPO.view.pacientes.CrearPacientePanel;
import com.example.TPO.view.pacientes.EliminarPacientePanel;
import com.example.TPO.view.pacientes.ModificarPacientePanel;
import com.example.TPO.view.peticiones.CrearPeticionPanel;
import com.example.TPO.view.peticiones.EliminarPeticionPanel;
import com.example.TPO.view.peticiones.ListarPeticionPanel;
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

public class LaboratorioClinicoApp extends JFrame {

    public LaboratorioClinicoApp() {
        setTitle("Administración del Laboratorio Clínico");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        ImageIcon icon = new ImageIcon("src/resources/imagenAdministrador.jpg");
        JLabel imageLabel = new JLabel(icon);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        topPanel.add(imageLabel, BorderLayout.CENTER);

        JLabel rolLabel = new JLabel("Rol: Administrador");
        rolLabel.setFont(new Font("Arial", Font.BOLD, 16));
        rolLabel.setHorizontalAlignment(JLabel.CENTER);
        topPanel.add(rolLabel, BorderLayout.SOUTH);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1, 10, 10));

        JButton btnAdministrarPacientes = new JButton("Administrar Pacientes");
        JButton btnAdministrarSucursales = new JButton("Administrar Sucursales");
        JButton btnAdministrarPracticas = new JButton("Administrar Prácticas");
        JButton btnAdministrarPeticiones = new JButton("Administrar Peticiones");
        JButton btnAdministrarResultados = new JButton("Administrar Resultados");
        JButton btnAdministrarUsuarios = new JButton("Administrar Usuarios");
        JButton btnListarPeticionConResulados = new JButton("Listar resultados de Peticion");
        JButton btnCerrarSesion = new JButton("Cerrar Sesión");

        // Cambiar el color de los botones a celeste
        Color lightBlue = new Color(173, 216, 230);
        btnAdministrarPacientes.setBackground(lightBlue);
        btnAdministrarSucursales.setBackground(lightBlue);
        btnAdministrarPracticas.setBackground(lightBlue);
        btnAdministrarPeticiones.setBackground(lightBlue);
        btnAdministrarResultados.setBackground(lightBlue);
        btnAdministrarUsuarios.setBackground(lightBlue);
        btnListarPeticionConResulados.setBackground(lightBlue);
        btnCerrarSesion.setBackground(Color.RED);
        btnCerrarSesion.setForeground(Color.WHITE);

        panel.add(btnAdministrarPacientes);
        panel.add(btnAdministrarSucursales);
        panel.add(btnAdministrarPracticas);
        panel.add(btnAdministrarPeticiones);
        panel.add(btnAdministrarResultados);
        panel.add(btnAdministrarUsuarios);
        panel.add(btnListarPeticionConResulados);
        panel.add(btnCerrarSesion);

        mainPanel.add(panel, BorderLayout.CENTER);
        add(mainPanel);

        btnAdministrarPacientes.addActionListener(e -> {
            JPanel administrarPacientesPanel = new JPanel();
            administrarPacientesPanel.setLayout(new BorderLayout());

            JTabbedPane tabbedPane = new JTabbedPane();
            tabbedPane.addTab("Crear", new CrearPacientePanel());
            tabbedPane.addTab("Modificar", new ModificarPacientePanel());
            tabbedPane.addTab("Eliminar", new EliminarPacientePanel());

            JButton btnVolver = new JButton("Volver");
            btnVolver.addActionListener(e1 -> mostrarPanel(mainPanel));

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

            JButton btnVolver = new JButton("Volver");
            btnVolver.addActionListener(e14 -> mostrarPanel(mainPanel));

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

            JButton btnVolver = new JButton("Volver");
            btnVolver.addActionListener(e15 -> mostrarPanel(mainPanel));

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
            tabbedPane.addTab("Listar", new ListarPeticionPanel());
            tabbedPane.addTab("Eliminar", new EliminarPeticionPanel());

            JButton btnVolver = new JButton("Volver");
            btnVolver.addActionListener(e1 -> mostrarPanel(mainPanel));

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

            JButton btnVolver = new JButton("Volver");
            btnVolver.addActionListener(e12 -> mostrarPanel(mainPanel));

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

            JButton btnVolver = new JButton("Volver");
            btnVolver.addActionListener(e13 -> mostrarPanel(mainPanel));

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

            JButton btnVolver = new JButton("Volver");
            btnVolver.addActionListener(e12 -> mostrarPanel(mainPanel));

            JPanel panelWithBackButton = new JPanel(new BorderLayout());
            panelWithBackButton.add(tabbedPane, BorderLayout.CENTER);
            panelWithBackButton.add(btnVolver, BorderLayout.SOUTH);

            listarPeticionConResultadosPanel.add(panelWithBackButton, BorderLayout.CENTER);

            mostrarPanel(listarPeticionConResultadosPanel);
        });

        btnCerrarSesion.addActionListener(e -> {
            dispose();
            menuLogin menu = new menuLogin("Sistema de Gestión de Laboratorio");
            menu.setVisible(true);
        });
    }

    private void mostrarPanel(JPanel panel) {
        setContentPane(panel);
        revalidate();
        repaint();
    }

}
