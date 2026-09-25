package com.minibank;

import com.minibank.config.DatabaseConnection;
import com.minibank.dao.JournalEntryDAO;
import com.minibank.model.JournalEntry;

import java.sql.Connection;
import java.time.OffsetDateTime;

public class JournalEntryDAOTest {

    public static void main(String[] args) {

        JournalEntryDAO journalEntryDAO =
                new JournalEntryDAO();

        try (Connection connection =
                     DatabaseConnection.getConnection()) {

            connection.setAutoCommit(false);

            JournalEntry journalEntry =
                    new JournalEntry(
                            null,
                            8L,
                            OffsetDateTime.now()
                    );

            Long journalEntryId =
                    journalEntryDAO.save(
                            connection,
                            journalEntry
                    );

            connection.commit();

            System.out.println(
                    "Journal entry saved successfully!"
            );

            System.out.println(
                    "Journal Entry ID: "
                            + journalEntryId
            );

        } catch (Exception e) {

            System.out.println(
                    "Journal entry creation failed."
            );

            e.printStackTrace();
        }
    }
}