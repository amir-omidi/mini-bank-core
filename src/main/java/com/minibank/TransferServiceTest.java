package com.minibank;

import com.minibank.dao.AccountDAO;
import com.minibank.service.TransferService;

import java.math.BigDecimal;

public class TransferServiceTest {

    public static void main(String[] args) {

        AccountDAO accountDAO = new AccountDAO();

        TransferService transferService =
                new TransferService(accountDAO);

        try {

            transferService.transfer(
                    "ACC-000001",
                    "ACC-000002",
                    new BigDecimal("200000")
            );

            System.out.println(
                    "Test completed."
            );

        } catch (Exception e) {

            System.out.println(
                    "Transfer test failed."
            );

            e.printStackTrace();
        }
    }
}