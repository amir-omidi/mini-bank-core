package com.minibank;

import com.minibank.model.Currency;
import com.minibank.model.Money;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Money a = new Money(
                new java.math.BigDecimal("1000.00"),
                Currency.USD
        );

        Money b = new Money(
                new java.math.BigDecimal("500.00"),
                Currency.USD
        );

        System.out.println(a);
        System.out.println(a.add(b));
        System.out.println(a.equals(
                new Money(
                        new java.math.BigDecimal("1000.00"),
                        Currency.USD
                )
        ));
    }
}