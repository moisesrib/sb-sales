package com.sb.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/sb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            throw new RuntimeException("Erro ao conectar ao banco de dados", e);
        }
    }

    public static void testConnection() {
        try (Connection connection = getConnection()) {
            if (connection != null && !connection.isClosed()) {
                System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao testar a conexão com o banco de dados: " + e.getMessage());
        }
    }
}
