package com.minibank;

import com.minibank.dao.CustomerDAO;
import com.minibank.model.Customer;

public class CustomerDAOTest {

    public static void main(String[] args) {

        CustomerDAO customerDAO = new CustomerDAO();

        try {

            Customer customer =
                    customerDAO.findByCustomerNumber(
                            "CUS-000001"
                    );

            if (customer != null) {

                System.out.println(
                        "Customer found:"
                );

                System.out.println(
                        "ID: " + customer.getId()
                );

                System.out.println(
                        "Customer Number: "
                                + customer.getCustomerNumber()
                );

                System.out.println(
                        "Name: "
                                + customer.getFirstName()
                                + " "
                                + customer.getLastName()
                );

                System.out.println(
                        "Status: "
                                + customer.getStatus()
                );

            } else {

                System.out.println(
                        "Customer not found."
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