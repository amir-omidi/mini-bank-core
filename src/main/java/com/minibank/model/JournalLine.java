package com.minibank.model;

import java.math.BigDecimal;
import java.util.Objects;

public class JournalLine {

    private final Long id;
    private final Long journalEntryId;
    private final Long accountId;
    private final JournalLineType type;
    private final BigDecimal amount;
    private final Currency currency;

    public JournalLine(
            Long id,
            Long journalEntryId,
            Long accountId,
            JournalLineType type,
            BigDecimal amount,
            Currency currency
    ) {
        this.id = id;

        this.journalEntryId = Objects.requireNonNull(
                journalEntryId,
                "Journal entry id cannot be null"
        );

        this.accountId = Objects.requireNonNull(
                accountId,
                "Account id cannot be null"
        );

        this.type = Objects.requireNonNull(
                type,
                "Journal line type cannot be null"
        );

        this.amount = Objects.requireNonNull(
                amount,
                "Journal line amount cannot be null"
        );

        this.currency = Objects.requireNonNull(
                currency,
                "Journal line currency cannot be null"
        );

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Journal line amount must be positive"
            );
        }
    }

    public Long getId() {
        return id;
    }

    public Long getJournalEntryId() {
        return journalEntryId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public JournalLineType getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }
}