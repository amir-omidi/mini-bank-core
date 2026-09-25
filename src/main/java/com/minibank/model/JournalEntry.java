package com.minibank.model;

import java.time.OffsetDateTime;
import java.util.Objects;

public class JournalEntry {

    private final Long id;
    private final Long transactionId;
    private final OffsetDateTime createdAt;

    public JournalEntry(
            Long id,
            Long transactionId,
            OffsetDateTime createdAt
    ) {
        this.id = id;

        this.transactionId = Objects.requireNonNull(
                transactionId,
                "Transaction id cannot be null"
        );

        this.createdAt = Objects.requireNonNull(
                createdAt,
                "Journal entry creation time cannot be null"
        );
    }

    public Long getId() {
        return id;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
}