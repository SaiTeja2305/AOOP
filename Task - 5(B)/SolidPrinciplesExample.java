import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// **Single Responsibility Principle (SRP)**

// Class responsible for employee details
class Employee {
    private String name;
    private String role;

    public Employee(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}

// Class responsible for salary calculation
class SalaryCalculator {
    public double calculateSalary(Employee employee) {
        switch (employee.getRole()) {
            case "Manager":
                return 80000;
            case "Developer":
                return 60000;
            case "Intern":
                return 20000;
            default:
                return 0;
        }
    }
}

// **Open/Closed Principle (OCP)**

// Abstract class for Shape
abstract class Shape {
    public abstract double calculateArea();
}

// Rectangle class
class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return width * height;
    }
}

// Circle class
class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

// **Liskov Substitution Principle (LSP)**

// Base class for Bird
class Bird {
    public void fly() {
        System.out.println("The bird is flying.");
    }
}

// Subclass that does not follow LSP
class Ostrich extends Bird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Ostriches cannot fly.");
    }
}

// **Interface Segregation Principle (ISP)**

// Worker interface
interface Worker {
    void work();
}

// Eater interface
interface Eater {
    void eat();
}

// Robot class that implements only Worker
class Robot implements Worker {
    @Override
    public void work() {
        System.out.println("The robot is working.");
    }
}

// Human class that implements both Worker and Eater
class Human implements Worker, Eater {
    @Override
    public void work() {
        System.out.println("The human is working.");
    }

    @Override
    public void eat() {
        System.out.println("The human is eating.");
    }
}

// **Dependency Inversion Principle (DIP)**

// MessageService interface
interface MessageService {
    void sendMessage(String message);
}

// EmailService class implementing MessageService
class EmailService implements MessageService {
    @Override
    public void sendMessage(String message) {
        System.out.println("Sending Email: " + message);
    }
}

// SMSService class implementing MessageService
class SMSService implements MessageService {
    @Override
    public void sendMessage(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

// Application class depending on MessageService interface
class MyApplication {
    private MessageService messageService;

    public MyApplication(MessageService messageService) {
        this.messageService = messageService;
    }

    public void send(String message) {
        messageService.sendMessage(message);
    }
}

// **Main class to interact with the user**

public class SolidPrinciplesExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nChoose a SOLID Principle to demonstrate:");
            System.out.println("1. Single Responsibility Principle (SRP)");
            System.out.println("2. Open/Closed Principle (OCP)");
            System.out.println("3. Liskov Substitution Principle (LSP)");
            System.out.println("4. Interface Segregation Principle (ISP)");
            System.out.println("5. Dependency Inversion Principle (DIP)");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: // SRP
                    System.out.println("Enter employee name:");
                    String name = scanner.nextLine();

                    System.out.println("Enter employee role (Manager, Developer, Intern):");
                    String role = scanner.nextLine();

                    Employee employee = new Employee(name, role);
                    SalaryCalculator salaryCalculator = new SalaryCalculator();

                    double salary = salaryCalculator.calculateSalary(employee);
                    System.out.println("The salary for " + name + " is $" + salary);
                    break;

                case 2: // OCP
                    System.out.println("Choose a shape (Rectangle, Circle):");
                    String shapeType = scanner.nextLine();

                    Shape shape = null;

                    if (shapeType.equalsIgnoreCase("Rectangle")) {
                        System.out.println("Enter width:");
                        double width = scanner.nextDouble();
                        System.out.println("Enter height:");
                        double height = scanner.nextDouble();
                        shape = new Rectangle(width, height);
                    } else if (shapeType.equalsIgnoreCase("Circle")) {
                        System.out.println("Enter radius:");
                        double radius = scanner.nextDouble();
                        shape = new Circle(radius);
                    }

                    if (shape != null) {
                        System.out.println("The area is " + shape.calculateArea());
                    } else {
                        System.out.println("Invalid shape.");
                    }
                    break;

                case 3: // LSP
                    System.out.println("Choose a bird (Bird, Ostrich):");
                    String birdType = scanner.nextLine();

                    Bird bird = null;

                    if (birdType.equalsIgnoreCase("Bird")) {
                        bird = new Bird();
                    } else if (birdType.equalsIgnoreCase("Ostrich")) {
                        bird = new Ostrich();
                    }

                    if (bird != null) {
                        try {
                            bird.fly();
                        } catch (UnsupportedOperationException e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("Invalid bird type.");
                    }
                    break;

                case 4: // ISP
                    System.out.println("Choose a worker type (Robot, Human):");
                    String workerType = scanner.nextLine();

                    if (workerType.equalsIgnoreCase("Robot")) {
                        Worker robot = new Robot();
                        robot.work();
                    } else if (workerType.equalsIgnoreCase("Human")) {
                        Human human = new Human();
                        human.work();
                        human.eat();
                    } else {
                        System.out.println("Invalid worker type.");
                    }
                    break;

                case 5: // DIP
                    System.out.println("Choose a message service (Email, SMS):");
                    String serviceType = scanner.nextLine();

                    MessageService messageService = null;

                    if (serviceType.equalsIgnoreCase("Email")) {
                        messageService = new EmailService();
                    } else if (serviceType.equalsIgnoreCase("SMS")) {
                        messageService = new SMSService();
                    }

                    if (messageService != null) {
                        MyApplication app = new MyApplication(messageService);
                        System.out.println("Enter your message:");
                        String message = scanner.nextLine();
                        app.send(message);
                    } else {
                        System.out.println("Invalid message service.");
                    }
                    break;

                case 6: // Exit
                    scanner.close();
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
