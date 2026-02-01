package com.budget_app.csvHandler;

import com.budget_app.domain.transaction.Transaction;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

@Component
public class CSVTransactionConverter {

    public CSVTransactionConverter() {
    }

    public Transaction convert (String contents) {
        System.out.println(contents);
        String[] attributes = contents.split(",");
        if (!Objects.equals(attributes[attributes.length - 1], "Posted")) {
            System.out.println("Item is not posted");
            return null;
        }
        System.out.println(Arrays.toString(attributes));
        LocalDate date = LocalDate.parse(attributes[0]);
        String company = attributes[1].replace("\"", "");
        double amount;
        try {
            amount = Double.valueOf(attributes[4]);
        } catch (Exception e) {
            System.out.println("Unable to convert " + attributes[4] + " to double. Attempting next value in list");
            amount = Double.valueOf(attributes[5]);
        }

        Transaction transaction = new Transaction()
                .setDate(date)
                .setCompany(company)
                .setAmount(amount);
        return transaction;
    }

}
