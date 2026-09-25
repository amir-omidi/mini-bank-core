package com.minibank.dao;

import com.minibank.config.DatabaseConnection;
import com.minibank.model.Account;
import com.minibank.model.AccountStatus;
import com.minibank.model.AccountType;
import com.minibank.model.Currency;
import com.minibank.model.Money;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {

    public Account findByAccountNumber(String accountNumber)
            throws SQLException {

        String sql = """
                SELECT
                    id,
                    account_number,
                    customer_id,
                    account_type,
                    currency,
                    balance,
                    status
                FROM accounts
                WHERE account_number = ?
                """;

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, accountNumber);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    return new Account(
                            resultSet.getLong("id"),

                            resultSet.getString("account_number"),

                            resultSet.getLong("customer_id"),

                            AccountType.valueOf(
                                    resultSet.getString("account_type")
                            ),

                            new Money(
                                    resultSet.getBigDecimal("balance"),
                                    Currency.valueOf(
                                            resultSet.getString("currency")
                                    )
                            ),

                            AccountStatus.valueOf(
                                    resultSet.getString("status")
                            )
                    );
                }

                return null;
            }
        }
    }
    public Account findByAccountNumber(
            Connection connection,
            String accountNumber
    ) throws SQLException {

        String sql = """
            SELECT
                id,
                account_number,
                customer_id,
                account_type,
                currency,
                balance,
                status
            FROM accounts
            WHERE account_number = ?
            """;

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, accountNumber);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    return new Account(
                            resultSet.getLong("id"),

                            resultSet.getString("account_number"),

                            resultSet.getLong("customer_id"),

                            AccountType.valueOf(
                                    resultSet.getString("account_type")
                            ),

                            new Money(
                                    resultSet.getBigDecimal("balance"),
                                    Currency.valueOf(
                                            resultSet.getString("currency")
                                    )
                            ),

                            AccountStatus.valueOf(
                                    resultSet.getString("status")
                            )
                    );
                }

                return null;
            }
        }
    }
    public void debit(
            Connection connection,
            Long accountId,
            BigDecimal amount
    ) throws SQLException {

        String sql = """
                UPDATE accounts
                SET balance = balance - ?
                WHERE id = ?
                """;

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setBigDecimal(1, amount);
            statement.setLong(2, accountId);

            statement.executeUpdate();
        }
    }

    public void credit(
            Connection connection,
            Long accountId,
            BigDecimal amount
    ) throws SQLException {

        String sql = """
                UPDATE accounts
                SET balance = balance + ?
                WHERE id = ?
                """;

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setBigDecimal(1, amount);
            statement.setLong(2, accountId);

            statement.executeUpdate();
        }
    }
}