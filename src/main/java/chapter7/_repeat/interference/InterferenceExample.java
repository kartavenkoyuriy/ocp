package chapter7._repeat.interference;

import java.util.concurrent.atomic.AtomicInteger;

public class InterferenceExample {
    private static final int hundredMillion = 100_000_000;
    private AtomicInteger counter = new AtomicInteger();

    public boolean stop(){
        return counter.incrementAndGet() > hundredMillion;
    }

    public void example() throws InterruptedException {
        InterferenceThread thread1 = new InterferenceThread(this);
        InterferenceThread thread2 = new InterferenceThread(this);
        thread1.start();
        thread2.start();
        thread1.join();
//        thread2.join();
        System.out.println("Expected:" + hundredMillion);
        System.out.println("Result:" + thread1.getI());
    }

    public static void main(String[] args) throws InterruptedException {
        InterferenceExample example = new InterferenceExample();
        example.example();
    }
}
