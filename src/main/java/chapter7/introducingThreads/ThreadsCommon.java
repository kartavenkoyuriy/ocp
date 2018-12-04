package chapter7.introducingThreads;

public class ThreadsCommon {

    public static void main(String[] args) {

    }

    //example of instantiate Runnable interface through Thread constructor
    private static void runnableInterfaceExample() {
        new Thread(() -> System.out.println("1")).start();

        new Thread(() -> {return;}).start();
        new Thread(() -> {}).start();
    }
}
