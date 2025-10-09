package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";
        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);

        double totalBalance = TransactionAnalyzer.calculateTotalBalance(transactions);
        TransactionReportGenerator.printBalanceReport(totalBalance);

        String monthYear = "01-2024";
        int transactionsCount = TransactionAnalyzer.countTransactionsByMonth(transactions, monthYear);
        TransactionReportGenerator.printTransactionsCountByMonth(monthYear, transactionsCount);

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);
        TransactionReportGenerator.printTopExpensesReport(topExpenses);

        System.out.println();
        System.out.println("-----------------------------------------");
        TransactionReportGenerator.printCoolReport(transactions);

        System.out.println("-----------------------------------------");
        List<Transaction> transactionsByPeriod = TransactionAnalyzer.transactionsByPeriod(transactions,"01-2024","02-2024");
        TransactionReportGenerator.printTopAndLowInPeriod(transactionsByPeriod);
    }
}
