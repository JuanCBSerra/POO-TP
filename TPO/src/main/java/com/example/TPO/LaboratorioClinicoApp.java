package com.example.TPO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class LaboratorioClinicoApp extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LaboratorioClinicoApp() {
		// Configuración de la ventana principal
        setTitle("Administración del Laboratorio Clínico");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear un panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1, 10, 10));

        // Crear y agregar botones al panel
        JButton btnAdministrarPacientes = new JButton("Administrar Pacientes");
        JButton btnAdministrarSucursales = new JButton("Administrar Sucursales");
        JButton btnAdministrarPracticas = new JButton("Administrar Prácticas");
        JButton btnAdministrarPeticiones = new JButton("Administrar Peticiones");
        JButton btnAdministrarResultados = new JButton("Administrar Resultados");
        JButton btnAdministrarUsuarios = new JButton("Administrar Usuarios");
        JButton btnRolesSeguridad = new JButton("Roles y Seguridad");

        panel.add(btnAdministrarPacientes);
        panel.add(btnAdministrarSucursales);
        panel.add(btnAdministrarPracticas);
        panel.add(btnAdministrarPeticiones);
        panel.add(btnAdministrarResultados);
        panel.add(btnAdministrarUsuarios);
        panel.add(btnRolesSeguridad);

        // Añadir panel al frame
        add(panel);

        // Agregar ActionListeners a los botones
        btnAdministrarPacientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel administrarPacientesPanel = new JPanel();
                administrarPacientesPanel.setLayout(new BorderLayout());

                JTabbedPane tabbedPane = new JTabbedPane();
                tabbedPane.addTab("Crear", new CrearPacientePanel());
                tabbedPane.addTab("Modificar", new ModificarPacientePanel());
                tabbedPane.addTab("Eliminar", new EliminarPacientePanel());

                administrarPacientesPanel.add(tabbedPane, BorderLayout.CENTER);

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

                administrarSucursalesPanel.add(tabbedPane, BorderLayout.CENTER);

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

                administrarPracticasPanel.add(tabbedPane, BorderLayout.CENTER);

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

                administrarPeticionesPanel.add(tabbedPane, BorderLayout.CENTER);

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
	
	            administrarResultadosPanel.add(tabbedPane, BorderLayout.CENTER);
	
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

                administrarUsuariosPanel.add(tabbedPane, BorderLayout.CENTER);

                mostrarPanel(administrarUsuariosPanel);
            }
        });

        btnRolesSeguridad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPanel(new RolesSeguridadPanel());
            }
        });
    }
	private void mostrarPanel(JPanel nuevoPanel) {
        setContentPane(nuevoPanel);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LaboratorioClinicoApp().setVisible(true);
            }
        });
    }
}
