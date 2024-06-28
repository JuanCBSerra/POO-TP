package com.example.TPO;

import com.example.TPO.view.menuLogin;


public class App {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        menuLogin frame = new menuLogin("Sistema de Gestión de Laboratorio");
        frame.setVisible(true);

    }
}