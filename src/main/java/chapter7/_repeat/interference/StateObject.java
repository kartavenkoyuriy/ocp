package chapter7._repeat.interference;

public class StateObject {
    private int i;

    public synchronized void increment(){
        i++;
    }

    public int getI() {
        return i;
    }
}
