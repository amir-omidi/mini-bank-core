package com.minibank;

import com.minibank.dao.AccountDAO;
import com.minibank.model.Account;
import com.minibank.service.AccountService;

public class AccountServiceTest {

    public static void main(String[] args) {

        AccountDAO accountDAO = new AccountDAO();

        AccountService accountService =
                new AccountService(accountDAO);

        try {

            Account account =
                    accountService.getAccount(
                            "ACC-000001"
                    );

            System.out.println(
                    "Account loaded successfully!"
            );

            System.out.println(
                    "Account: "
                            + account.getAccountNumber()
            );

            System.out.println(
                    "Balance: "
                            + account.getBalance()
            );

        } catch (Exception e) {

            System.out.println(
                    "Operation failed."
            );

            e.printStackTrace();
        }
    }
}