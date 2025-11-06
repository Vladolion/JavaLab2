package org.example;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TransactionAnalyzerTest {
    @Test
    public void testCalculateTotalBalance() {
        // Створення тестових даних
        Transaction transaction1 = new Transaction("2023-01-01", 100.0, "Дохід");
        Transaction transaction2 = new Transaction("2023-01-02", -50.0, "Витрата");
        Transaction transaction3 = new Transaction("2023-01-03", 150.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        // Створення екземпляру TransactionAnalyzer з тестовими даними

        // Виклик методу, який потрібно протестувати
        double result = TransactionAnalyzer.calculateTotalBalance(transactions);

        // Перевірка результату
        Assertions.assertEquals(200.0, result, "Розрахунок загального балансу неправильний");
    }

    @Test
    public void testCountTransactionsByMonth() {
        // Підготовка тестових даних
        Transaction transaction1 = new Transaction("01-02-2023", 50.0, "Дохід");
        Transaction transaction2 = new Transaction("15-02-2023", -20.0, "Витрата");
        Transaction transaction3 = new Transaction("05-03-2023", 100.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        // Створення екземпляру TransactionAnalyzer з тестовими даними

        int countFeb = TransactionAnalyzer.countTransactionsByMonth(transactions,"02-2023");
        int countMar = TransactionAnalyzer.countTransactionsByMonth(transactions,"03-2023");

        // Перевірка результатів
        Assertions.assertEquals(2, countFeb, "Кількість транзакцій за лютий неправильна");
        Assertions.assertEquals(1, countMar, "Кількість транзакцій за березень неправильна");
    }


    @Test
    public void testPrintTopExpensesReport() {
        // Підготовка тестових даних
        Transaction transaction1 = new Transaction("01-02-2023", -50.0, "Дохід");
        Transaction transaction2 = new Transaction("15-02-2023", -20.0, "Витрата");
        Transaction transaction3 = new Transaction("05-03-2023", -100.0, "Дохід");
        Transaction transaction4 = new Transaction("05-03-2023", -100.0, "Дохід");
        Transaction transaction5 = new Transaction("05-03-2023", -100.0, "Дохід");
        Transaction transaction6 = new Transaction("05-03-2023", -100.0, "Дохід");
        Transaction transaction7 = new Transaction("05-03-2023", -100.0, "Дохід");
        Transaction transaction8 = new Transaction("05-03-2023", -100.0, "Дохід");
        Transaction transaction9 = new Transaction("05-03-2023", -100.0, "Дохід");
        Transaction transaction10 = new Transaction("05-03-2023", -100.0, "Дохід");
        Transaction transaction11 = new Transaction("05-03-2023", -100.0, "Дохід");
        List<Transaction> transactions1 = Arrays.asList(transaction1, transaction2, transaction3);
        List<Transaction> transactions2 = Arrays.asList(transaction1, transaction2, transaction3,transaction4,transaction5,transaction6,transaction7,transaction8,transaction9,transaction10,transaction11);
        List<Transaction> transactions3 = new ArrayList<Transaction>();

        // Створення екземпляру TransactionAnalyzer з тестовими даними

        List<Transaction> topExpenses1 = TransactionAnalyzer.findTopExpenses(transactions1);
        List<Transaction> topExpenses2 = TransactionAnalyzer.findTopExpenses(transactions2);
        List<Transaction> topExpenses3 = TransactionAnalyzer.findTopExpenses(transactions3);

        int res1 = topExpenses1.size();
        int res2 = topExpenses2.size();
        int res3 = topExpenses3.size();


        Assertions.assertEquals(3, res1, "Кількість транзакцій за березень неправильна");
        // Перевірка результатів
        Assertions.assertEquals(10, res2, "Кількість транзакцій за лютий неправильна");
        Assertions.assertEquals(0, res3, "Кількість транзакцій за березень неправильна");
    }

    @Test
    public void testCSVReader() {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";
        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);

        Assertions.assertNotNull(transactions,"Transactions is null");
        Assertions.assertFalse(transactions.isEmpty(),"Transactions is empty");

        Transaction transaction = transactions.get(0);
        Assertions.assertNotNull(transaction,"Transaction is null");
        Assertions.assertNotNull(transaction.getAmount(),"Amount is null");
        Assertions.assertNotNull(transaction.getDate(),"Date is null");
    }
}
