import java.util.Scanner;

// Singleton Pattern: UserSession to manage user authentication
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

// Factory Method Pattern: Vehicle and VehicleFactory for different types of vehicles
abstract class Vehicle {
    public abstract void startRide();
}

class Car extends Vehicle {
    @Override
    public void startRide() {
        System.out.println("Car ride started.");
    }
}

class Bike extends Vehicle {
    @Override
    public void startRide() {
        System.out.println("Bike ride started.");
    }
}

class Scooter extends Vehicle {
    @Override
    public void startRide() {
        System.out.println("Scooter ride started.");
    }
}

abstract class VehicleFactory {
    public abstract Vehicle createVehicle();
}

class CarFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new Car();
    }
}

class BikeFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new Bike();
    }
}

class ScooterFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new Scooter();
    }
}

// Abstract Factory Pattern: PaymentMethod and PaymentFactory for different payment methods
abstract class PaymentMethod {
    public abstract void pay(double amount);
}

class CreditCardPayment extends PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using Credit Card.");
    }
}

class PayPalPayment extends PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using PayPal.");
    }
}

abstract class PaymentFactory {
    public abstract PaymentMethod createPaymentMethod();
}

class CreditCardFactory extends PaymentFactory {
    @Override
    public PaymentMethod createPaymentMethod() {
        return new CreditCardPayment();
    }
}

class PayPalFactory extends PaymentFactory {
    @Override
    public PaymentMethod createPaymentMethod() {
        return new PayPalPayment();
    }
}

// Main Application: Integrates all the patterns
public class RideSharingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Singleton pattern for user authentication
        UserSession session = UserSession.getInstance();

        // Login process
        System.out.println("Enter your username to login:");
        String username = scanner.nextLine();
        session.login(username);

        if (session.isLoggedIn()) {
            // Vehicle selection
            System.out.println("Select a vehicle for your ride:");
            System.out.println("1. Car");
            System.out.println("2. Bike");
            System.out.println("3. Scooter");

            int vehicleChoice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            VehicleFactory vehicleFactory;
            switch (vehicleChoice) {
                case 1:
                    vehicleFactory = new CarFactory();
                    break;
                case 2:
                    vehicleFactory = new BikeFactory();
                    break;
                case 3:
                    vehicleFactory = new ScooterFactory();
                    break;
                default:
                    System.out.println("Invalid choice. Defaulting to Car.");
                    vehicleFactory = new CarFactory();
                    break;
            }

            Vehicle vehicle = vehicleFactory.createVehicle();
            vehicle.startRide();

            // Payment selection
            System.out.println("Select a payment method:");
            System.out.println("1. Credit Card");
            System.out.println("2. PayPal");

            int paymentChoice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            PaymentFactory paymentFactory;
            switch (paymentChoice) {
                case 1:
                    paymentFactory = new CreditCardFactory();
                    break;
                case 2:
                    paymentFactory = new PayPalFactory();
                    break;
                default:
                    System.out.println("Invalid choice. Defaulting to Credit Card.");
                    paymentFactory = new CreditCardFactory();
                    break;
            }

            PaymentMethod payment = paymentFactory.createPaymentMethod();
            payment.pay(50.0); // Assume the ride cost is $50

            // Log out user
            session.logout();
        }

        scanner.close();
    }
}
