package chapter2.designPattern.singleton.staticDouble;

public class VisitorTicketTracker {
    private static volatile VisitorTicketTracker instance;

    private VisitorTicketTracker() {
    }

    //double-checking locking pattern
    //we frst test if synchronization is needed before actually acquiring any locks
    public static VisitorTicketTracker getInstance(){
        if(instance == null){
            synchronized (VisitorTicketTracker.class){
                if(instance == null){
                    instance = new VisitorTicketTracker();
                }
            }
        }
        return instance;
    }
}
