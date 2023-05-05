package com.learntocode;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class FinancialTransactions {
        private static final ArrayList<Product> transactions = new ArrayList<Product>();
        private static final String FILE_NAME = "transactions.csv";
        private static final String DATE_FORMAT = "yyyy-MM-dd";
        private static final String TIME_FORMAT = "HH:mm:ss";
        private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
        private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMAT);

        public static void main(String[] args) throws FileNotFoundException {
            loadTransactions(FILE_NAME);
            Scanner scanner = new Scanner(System.in);
            boolean running = true;

            while (running) {
                System.out.println("Welcome to TransactionApp");
                System.out.println("Choose an option:");
                System.out.println("D) Add Deposit");
                System.out.println("P) Make Payment (Debit)");
                System.out.println("L) Ledger");
                System.out.println("X) Exit");

                String input = scanner.nextLine().trim();

                switch (input.toUpperCase()) {
                    case "D":
                        addDeposit(scanner);
                        break;
                    case "P":
                        addPayment(scanner);
                        break;
                    case "L":
                        ledgerMenu(scanner);
                        break;
                    case "X":
                        running = false;
                        break;
                    default:
                        break;
                }
            }

            scanner.close();
        }
        public static void loadTransactions(String fileName) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(fileName));
                    String line = "";
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split("\\|");
                        LocalDate date = LocalDate.parse(parts[0], DATE_FORMATTER);
                        LocalTime time = LocalTime.parse(parts[1], TIME_FORMATTER);
                        String description = parts[2];
                        String vendor = parts[3];
                        double amount = Double.parseDouble(parts[4]);
                        transactions.add(new Product(date, time, description, vendor, amount));
                    }
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Error loading inventory: " + e.getMessage());
                }
            }

        private static void addDeposit(Scanner scanner) {
            try{


            FileWriter fileWriter = new FileWriter(FILE_NAME,true);
            BufferedWriter bufWriter = new BufferedWriter(fileWriter);

            System.out.println("Enter deposit.");
            System.out.println("Enter description: ");
            String description = scanner.nextLine();
            System.out.println("Enter vendor: ");
            String vendor = scanner.nextLine();
            System.out.println("Enter amount of a deposit: ");
            double amount = scanner.nextDouble();

            LocalDate date = LocalDate.now();
            LocalTime time1 = LocalTime.now();
            String time2 = time1.format(TIME_FORMATTER);
            LocalTime time = LocalTime.parse(time2,TIME_FORMATTER);

            Product Product = new Product(date,time,description, vendor, amount);
            bufWriter.write(Product.getDate() + "|" + Product.getTime() + "|" + Product.getDescription() + "|" + Product.getVendor() + "|" + Product.getAmount());
            bufWriter.newLine();
            transactions.add(Product);
            System.out.println("Transaction was added.");
            bufWriter.close();
            
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private static void addPayment(Scanner scanner) {
            try{


                FileWriter fileWriter = new FileWriter(FILE_NAME,true);
                BufferedWriter bufWriter = new BufferedWriter(fileWriter);

            System.out.println("Enter deposit.");
            System.out.println("Enter description: ");
            String description = scanner.nextLine();
            System.out.println("Enter vendor: ");
            String vendor = scanner.nextLine();
            System.out.println("Enter amount of a payment: ");
            double amount = scanner.nextDouble();

            LocalDate date = LocalDate.now();
            LocalTime time1 = LocalTime.now();
            String time2 = time1.format(TIME_FORMATTER);
            LocalTime time = LocalTime.parse(time2,TIME_FORMATTER);


            Product newProduct = new Product(date,time,description,vendor,amount);
            bufWriter.write(newProduct.getDate() + "|" + newProduct.getTime() + "|" + newProduct.getDescription() + "|" + newProduct.getVendor() + "|" + newProduct.getAmount());
            bufWriter.newLine();
            transactions.add(newProduct);
            System.out.println("Payment was added.");
            bufWriter.close();

        } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private static void ledgerMenu(Scanner scanner) {

            boolean running = true;

            while (running) {
                System.out.println("Ledger");
                System.out.println("Choose an option:");
                System.out.println("A) All");
                System.out.println("D) Deposits");
                System.out.println("P) Payments");
                System.out.println("R) Reports");
                System.out.println("H) Home");

                String input = scanner.nextLine().trim();


                switch (input.toUpperCase()) {
                    case "A":
                        displayLedger();
                        break;
                    case "D":
                        displayDeposits();
                        break;
                    case "P":
                        displayPayments();
                        break;
                    case "R":
                        reportsMenu(scanner);
                        break;
                    case "H":
                        running = false;
                    default:
                        System.out.println("Returned to home screen.\n");
                        break;
                }
            }
        }

        private static void displayLedger() {
            for(Product product : transactions){
                System.out.println(product);
            }

        }

        private static void displayDeposits() {
            for(Product product : transactions) {
                if(product.getAmount() > 0){
                System.out.println(product);
                }
            }
        }

        private static void displayPayments() {
            for(Product product : transactions) {
                if(product.getAmount() < 0){
                    System.out.println(product);
                }
            }
        }

        private static void reportsMenu(Scanner scanner) {
            LocalDate date = LocalDate.now();
            LocalDate currentDate = LocalDate.now();
            LocalDate firstDayOfPreviousMonth = currentDate.minusMonths(1).withDayOfMonth(1);
            LocalDate lastDayOfPreviousMonth = firstDayOfPreviousMonth.withDayOfMonth(firstDayOfPreviousMonth.lengthOfMonth());



            boolean running = true;
            while (running) {
                System.out.println("Reports");
                System.out.println("Choose an option:");
                System.out.println("1) Month To Date");
                System.out.println("2) Previous Month");
                System.out.println("3) Year To Date");
                System.out.println("4) Previous Year");
                System.out.println("5) Search by Vendor");
                System.out.println("0) Back");

                String input = scanner.nextLine().trim();

                switch (input) {
                    case "1":
                        for(Product information: transactions){
                            if(information.getDate().getMonth() == date.getMonth()){
                                System.out.println(information);
                            }
                        }
                        break;

                    case "2":
                        for(Product information : transactions){
                            if (information.getDate().getMonthValue() == date.getMonthValue()-1) {
                                System.out.println(information);
                            }
                        }

                        break;
                    case "3":
                        for(Product information : transactions){
                            if(information.getDate().getYear() == date.getYear()) {
                                System.out.println(information);
                            }
                        }


                        break;

                    case "4":
                        for(Product information : transactions ){
                            if(information.getDate().getYear() == (date.getYear()-1)){
                                System.out.println(information);
                            }
                        }
                        break;

                    case "5":
                        System.out.println("Enter the name of the vendor: ");
                        String response = scanner.next();
                        for (Product information : transactions){
                            if(information.getVendor().equals(response)){
                                System.out.println(information);
                            }
                        }
                        break;
                    case "0":
                        running = false;
                    default:
                        System.out.println("Invalid option");
                        break;
                }
            }
        }
}










