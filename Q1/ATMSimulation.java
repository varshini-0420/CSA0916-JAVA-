import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;

class InsufficientFundsException extends Exception {
    InsufficientFundsException(String message) {
        super(message);
    }
}

public class ATMSimulation {

    static double balance = 10000;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            // Check card reader file
            FileReader card = new FileReader("card.txt");
            card.close();

            System.out.println("===== BANK ATM SIMULATION =====");
            System.out.print("Enter Account Number: ");
            int accountNumber = sc.nextInt();

            System.out.print("Enter Withdrawal Amount: ");
            double amount = sc.nextDouble();

            if (accountNumber <= 0) {
                throw new IllegalArgumentException("Invalid account number!");
            }

            if (amount <= 0) {
                throw new IllegalArgumentException("Withdrawal amount must be positive!");
            }

            if (amount > balance) {
                throw new InsufficientFundsException("Insufficient account balance!");
            }

            balance = balance - amount;

            System.out.println("Withdrawal Successful!");
            System.out.println("Cash Dispensed: Rs." + amount);
            System.out.println("Remaining Balance: Rs." + balance);

        } catch (FileNotFoundException e) {
            System.out.println("Card Reader Error: Card file not found!");

        } catch (InputMismatchException | IllegalArgumentException e) {
            System.out.println("Invalid Input: " + e.getMessage());

        } catch (InsufficientFundsException e) {
            System.out.println("Transaction Failed: " + e.getMessage());

        } catch (IOException e) {
            System.out.println("File Error: " + e.getMessage());

        } finally {
            System.out.println("ATM Transaction Process Completed.");
            sc.close();
        }
    }
}