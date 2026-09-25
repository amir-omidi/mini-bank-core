package com.minibank.service;

import com.minibank.config.DatabaseConnection;
import com.minibank.dao.AccountDAO;
import com.minibank.model.Account;
import com.minibank.model.AccountStatus;

import java.math.BigDecimal;
import java.sql.Connection;

public class TransferService {

    private final AccountDAO accountDAO;

    public TransferService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }
    private void validateTransfer(
            Account source,
            Account destination,
            BigDecimal amount
    ) {
        if (source == null) {
            throw new IllegalArgumentException(
                    "Source account not found"
            );
        }

        if (destination == null) {
            throw new IllegalArgumentException(
                    "Destination account not found"
            );
        }

        if (source.getId().equals(destination.getId())) {
            throw new IllegalArgumentException(
                    "Source and destination accounts must be different"
            );
        }

        if (amount == null) {
            throw new IllegalArgumentException(
                    "Transfer amount cannot be null"
            );
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Transfer amount must be positive"
            );
        }

        if (source.getStatus() != AccountStatus.ACTIVE) {
            throw new IllegalArgumentException(
                    "Source account is not active"
            );
        }

        if (destination.getStatus() != AccountStatus.ACTIVE) {
            throw new IllegalArgumentException(
                    "Destination account is not active"
            );
        }

        if (source.getBalance().getCurrency()
                != destination.getBalance().getCurrency()) {

            throw new IllegalArgumentException(
                    "Source and destination currencies must match"
            );
        }

        if (source.getBalance().getAmount()
                .compareTo(amount) < 0) {

            throw new IllegalArgumentException(
                    "Insufficient balance"
            );
        }
    }
    public void transfer(
            String sourceAccountNumber,
            String destinationAccountNumber,
            BigDecimal amount
    ) {

        try (Connection connection = DatabaseConnection.getConnection()) {

            connection.setAutoCommit(false);

            try {
                Account source = accountDAO.findByAccountNumber(
                        connection,
                        sourceAccountNumber
                );

                Account destination = accountDAO.findByAccountNumber(
                        connection,
                        destinationAccountNumber
                );

                validateTransfer(
                        source,
                        destination,
                        amount
                );

                accountDAO.debit(
                        connection,
                        source.getId(),
                        amount
                );

                accountDAO.credit(
                        connection,
                        destination.getId(),
                        amount
                );

                connection.commit();

                System.out.println(
                        "Transfer completed successfully."
                );

            } catch (Exception e) {

                connection.rollback();

                System.out.println(
                        "Transfer failed. Transaction rolled back."
                );

                throw e;
            }

        } catch (Exception e) {

            throw new RuntimeException(
                    "Transfer operation failed.",
                    e
            );
        }
    }
}