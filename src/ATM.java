import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ATM {
    private static Map<String, String> userCredentials = new HashMap<>();
    private static Map<String, Double> userBalances = new HashMap<>();
    private static Map<String, StringBuilder> transactionHistory = new HashMap<>();

    public static void main(String[] args) {
        // Dummy data for demonstration purposes
        userCredentials.put("user123", "pass123");
        userBalances.put("user123", 1000.0);

        userCredentials.put("user1234", "pass1234");
        userBalances.put("user123", 1000.0);

        transactionHistory.put("user123", new StringBuilder("Transaction History:\n"));

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the ATM Interface!");

        // User login
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter User PIN: ");
        String pin = scanner.nextLine();

        if (authenticateUser(userId, pin)) {
            System.out.println("Login successful! What would you like to do?");

            while (true) {
                displayMenu();
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        displayTransactionHistory(userId);
                        break;
                    case 2:
                        withdraw(userId);
                        break;
                    case 3:
                        deposit(userId);
                        break;
                    case 4:
                        transfer(userId);
                        break;
                    case 5:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Invalid credentials. Exiting...");
        }
    }

    private static boolean authenticateUser(String userId, String pin) {
        return userCredentials.containsKey(userId) && userCredentials.get(userId).equals(pin);
    }

    private static void displayMenu() {
        System.out.println("\nATM Menu:");
        System.out.println("1. View Transaction History");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer");
        System.out.println("5. Quit");
        System.out.print("Enter your choice: ");
    }

    private static void displayTransactionHistory(String userId) {
        System.out.println(transactionHistory.get(userId).toString());
    }

    private static void withdraw(String userId) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the withdrawal amount: ");
        double amount = scanner.nextDouble();

        double balance = userBalances.get(userId);
        if (amount > 0 && amount <= balance) {
            userBalances.put(userId, balance - amount);
            transactionHistory.get(userId).append("Withdrawal: -$").append(amount).append("\n");
            System.out.println("Withdrawal successful. Remaining balance: $" + userBalances.get(userId));
        } else {
            System.out.println("Invalid amount or insufficient funds. Please try again.");
        }
    }

    private static void deposit(String userId) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the deposit amount: ");
        double amount = scanner.nextDouble();

        if (amount > 0) {
            double balance = userBalances.get(userId);
            userBalances.put(userId, balance + amount);
            transactionHistory.get(userId).append("Deposit: +$").append(amount).append("\n");
            System.out.println("Deposit successful. Updated balance: $" + userBalances.get(userId));
        } else {
            System.out.println("Invalid amount. Please try again.");
        }
    }

    private static void transfer(String userId) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the recipient's User ID: ");
        String recipientId = scanner.nextLine();

        if (userCredentials.containsKey(recipientId) && !userId.equals(recipientId)) {
            System.out.print("Enter the transfer amount: ");
            double amount = scanner.nextDouble();

            double senderBalance = userBalances.get(userId);
            double recipientBalance = userBalances.get(recipientId);

            if (amount > 0 && amount <= senderBalance) {
                userBalances.put(userId, senderBalance - amount);
                userBalances.put(recipientId, recipientBalance + amount);

                transactionHistory.get(userId).append("Transfer to ").append(recipientId).append(": -$").append(amount).append("\n");
                transactionHistory.get(recipientId).append("Transfer from ").append(userId).append(": +$").append(amount).append("\n");

                System.out.println("Transfer successful. Remaining balance: $" + userBalances.get(userId));
            } else {
                System.out.println("Invalid amount or insufficient funds. Please try again.");
            }
        } else {
            System.out.println("Invalid recipient User ID. Please try again.");
        }
    }
}