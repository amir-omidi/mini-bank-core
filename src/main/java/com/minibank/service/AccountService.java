package com.minibank.service;

import com.minibank.dao.AccountDAO;
import com.minibank.model.Account;

import java.sql.SQLException;

public class AccountService {

    private final AccountDAO accountDAO;

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Account getAccount(String accountNumber)
            throws SQLException {

        Account account =
                accountDAO.findByAccountNumber(accountNumber);

        if (account == null) {
            throw new IllegalArgumentException(
                    "Account not found: " + accountNumber
            );
        }

        return account;
    }
}