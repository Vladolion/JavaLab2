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


    public static List<Transaction> transactionsByPeriod(List<Transaction> transactions, String monthYear1, String monthYear2) {
        List<Transaction> result = new ArrayList<>();
        boolean entered = false;
        for (Transaction transaction : transactions) {
            LocalDate date = LocalDate.parse(transaction.getDate(), dateFormatter);
            String transactionMonthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
            var dateA = transactionMonthYear.split("-");
            var date1 = monthYear1.split("-");
            var date2 = monthYear1.split("-");
            if(!entered && ((Integer.parseInt(dateA[1])>Integer.parseInt(date1[1]) ||
                    ((Integer.parseInt(dateA[1])==Integer.parseInt(date1[1]) &&
                            (Integer.parseInt(dateA[0])<=Integer.parseInt(date1[0]))))))) {
                entered = true;
            }
            if(entered && ((Integer.parseInt(dateA[1])<Integer.parseInt(date2[1]) ||
                    ((Integer.parseInt(dateA[1])==Integer.parseInt(date2[1]) &&
                            (Integer.parseInt(dateA[0])>Integer.parseInt(date2[0]))))))) {
                entered = false;
            }
            if(entered){
                result.add(transaction);
            }
        }
        return result;
    }

    // Тут будуть інші методи для аналізу транзакцій
}
