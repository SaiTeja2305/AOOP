import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// **Interfaces for SOLID Principles**

interface Manageable {
    String getId();
    String getName();
}

interface Borrowable {
    boolean isAvailable();
    void borrow();
    void returnItem();
}

// **Single Responsibility Principle (SRP)**

// Employee class
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

// SalaryCalculator class
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

// Shape class
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

// Bird class
class Bird {
    public void fly() {
        System.out.println("The bird is flying.");
    }
}

// Ostrich class
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

// Robot class
class Robot implements Worker {
    @Override
    public void work() {
        System.out.println("The robot is working.");
    }
}

// Human class
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

// EmailService class
class EmailService implements MessageService {
    @Override
    public void sendMessage(String message) {
        System.out.println("Sending Email: " + message);
    }
}

// SMSService class
class SMSService implements MessageService {
    @Override
    public void sendMessage(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

// MyApplication class
class MyApplication {
    private MessageService messageService;

    public MyApplication(MessageService messageService) {
        this.messageService = messageService;
    }

    public void send(String message) {
        messageService.sendMessage(message);
    }
}

// **Library Management System**

class Book implements Manageable, Borrowable {
    private String id;
    private String name;
    private boolean available;

    public Book(String id, String name) {
        this.id = id;
        this.name = name;
        this.available = true;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public void borrow() {
        if (available) {
            available = false;
            System.out.println("Book borrowed: " + name);
        } else {
            System.out.println("Book is not available.");
        }
    }

    @Override
    public void returnItem() {
        if (!available) {
            available = true;
            System.out.println("Book returned: " + name);
        } else {
            System.out.println("Book was not borrowed.");
        }
    }
}

class Member implements Manageable {
    private String id;
    private String name;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}

class Library {
    private Map<String, Book> books = new HashMap<>();
    private Map<String, Member> members = new HashMap<>();

    public void addBook(Book book) {
        books.put(book.getId(), book);
        System.out.println("Book added: " + book.getName());
    }

    public void addMember(Member member) {
        members.put(member.getId(), member);
        System.out.println("Member added: " + member.getName());
    }

    public void borrowBook(String bookId) {
        Book book = books.get(bookId);
        if (book != null) {
            book.borrow();
        } else {
            System.out.println("Book not found.");
        }
    }

    public void returnBook(String bookId) {
        Book book = books.get(bookId);
        if (book != null) {
            book.returnItem();
        } else {
            System.out.println("Book not found.");
        }
    }

    public void listBooks() {
        System.out.println("Books in the library:");
        for (Book book : books.values()) {
            System.out.println("ID: " + book.getId() + ", Name: " + book.getName() + ", Available: " + book.isAvailable());
        }
    }

    public void listMembers() {
        System.out.println("Library members:");
        for (Member member : members.values()) {
            System.out.println("ID: " + member.getId() + ", Name: " + member.getName());
        }
    }
}

// **Main Class**

public class CombinedLibraryAndSOLID {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Library Management System");
            System.out.println("2. Demonstrate SOLID Principles");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Library Management System
                    while (true) {
                        System.out.println("\nLibrary Management System");
                        System.out.println("1. Add Book");
                        System.out.println("2. Add Member");
                        System.out.println("3. Borrow Book");
                        System.out.println("4. Return Book");
                        System.out.println("5. List Books");
                        System.out.println("6. List Members");
                        System.out.println("7. Back to Main Menu");

                        int libraryChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        switch (libraryChoice) {
                            case 1: // Add Book
                                System.out.println("Enter book ID:");
                                String bookId = scanner.nextLine();
                                System.out.println("Enter book name:");
                                String bookName = scanner.nextLine();
                                library.addBook(new Book(bookId, bookName));
                                break;

                            case 2: // Add Member
                                System.out.println("Enter member ID:");
                                String memberId = scanner.nextLine();
                                System.out.println("Enter member name:");
                                String memberName = scanner.nextLine();
                                library.addMember(new Member(memberId, memberName));
                                break;

                            case 3: // Borrow Book
                                System.out.println("Enter book ID to borrow:");
                                String borrowBookId = scanner.nextLine();
                                library.borrowBook(borrowBookId);
                                break;

                            case 4: // Return Book
                                System.out.println("Enter book ID to return:");
                                String returnBookId = scanner.nextLine();
                                library.returnBook(returnBookId);
                                break;

                            case 5: // List Books
                                library.listBooks();
                                break;

                            case 6: // List Members
                                library.listMembers();
                                break;

                            case 7: // Back to Main Menu
                                break;

                            default:
                                System.out.println("Invalid choice. Please try again.");
                                break;
                        }

                        if (libraryChoice == 7) {
                            break;
                        }
                    }
                    break;

                case 2: // Demonstrate SOLID Principles
                    while (true) {
                        System.out.println("\nChoose a SOLID Principle to demonstrate:");
                        System.out.println("1. Single Responsibility Principle (SRP)");
                        System.out.println("2. Open/Closed Principle (OCP)");
                        System.out.println("3. Liskov Substitution Principle (LSP)");
                        System.out.println("4. Interface Segregation Principle (ISP)");
                        System.out.println("5. Dependency Inversion Principle (DIP)");
                        System.out.println("6. Back to Main Menu");

                        int solidChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        switch (solidChoice) {
                            case 1: // SRP
                                System.out.println("Enter employee name:");
                                String name = scanner.nextLine();
                                System.out.println("Enter employee role (Manager, Developer, Intern):");
                                String role = scanner.nextLine();
                                Employee employee = new Employee(name, role);
                                SalaryCalculator calculator = new SalaryCalculator();
                                double salary = calculator.calculateSalary(employee);
                                System.out.println("Salary for " + employee.getName() + ": " + salary);
                                break;

                            case 2: // OCP
                                System.out.println("Enter rectangle width:");
                                double rectWidth = scanner.nextDouble();
                                System.out.println("Enter rectangle height:");
                                double rectHeight = scanner.nextDouble();
                                Shape rectangle = new Rectangle(rectWidth, rectHeight);
                                System.out.println("Area of Rectangle: " + rectangle.calculateArea());

                                System.out.println("Enter circle radius:");
                                double radius = scanner.nextDouble();
                                Shape circle = new Circle(radius);
                                System.out.println("Area of Circle: " + circle.calculateArea());
                                break;

                            case 3: // LSP
                                Bird bird = new Bird();
                                bird.fly();

                                try {
                                    Ostrich ostrich = new Ostrich();
                                    ostrich.fly();
                                } catch (UnsupportedOperationException e) {
                                    System.out.println(e.getMessage());
                                }
                                break;

                            case 4: // ISP
                                Robot robot = new Robot();
                                robot.work();

                                Human human = new Human();
                                human.work();
                                human.eat();
                                break;

                            case 5: // DIP
                                System.out.println("Choose message service (1 for Email, 2 for SMS):");
                                int serviceChoice = scanner.nextInt();
                                scanner.nextLine(); // Consume newline

                                MessageService messageService = (serviceChoice == 1) ? new EmailService() : new SMSService();
                                MyApplication app = new MyApplication(messageService);

                                System.out.println("Enter message to send:");
                                String message = scanner.nextLine();
                                app.send(message);
                                break;

                            case 6: // Back to Main Menu
                                break;

                            default:
                                System.out.println("Invalid choice. Please try again.");
                                break;
                        }

                        if (solidChoice == 6) {
                            break;
                        }
                    }
                    break;

                case 3: // Exit
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
