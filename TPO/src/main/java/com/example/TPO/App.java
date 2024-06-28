package com.example.TPO;

import com.example.TPO.view.menuLogin;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        menuLogin frame = new menuLogin("Sistema de Gestión de Laboratorio");
        frame.setVisible(true);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LaboratorioClinicoApp().setVisible(true);
            }
        });
    }
}