package chapter2.designPattern.singleton.lazy;

public class VisitorTicketTracker {
    private VisitorTicketTracker instance;

    private VisitorTicketTracker() {}

    public synchronized VisitorTicketTracker getInstance() {
        if (instance == null) {
            instance = new VisitorTicketTracker();
        }
        return instance;
    }
}
