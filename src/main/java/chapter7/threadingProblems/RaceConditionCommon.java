package chapter7.threadingProblems;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RaceConditionCommon {

    int counter = 0;

    private void increment() {
        counter++;
    }

    private void decrement() {
        counter--;
    }

    //race condition - state when many threads tries to access(modify) shared resources
    //
    //A race condition is an undesirable result that occurs when two tasks, which should be
    //completed sequentially, are completed at the same time.
    public static void main(String[] args) {
        ExecutorService executorService = null;
        RaceConditionCommon raceConditionCommon = new RaceConditionCommon();
        try {
            executorService = Executors.newFixedThreadPool(100);
            for (int i = 0; i < 100; i++) {
                executorService.submit(() -> {
                    raceConditionCommon.increment();
                    System.out.println(raceConditionCommon.counter);
                    raceConditionCommon.decrement();
                    System.out.println(raceConditionCommon.counter);
                });
            }
        } finally {
            if (executorService != null) {
                executorService.shutdown();
            }
        }
    }
}
