package com.minibank.model;

import java.util.Objects;

public class Customer {

    private final Long id;
    private final String customerNumber;
    private final String firstName;
    private final String lastName;
    private CustomerStatus status;

    public Customer(
            Long id,
            String customerNumber,
            String firstName,
            String lastName,
            CustomerStatus status
    ) {
        this.id = Objects.requireNonNull(
                id,
                "Customer id cannot be null"
        );

        this.customerNumber = Objects.requireNonNull(
                customerNumber,
                "Customer number cannot be null"
        );

        this.firstName = Objects.requireNonNull(
                firstName,
                "First name cannot be null"
        );

        this.lastName = Objects.requireNonNull(
                lastName,
                "Last name cannot be null"
        );

        this.status = Objects.requireNonNull(
                status,
                "Customer status cannot be null"
        );
    }

    public Long getId() {
        return id;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public CustomerStatus getStatus() {
        return status;
    }
}