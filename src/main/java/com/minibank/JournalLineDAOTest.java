package com.minibank;

import com.minibank.config.DatabaseConnection;
import com.minibank.dao.JournalLineDAO;
import com.minibank.model.Currency;
import com.minibank.model.JournalLine;
import com.minibank.model.JournalLineType;

import java.math.BigDecimal;
import java.sql.Connection;

public class JournalLineDAOTest {

    public static void main(String[] args) {

        JournalLineDAO journalLineDAO =
                new JournalLineDAO();

        try (Connection connection =
                     DatabaseConnection.getConnection()) {

            connection.setAutoCommit(false);

            Long journalEntryId = 2L;

            JournalLine debitLine =
                    new JournalLine(
                            null,
                            journalEntryId,
                            3L,
                            JournalLineType.DEBIT,
                            new BigDecimal("150000"),
                            Currency.IRR
                    );

            JournalLine creditLine =
                    new JournalLine(
                            null,
                            journalEntryId,
                            4L,
                            JournalLineType.CREDIT,
                            new BigDecimal("150000"),
                            Currency.IRR
                    );

            Long debitLineId =
                    journalLineDAO.save(
                            connection,
                            debitLine
                    );

            Long creditLineId =
                    journalLineDAO.save(
                            connection,
                            creditLine
                    );

            connection.commit();

            System.out.println(
                    "Journal lines saved successfully!"
            );

            System.out.println(
                    "Debit Line ID: "
                            + debitLineId
            );

            System.out.println(
                    "Credit Line ID: "
                            + creditLineId
            );

        } catch (Exception e) {

            System.out.println(
                    "Journal line creation failed."
            );

            e.printStackTrace();
        }
    }
}