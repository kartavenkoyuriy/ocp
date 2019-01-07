package chapter7.examRepeating01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicClasses {
    //incrementAndGet
    //decrementAndGet
    //getAndIncrement
    //getAndDecrement
    AtomicInteger counterAI = new AtomicInteger(0);
    int counterI = 0;

    public static void main(String[] args) {

    }

    //also unordered
    //but incrementing synchronously, each number printed only once
    private static void atomicIntegerConcurrencyIncrementing() {
        ExecutorService executorService = null;
        AtomicClasses classes = new AtomicClasses();
        try {
            executorService = Executors.newFixedThreadPool(100);
            for (int i = 0; i < 100; i++) {
                executorService.submit(() -> {
                    System.out.println(classes.counterAI.incrementAndGet());
                });
            }
        } finally {
            if (executorService != null) {
                executorService.shutdown();
            }
        }
        System.out.println("!"+classes.counterAI);
    }

    //counter increments unpredictable because of race condition
    //unordered
    private static void simpleCounterConcurrentIncrementing() {
        ExecutorService executorService = null;
        AtomicClasses classes = new AtomicClasses();
        try {
            executorService = Executors.newFixedThreadPool(100);
            for (int i = 0; i < 100; i++) {
                executorService.submit(() -> {
                    classes.counterI++;
                    System.out.println(classes.counterI);
                });
            }
        } finally {
            if (executorService != null) {
                executorService.shutdown();
            }
        }
        System.out.println("!"+classes.counterI);
    }
}
