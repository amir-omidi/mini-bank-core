package com.minibank.dao;

import com.minibank.model.JournalEntry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JournalEntryDAO {

    public Long save(
            Connection connection,
            JournalEntry journalEntry
    ) throws SQLException {

        String sql = """
                INSERT INTO journal_entries (
                    transaction_id,
                    created_at
                )
                VALUES (?, ?)
                RETURNING id
                """;

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setLong(
                    1,
                    journalEntry.getTransactionId()
            );

            statement.setObject(
                    2,
                    journalEntry.getCreatedAt()
            );

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                if (resultSet.next()) {

                    return resultSet.getLong("id");
                }

                throw new SQLException(
                        "Failed to create journal entry."
                );
            }
        }
    }
}