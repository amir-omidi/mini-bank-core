package com.minibank;

import com.minibank.config.DatabaseConnection;
import com.minibank.dao.TransactionDAO;
import com.minibank.model.Currency;
import com.minibank.model.Transaction;

import java.math.BigDecimal;
import java.sql.Connection;

public class TransactionDAOTest {

    public static void main(String[] args) {

        TransactionDAO transactionDAO =
                new TransactionDAO();

        try (Connection connection =
                     DatabaseConnection.getConnection()) {

            connection.setAutoCommit(false);

            Transaction transaction =
                    new Transaction(
                            null,
                            "TX-JAVA-001",
                            3L,
                            4L,
                            new BigDecimal("150000"),
                            Currency.IRR
                    );

            Long transactionId =
                    transactionDAO.save(
                            connection,
                            transaction
                    );

            connection.commit();

            System.out.println(
                    "Transaction saved successfully!"
            );

            System.out.println(
                    "Transaction ID: "
                            + transactionId
            );

        } catch (Exception e) {

            System.out.println(
                    "Transaction creation failed."
            );

            e.printStackTrace();
        }
    }
}