package org.example;


import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TransactionReaderTest {

    @Test
    public void testCSVReader() {

        String fileName = "output.csv";
        List<List<String>> data = Arrays.asList(
                Arrays.asList("05-12-2023", "-1", "b"),
                Arrays.asList("06-12-2023", "-2", "d"),
                Arrays.asList("07-12-2023", "3", "s"),
                Arrays.asList("08-12-2023", "-4", "m")
        );

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (List<String> row : data) {
                writer.write(String.join(",", row));
                writer.write("\n");
            }
            System.out.println("CSV file '" + fileName + "' created successfully.");
        } catch (IOException e) {
            System.err.println("Error writing CSV file: " + e.getMessage());
            e.printStackTrace();
        }

        //String filePath = "./test.csv";
        List<Transaction> transactions = TransactionCSVReader.readTransactions(new File(fileName).toURI().toString().toString());

        Assertions.assertNotNull(transactions,"Transactions is null");
        Assertions.assertFalse(transactions.isEmpty(),"Transactions is empty");

        Transaction transaction = transactions.get(0);
        Assertions.assertEquals(data.get(0).get(2),transaction.getDescription(),"wrong desc");
        Assertions.assertEquals(Double.parseDouble(data.get(0).get(1)),transaction.getAmount(),"wrong desc");
        Assertions.assertEquals(data.get(0).get(0),transaction.getDate(),"wrong desc");
    }
}
