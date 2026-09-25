package com.minibank;

import com.minibank.dao.AccountDAO;
import com.minibank.model.Account;

public class AccountDAOTest {

    public static void main(String[] args) {

        AccountDAO accountDAO = new AccountDAO();

        try {

            Account account =
                    accountDAO.findByAccountNumber(
                            "ACC-000001"
                    );

            if (account != null) {

                System.out.println(
                        "Account found:"
                );

                System.out.println(
                        "ID: " + account.getId()
                );

                System.out.println(
                        "Account Number: "
                                + account.getAccountNumber()
                );

                System.out.println(
                        "Customer ID: "
                                + account.getCustomerId()
                );

                System.out.println(
                        "Type: "
                                + account.getAccountType()
                );

                System.out.println(
                        "Balance: "
                                + account.getBalance()
                );

                System.out.println(
                        "Status: "
                                + account.getStatus()
                );

            } else {

                System.out.println(
                        "Account not found."
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Database operation failed."
            );

            e.printStackTrace();
        }
    }
}