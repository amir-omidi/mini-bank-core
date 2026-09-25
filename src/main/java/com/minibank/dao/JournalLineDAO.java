package com.minibank.dao;

import com.minibank.model.JournalLine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JournalLineDAO {

    public Long save(
            Connection connection,
            JournalLine journalLine
    ) throws SQLException {

        String sql = """
                INSERT INTO journal_lines (
                    journal_entry_id,
                    account_id,
                    line_type,
                    amount,
                    currency
                )
                VALUES (?, ?, ?, ?, ?)
                RETURNING id
                """;

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setLong(
                    1,
                    journalLine.getJournalEntryId()
            );

            statement.setLong(
                    2,
                    journalLine.getAccountId()
            );

            statement.setString(
                    3,
                    journalLine.getType().name()
            );

            statement.setBigDecimal(
                    4,
                    journalLine.getAmount()
            );

            statement.setString(
                    5,
                    journalLine.getCurrency().name()
            );

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                if (resultSet.next()) {
                    return resultSet.getLong("id");
                }

                throw new SQLException(
                        "Failed to create journal line."
                );
            }
        }
    }
}