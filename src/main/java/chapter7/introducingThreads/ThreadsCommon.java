package chapter7.introducingThreads;

class PrintData implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("printing:" + i);
        }
    }
}

class InventoryThread extends Thread {
    @Override
    public void run() {
        System.out.println("zoo");
    }
}

public class ThreadsCommon {

    //a Thread instance will execute can be done two ways in Java:
    //■ Provide a Runnable object or lambda expression to the Thread constructor.
    //■ Create a class that extends Thread and overrides the run() method

    public static void main(String[] args) {
        notGuaranteedOrderExample();

    }

    private static void notGuaranteedOrderExample() {
        System.out.println("begin");
        new Thread(new PrintData()).start();
        new InventoryThread().start();
        System.out.println("end");
    }

    private static void commonThreadConstructorsExample() {
        new Thread(new PrintData()).start();
        new InventoryThread().start();
    }

    //example of instantiate Runnable interface through Thread constructor
    private static void runnableInterfaceExample() {
        new Thread(() -> System.out.println("1")).start();

        new Thread(() -> {
            return;
        }).start();
        new Thread(() -> {
        }).start();
    }
}
