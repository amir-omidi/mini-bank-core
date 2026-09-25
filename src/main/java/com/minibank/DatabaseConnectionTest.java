package com.minibank;

import com.minibank.config.DatabaseConnection;

import java.sql.Connection;

public class DatabaseConnectionTest {

    public static void main(String[] args) {

        try (Connection connection = DatabaseConnection.getConnection()) {

            System.out.println("Connected successfully!");
            System.out.println(
                    "Database: " +
                            connection.getCatalog()
            );

        } catch (Exception e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }
    }
}