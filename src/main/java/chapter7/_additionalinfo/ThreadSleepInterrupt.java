package chapter7._additionalinfo;

public class ThreadSleepInterrupt {

    public static void main(String[] args) {
        notInterruptedIfNotSleepExample();
    }

    private static void notInterruptedIfNotSleepExample() {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                System.out.println(i);
            }
            //just flag will set to true
            System.out.println(Thread.currentThread().isInterrupted());
        });
        thread.start();

        thread.interrupt();
    }

    private static void sleepInterruptedExample() {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }
        });
        thread.start();

        thread.interrupt();
    }


}
