package com.minibank.dao;

import com.minibank.model.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionDAO {

    public Long save(
            Connection connection,
            Transaction transaction
    ) throws SQLException {

        String sql = """
                INSERT INTO transactions (
                    transaction_reference,
                    source_account_id,
                    destination_account_id,
                    amount,
                    currency
                )
                VALUES (?, ?, ?, ?, ?)
                RETURNING id
                """;

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(
                    1,
                    transaction.getTransactionReference()
            );

            statement.setLong(
                    2,
                    transaction.getSourceAccountId()
            );

            statement.setLong(
                    3,
                    transaction.getDestinationAccountId()
            );

            statement.setBigDecimal(
                    4,
                    transaction.getAmount()
            );

            statement.setString(
                    5,
                    transaction.getCurrency().name()
            );

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                if (resultSet.next()) {
                    return resultSet.getLong("id");
                }

                throw new SQLException(
                        "Failed to create transaction."
                );
            }
        }
    }
}