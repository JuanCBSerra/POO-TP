//package com.example.TPO;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class LaboratorioClinicoApp extends JFrame {
//
//    public LaboratorioClinicoApp() {
//        // Configuración de la ventana principal
//        setTitle("Administración del Laboratorio Clínico");
//        setSize(400, 300);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//
//        // Crear un panel principal
//        JPanel panel = new JPanel();
//        panel.setLayout(new GridLayout(4, 1, 10, 10));
//
//        // Crear y agregar botones al panel
//        JButton btnAgregarPaciente = new JButton("Agregar Paciente");
//        JButton btnVerResultados = new JButton("Ver Resultados");
//        JButton btnAdministrarInventario = new JButton("Administrar Inventario");
//
//        panel.add(btnAgregarPaciente);
//        panel.add(btnVerResultados);
//        panel.add(btnAdministrarInventario);
//
//        // Añadir panel al frame
//        add(panel);
//
//        // Agregar ActionListeners a los botones
//        btnAgregarPaciente.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                agregarPaciente();
//            }
//        });
//
//        btnVerResultados.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                verResultados();
//            }
//        });
//
//        btnAdministrarInventario.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                administrarInventario();
//            }
//        });
//    }
//
//    // Métodos para manejar acciones de los botones
//    private void agregarPaciente() {
//        JOptionPane.showMessageDialog(this, "Función para agregar paciente.");
//    }
//
//    private void verResultados() {
//        JOptionPane.showMessageDialog(this, "Función para ver resultados.");
//    }
//
//    private void administrarInventario() {
//        JOptionPane.showMessageDialog(this, "Función para administrar inventario.");
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new LaboratorioClinicoApp().setVisible(true);
//            }
//        });
//    }
//}
