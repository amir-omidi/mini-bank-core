package com.minibank.model;

import java.math.BigDecimal;
import java.util.Objects;

public final class Money {

    private final BigDecimal amount;
    private final Currency currency;

    public Money(BigDecimal amount, Currency currency) {
        this.amount = Objects.requireNonNull(
                amount,
                "Amount cannot be null"
        );

        this.currency = Objects.requireNonNull(
                currency,
                "Currency cannot be null"
        );

        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "Money amount cannot be negative"
            );
        }
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Money add(Money other) {
        requireSameCurrency(other);

        return new Money(
                amount.add(other.amount),
                currency
        );
    }

    public Money subtract(Money other) {
        requireSameCurrency(other);

        BigDecimal result = amount.subtract(other.amount);

        if (result.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "Money amount cannot become negative"
            );
        }

        return new Money(result, currency);
    }

    private void requireSameCurrency(Money other) {
        Objects.requireNonNull(other, "Money cannot be null");

        if (currency != other.currency) {
            throw new IllegalArgumentException(
                    "Currencies must be the same"
            );
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Money)) {
            return false;
        }

        Money money = (Money) o;

        return amount.compareTo(money.amount) == 0
                && currency == money.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                amount.stripTrailingZeros(),
                currency
        );
    }

    @Override
    public String toString() {
        return amount + " " + currency;
    }
}