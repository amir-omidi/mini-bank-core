package com.minibank.dao;

import com.minibank.config.DatabaseConnection;
import com.minibank.model.Customer;
import com.minibank.model.CustomerStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {

    public Customer findByCustomerNumber(String customerNumber)
            throws SQLException {

        String sql = """
                SELECT
                    id,
                    customer_number,
                    first_name,
                    last_name,
                    status
                FROM customers
                WHERE customer_number = ?
                """;

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, customerNumber);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    return new Customer(
                            resultSet.getLong("id"),
                            resultSet.getString("customer_number"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            CustomerStatus.valueOf(
                                    resultSet.getString("status")
                            )
                    );
                }

                return null;
            }
        }
    }
}