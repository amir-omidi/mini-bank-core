package com.minibank.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Transaction {

    private final Long id;
    private final String transactionReference;
    private final Long sourceAccountId;
    private final Long destinationAccountId;
    private final BigDecimal amount;
    private final Currency currency;

    public Transaction(
            Long id,
            String transactionReference,
            Long sourceAccountId,
            Long destinationAccountId,
            BigDecimal amount,
            Currency currency
    ) {
        this.id = id;

        this.transactionReference = Objects.requireNonNull(
                transactionReference,
                "Transaction reference cannot be null"
        );

        this.sourceAccountId = Objects.requireNonNull(
                sourceAccountId,
                "Source account id cannot be null"
        );

        this.destinationAccountId = Objects.requireNonNull(
                destinationAccountId,
                "Destination account id cannot be null"
        );

        this.amount = Objects.requireNonNull(
                amount,
                "Transaction amount cannot be null"
        );

        this.currency = Objects.requireNonNull(
                currency,
                "Transaction currency cannot be null"
        );

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Transaction amount must be positive"
            );
        }
    }

    public Long getId() {
        return id;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    public Long getSourceAccountId() {
        return sourceAccountId;
    }

    public Long getDestinationAccountId() {
        return destinationAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }
}