package com.learntocode;

import java.io.*;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class FinancialTransactions {
        private static ArrayList<Product> transactions = new ArrayList<Product>();
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
                        System.out.println("Invalid option");
                        break;
                }
            }

            scanner.close();
        }

        public static void loadTransactions(String fileName) throws FileNotFoundException {
            fileName = "transactions.csv";
            ArrayList<Product> transactions = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            Scanner input = new Scanner(System.in);

            try {
                File file = new File("transactions.csv");
                reader = new BufferedReader(new FileReader(file));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println("No transactions found.");
                        System.out.println("Please add new transaction.");
                        System.out.print("Enter date (yyyy-MM-dd): ");
                        String date = input.nextLine();
                        System.out.print("Enter time (Military format, Example: 13:45:00 ): ");
                        String time = input.nextLine();
                        System.out.print("Enter description: ");
                        String description = input.nextLine();
                        System.out.print("Enter vendor: ");
                        String vendor = input.nextLine();
                        System.out.print("Enter amount: ");
                        Double amount = input.nextDouble();
                    }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        private static void addDeposit(Scanner scanner) {
            try{
            Scanner scanner1 = new Scanner(System.in);
            String filepath = "transactions.csv";
            FileWriter fileWriter = new FileWriter(filepath,true);
            BufferedWriter bufWriter = new BufferedWriter(fileWriter);

            System.out.println("Enter deposit.");
            System.out.println("Enter date(yyyy-MM-dd): ");
            String date = scanner1.nextLine();
            System.out.println("Enter time (Military format, Example: 13:45:00): ");
            String time = scanner1.nextLine();
            System.out.println("Enter description: ");
            String description = scanner1.nextLine();
            System.out.println("Enter vendor: ");
            String vendor = scanner1.nextLine();
            System.out.println("Enter amount of a deposit: ");
            double amount = scanner1.nextDouble();


            Product newProduct;
            double payment = 0;
            newProduct = new Product(date,time,description,vendor,payment);
            bufWriter.write(newProduct.getDate() + "|" + newProduct.getTime() + "|" + newProduct.getDescription() + "|" + newProduct.getVendor() + "|" + newProduct.getAmount());
            bufWriter.newLine();
            transactions.add(newProduct);
            System.out.println("Transaction was added.");
            bufWriter.close();
            


        } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private static void addPayment(Scanner scanner) {
            try{

                Scanner scanner1 = new Scanner(System.in);
                String filepath = "transactions.csv";
                FileWriter fileWriter = new FileWriter(filepath,true);
                BufferedWriter bufWriter = new BufferedWriter(fileWriter);

            System.out.println("Enter deposit.");
            System.out.println("Enter date(yyyy-MM-dd): ");
            Scanner scanner2 = new Scanner(System.in);
            String date = scanner2.nextLine();
            System.out.println("Enter time (Military format, Example: 13:45:00): ");
            String time = scanner2.nextLine();
            System.out.println("Enter description: ");
            String description = scanner2.nextLine();
            System.out.println("Enter vendor: ");
            String vendor = scanner2.nextLine();
            System.out.println("Enter amount of a payment: ");
            double amount = scanner2.nextDouble();
            ArrayList<Product> Payment = new ArrayList<>();
            Payment.add(new Product(date,time,description,vendor,amount));

                Product newProduct;
                double payment = 0;
                newProduct = new Product(date,time,description,vendor,payment);
                bufWriter.write(newProduct.getDate() + "|" + newProduct.getTime() + "|" + newProduct.getDescription() + "|" + newProduct.getVendor() + "|" + newProduct.getAmount());
                bufWriter.newLine();
                transactions.add(newProduct);
                System.out.println("Transaction was added.");
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
                        System.out.println("Invalid option");
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
            // This method should display a table of all deposits in the `transactions` ArrayList.
            // The table should have columns for date, time, vendor, and amount.
            // The total amount of all deposits should be displayed at the bottom of the table.
        }

        private static void displayPayments() {
            // This method should display a table of all payments in the `transactions` ArrayList.
            // The table should have columns for date, time, vendor, and amount.
            // The total amount of all payments should be displayed at the bottom of the table.
        }

        private static void reportsMenu(Scanner scanner) {
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
                        // Generate a report for all transactions within the current month,
                        // including the date, vendor, and amount for each transaction.
                        // The report should include a total of all transaction amounts for the month.
                    case "2":
                        // Generate a report for all transactions within the previous month,
                        // including the date, vendor, and amount for each transaction.
                        // The report should include a total of all transaction amounts for the month.
                    case "3":
                        // Generate a report for all transactions within the current year,
                        // including the date, vendor, and amount for each transaction.
                        // The report should include a total of all transaction amounts for the year.

                    case "4":
                        // Generate a report for all transactions within the previous year,
                        // including the date, vendor, and amount for each transaction.
                        // The report should include a total of all transaction amounts for the year.
                    case "5":
                        // Prompt the user to enter a vendor name, then generate a report for all transactions
                        // with that vendor, including the date, vendor, and amount for each transaction.
                        // The report should include a total of all transaction amounts for the vendor.
                    case "0":
                        running = false;
                    default:
                        System.out.println("Invalid option");
                        break;
                }
            }
        }


        private static void filterTransactionsByDate(LocalDate startDate, LocalDate endDate) {
            // This method filters the transactions by date and prints a report to the console.
            // It takes two parameters: startDate and endDate, which represent the range of dates to filter by.
            // The method loops through the transactions list and checks each transaction's date against the date range.
            // Transactions that fall within the date range are printed to the console.
            // If no transactions fall within the date range, the method prints a message indicating that there are no results.
        }

        private static void filterTransactionsByVendor(String vendor) {
            // This method filters the transactions by vendor and prints a report to the console.
            // It takes one parameter: vendor, which represents the name of the vendor to filter by.
            // The method loops through the transactions list and checks each transaction's vendor name against the specified vendor name.
            // Transactions with a matching vendor name are printed to the console.
            // If no transactions match the specified vendor name, the method prints a message indicating that there are no results.
        }
    }


