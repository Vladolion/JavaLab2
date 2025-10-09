package org.example;

import lombok.AllArgsConstructor;

import java.util.*;

public abstract class TransactionReportGenerator {

    public static void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance);
    }

    public static void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }

    public static void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("10 найбільших витрат:");
        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }

    public static void printTopAndLowExpensesReport(List<Transaction> topAndLowExpenses) {

    }
@AllArgsConstructor
    static class Double{
        public double amount = 0;
    }
    public static void printCoolReport(List<Transaction> transactions) {
        Map<String, Double>  coolReport = new HashMap<>();

        double maxExpense = 0;

        for (Transaction transaction : transactions) {
            if(coolReport.containsKey(transaction.getDescription())) {
                coolReport.get(transaction.getDescription()).amount += transaction.getAmount();
            }
            else{
                coolReport.put(transaction.getDescription(),new Double(transaction.getAmount()));
            }
        }

        for(Double d : coolReport.values()){
            if(d.amount<0)
            if(d.amount*-1 > maxExpense){
                maxExpense = d.amount;
            }
        }

        for(String key : coolReport.keySet()) {
            if(coolReport.get(key).amount < 0) {
                System.out.println(key + " : " + coolReport.get(key).amount);

                int stars = (int)(coolReport.get(key).amount / (maxExpense/40));
                for(int i = 0; i<stars;i++){
                    System.out.print('*');
                }
                System.out.println();
            }
        }
    }

    public static void printTopAndLowInPeriod(List<Transaction> transactions) {
        int max = 0;
        int min = transactions.size()-1;

        for(int i = 0; i < transactions.size(); i++) {
            if(transactions.get(i).getAmount() < 0) {
                if (transactions.get(i).getAmount()*-1 > transactions.get(max).getAmount()*-1) {
                    max = i;
                }
                if (transactions.get(i).getAmount()*-1 < transactions.get(min).getAmount()*-1) {
                    min = i;
                }
            }
        }

        System.out.println("Lowest:"+transactions.get(min).getDate()+"\n"+transactions.get(min).getDescription()+": "+transactions.get(min).getAmount());
        System.out.println("Highest:"+transactions.get(max).getDate()+"\n"+transactions.get(max).getDescription()+": "+transactions.get(max).getAmount());

    }
}

