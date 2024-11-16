package com.sb;

import com.sb.util.DatabaseConnection;
import com.sb.view.Tela;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection.testConnection();

        SwingUtilities.invokeLater(() -> {
            Tela tela = new Tela();
            tela.setVisible(true);
        });
    }
}