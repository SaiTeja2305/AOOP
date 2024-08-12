import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Observer Pattern: Observer Interface
interface Observer {
    void update(String message);
}

// Observer Pattern: Subject Interface
interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers(String message);
}

// Observer Pattern: Concrete Subject
class Auction implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String itemName;
    private boolean isBiddingStarted;
    private boolean isBiddingEnded;

    public Auction(String itemName) {
        this.itemName = itemName;
        this.isBiddingStarted = false;
        this.isBiddingEnded = false;
    }

    public void startBidding() {
        if (!isBiddingStarted) {
            isBiddingStarted = true;
            notifyObservers("Bidding for " + itemName + " has started!");
        }
    }

    public void endBidding() {
        if (isBiddingStarted && !isBiddingEnded) {
            isBiddingEnded = true;
            notifyObservers("Bidding for " + itemName + " has ended!");
        }
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}

// Observer Pattern: Concrete Observer
class Bidder implements Observer {
    private String name;

    public Bidder(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received notification: " + message);
    }
}

// Main Application: Interacts with the user to manage the auction system
public class AuctionSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create an auction
        System.out.println("Enter the name of the auction item:");
        String itemName = scanner.nextLine();
        Auction auction = new Auction(itemName);

        // Manage bidders
        List<Bidder> bidders = new ArrayList<>();

        while (true) {
            System.out.println("Choose an action:");
            System.out.println("1. Add Bidder");
            System.out.println("2. Remove Bidder");
            System.out.println("3. Start Bidding");
            System.out.println("4. End Bidding");
            System.out.println("5. Exit");

            int action = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (action) {
                case 1: // Add Bidder
                    System.out.println("Enter bidder name:");
                    String bidderName = scanner.nextLine();
                    Bidder newBidder = new Bidder(bidderName);
                    auction.attach(newBidder);
                    bidders.add(newBidder);
                    System.out.println("Bidder " + bidderName + " added.");
                    break;

                case 2: // Remove Bidder
                    System.out.println("Enter bidder name:");
                    String removeBidderName = scanner.nextLine();
                    Bidder removeBidder = null;
                    for (Bidder bidder : bidders) {
                        if (bidder.getName().equals(removeBidderName)) {
                            removeBidder = bidder;
                            break;
                        }
                    }
                    if (removeBidder != null) {
                        auction.detach(removeBidder);
                        bidders.remove(removeBidder);
                        System.out.println("Bidder " + removeBidderName + " removed.");
                    } else {
                        System.out.println("Bidder not found.");
                    }
                    break;

                case 3: // Start Bidding
                    auction.startBidding();
                    break;

                case 4: // End Bidding
                    auction.endBidding();
                    break;

                case 5: // Exit
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
