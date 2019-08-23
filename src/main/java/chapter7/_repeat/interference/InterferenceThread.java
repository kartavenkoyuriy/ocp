package chapter7._repeat.interference;

public class InterferenceThread extends Thread {
    private final InterferenceExample checker;
    private static int i;

    public InterferenceThread(InterferenceExample interferenceExample) {
        checker = interferenceExample;
    }

    //or do the static synchronized method
    public void increment() {
        synchronized (checker) {
            i++;
        }
    }

    @Override
    public void run() {
        while (!checker.stop()){
            //do something
            increment();
        }
    }

    public int getI() {
        return i;
    }
}
