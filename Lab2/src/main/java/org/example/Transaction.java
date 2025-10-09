package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Transaction {
    @Getter
    private String date;
    @Getter
    @Setter
    private double amount;
    @Getter
    private String description;

    @Override
    public String toString() {
        return "Transaction{" +
                "date='" + date + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }
}