package org.example;

import lombok.Getter;

public class Transaction {
    @Getter private String date;
    @Getter private double amount;
    @Getter private String description;

    public Transaction(String date, double amount, String description) {
        this.date = date;
        this.amount = amount;
        this.description = description;
    }
    // Getters
    // Setters
    // toString метод для зручності виводу
    @Override
    public String toString() {
        return "Transaction{" +
                "date='" + date + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }
}
