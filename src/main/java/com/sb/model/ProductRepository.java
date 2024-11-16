package com.sb.model;

import com.sb.util.DatabaseConnection;
import com.sb.util.ProductQueries;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRepository {

    public Product findByBarcode(String barcode) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(ProductQueries.FIND_BY_ID)) {

            stmt.setString(1, barcode);
            stmt.setString(2, barcode);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToProduct(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar o produto por ID: " + e.getMessage());
        }

        return null;
    }

    private Product mapResultSetToProduct(ResultSet rs) throws SQLException {
        return new Product(
                rs.getInt("id"),
                rs.getString("barcode"),
                rs.getString("name"),
                rs.getDouble("price"),
                rs.getInt("quantity"),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getTimestamp("updated_at").toLocalDateTime(),
                rs.getTimestamp("deleted_at") != null ? rs.getTimestamp("deleted_at").toLocalDateTime() : null
        );
    }
}
