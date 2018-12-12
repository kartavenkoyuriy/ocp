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

    //In general, you should extend the Thread class only under very specific circumstances,
    //such as when you are creating your own priority-based thread. In most situations, you
    //should implement the Runnable interface rather than extend the Thread class.

    //Despite the fact that the exam no longer focuses on creating threads by extending the
    //Thread class and implementing the Runnable interface, it is extremely common when
    //interviewing for a Java development position to be asked to explain the difference
    //between extending the Thread class and implementing Runnable. The following are some
    //reasons to prefer one method over the other in Java:
    //■ If you need to defne your own Thread rules upon which multiple tasks will rely, such
    //as a priority Thread, extending Thread may be preferable.
    //■ Since Java doesn't support multiple inheritance, extending Thread does not allow you to
    //extend any other class, whereas implementing Runnable lets you extend another class.
    //■ Implementing Runnable is often a better object-oriented design practice since it separates the task being performed from the Thread object performing it.
    //■ Implementing Runnable allows the class to be used by numerous Concurrency API
    //classes.
    //■■■If asked this question, you should answer it accurately. You should also mention that you
    //can now use the ExecutorService, which we will discuss in the next section, to perform
    //thread tasks without having to create Thread objects directly
    public static void main(String[] args) {
        threadsDontStartExample();

    }

    //threads don't start, because of wrong method invocation. they all invoke run method, and all methods will run in one 'main()' thread
    private static void threadsDontStartExample() {
        new PrintData().run();
        new Thread(new PrintData()).run();
        new InventoryThread().run();
    }

    private static void notGuaranteedOrderExample() {
        System.out.println("begin");
        new InventoryThread().start();
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
