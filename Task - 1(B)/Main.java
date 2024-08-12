import java.util.Scanner;

class UserSession {
    private static UserSession instance = null;
    private boolean isLoggedIn;
    private String username;

    private UserSession() {
        this.isLoggedIn = false;
        this.username = "";
    }

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void login(String username) {
        if (!isLoggedIn) {
            this.username = username;
            this.isLoggedIn = true;
            System.out.println(username + " logged in successfully.");
        } else {
            System.out.println("User already logged in.");
        }
    }

    public void logout() {
        if (isLoggedIn) {
            this.username = "";
            this.isLoggedIn = false;
            System.out.println("User logged out successfully.");
        } else {
            System.out.println("No user is logged in.");
        }
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public String getUsername() {
        return username;
    }
}

class Bank {
    private double balance;

    public Bank(double initialBalance) {
        this.balance = initialBalance;
    }

    public void viewBalance() {
        UserSession session = UserSession.getInstance();
        if (session.isLoggedIn()) {
            System.out.println("Balance for " + session.getUsername() + ": $" + balance);
        } else {
            System.out.println("Please log in to view balance.");
        }
    }

    public void deposit(double amount) {
        UserSession session = UserSession.getInstance();
        if (session.isLoggedIn()) {
            balance += amount;
            System.out.println("Deposited $" + amount + ". New balance: $" + balance);
        } else {
            System.out.println("Please log in to deposit money.");
        }
    }

    public void withdraw(double amount) {
        UserSession session = UserSession.getInstance();
        if (session.isLoggedIn()) {
            if (balance >= amount) {
                balance -= amount;
                System.out.println("Withdrew $" + amount + ". New balance: $" + balance);
            } else {
                System.out.println("Insufficient balance.");
            }
        } else {
            System.out.println("Please log in to withdraw money.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Bank myBank = new Bank(500);
        UserSession session = UserSession.getInstance();
        Scanner scanner = new Scanner(System.in);

        session.login("JohnDoe");

        while (true) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. View Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Logout and Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    myBank.viewBalance();
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    myBank.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    myBank.withdraw(withdrawAmount);
                    break;
                case 4:
                    session.logout();
                    System.out.println("Exiting the application.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
