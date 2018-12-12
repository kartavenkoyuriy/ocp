package chapter7.introducingThreads;

public class ThreadSleep {

    private static int counter = 0;

    public static void main(String[] args) throws InterruptedException {

    }

    //While polling does prevent the CPU from being overwhelmed with a potentially infnite
    //loop, it does not guarantee when the loop will terminate.
    private static void pollingSleepExample() throws InterruptedException {
        new Thread(() -> {
            for (int i = 0; i < 1_000_000_000; i++) {
                counter++;
            }
        }).start();

        while (counter < 100){
            System.out.println("not reached" + counter);
            Thread.sleep(100);
        }
        System.out.println("reached" + counter);
    }

    //polling - intermittently checking data at some fixed interval.
    //here - no interval, not defined how many times will loop executed
    //and not defined what will be the final counter value
    private static void noPollingNoSleepExample() {
        new Thread(() -> {
            for (int i = 0; i < 1_000_000_000; i++) {
                counter++;
            }
        }).start();

        while (counter < 100){
            System.out.println("not reached" + counter);
        }
        System.out.println("reached" + counter);
    }
}
