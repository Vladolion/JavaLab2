package org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public abstract class TransactionAnalyzer {
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");


    // Метод для розрахунку загального балансу
    public static double calculateTotalBalance(List<Transaction> transactions) {
        double balance = 0;
        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }
        return balance;
    }

    public static int countTransactionsByMonth(List<Transaction> transactions, String monthYear) {
        int count = 0;
        for (Transaction transaction : transactions) {
            LocalDate date = LocalDate.parse(transaction.getDate(), dateFormatter);
            String transactionMonthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
            if (transactionMonthYear.equals(monthYear)) {
                count++;
            }
        }
        return count;
    }

    public static List<Transaction> findTopExpenses(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0) // Вибірка лише витрат (від'ємні значення)
                .sorted(Comparator.comparing(Transaction::getAmount)) // Сортування за сумою
                .limit(10) // Обмеження результату першими 10 записами
                .collect(Collectors.toList()); // Збір результату в список
    }


    public static List<Transaction> findByPeriod(List<Transaction> transactions, String beginDate, String endDate) {
        LocalDate begin = LocalDate.parse(beginDate, dateFormatter);
        LocalDate end = LocalDate.parse(endDate, dateFormatter);
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .filter(t -> {
                    LocalDate tempDate = LocalDate.parse(t.getDate(), dateFormatter);
                    if (tempDate.isAfter(begin) && tempDate.isBefore(end) || tempDate.isEqual(begin) || tempDate.isEqual(end)) {
                        return true;
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    // Тут будуть інші методи для аналізу транзакцій
}
