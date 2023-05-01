package com.learntocode;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FinancialTransactions {
    public static void main(String[] args) throws IOException {
        ArrayList<Product> transactions = getTransactions();

        for (Product product : transactions) {
            System.out.println(product);
        }
        Scanner scanner = new Scanner(System.in);
        int command = 0;
        while(command != 5){
            System.out.println("What would you like to do?");
            System.out.println("1- Add deposit");
            System.out.println("2- Make Payment (Debit)");
            System.out.println("3- Ledger");
            System.out.println("4- Exit");

            System.out.print("Enter command: ");
            command = scanner.nextInt();

            switch (command) {
                case 1:addDeposit(transactions);
                case 2:
                case 3:
                case 4:
                    System.out.println("Thank You!\nHave a Good Day!!");
            }
        }

    }
    private static ArrayList<Product> getTransactions(){
        ArrayList<Product> transactions = new ArrayList<>();
        String line;
        return transactions;
    }
    private static void addDeposit(ArrayList<Product>transactions) throws IOException {
        FileWriter writer = new FileWriter("transactions.csv");
        Scanner input = new Scanner(System.in);
        System.out.println("Enter amount");
        writer.write(String.valueOf(input));
        writer.close();

    }
}
