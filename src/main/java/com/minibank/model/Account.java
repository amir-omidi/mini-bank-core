package com.minibank.model;

import java.util.Objects;

public class Account {

    private final Long id;
    private final String accountNumber;
    private final Long customerId;
    private final AccountType accountType;
    private final Money balance;
    private AccountStatus status;

    public Account(
            Long id,
            String accountNumber,
            Long customerId,
            AccountType accountType,
            Money balance,
            AccountStatus status
    ) {
        this.id = Objects.requireNonNull(
                id,
                "Account id cannot be null"
        );

        this.accountNumber = Objects.requireNonNull(
                accountNumber,
                "Account number cannot be null"
        );

        this.customerId = Objects.requireNonNull(
                customerId,
                "Customer id cannot be null"
        );

        this.accountType = Objects.requireNonNull(
                accountType,
                "Account type cannot be null"
        );

        this.balance = Objects.requireNonNull(
                balance,
                "Account balance cannot be null"
        );

        this.status = Objects.requireNonNull(
                status,
                "Account status cannot be null"
        );
    }

    public Long getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public Money getBalance() {
        return balance;
    }

    public AccountStatus getStatus() {
        return status;
    }
}