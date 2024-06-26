package com.example.TPO;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LaboratorioClinicoApp().setVisible(true);
            }
        });
    }
}