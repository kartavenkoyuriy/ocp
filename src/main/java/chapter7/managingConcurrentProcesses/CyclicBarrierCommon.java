package chapter7.managingConcurrentProcesses;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierCommon {

    private void removeAnimals() {
        System.out.println("Removing animals");
    }

    private void cleanPen() {
        System.out.println("Cleaning the pen");
    }

    private void addAnimals() {
        System.out.println("Adding animals");
    }

    private void performTask() {
        removeAnimals();
        cleanPen();
        addAnimals();
    }

    private void performTaskCyclicBarrier(CyclicBarrier c1, CyclicBarrier c2) {
        try {
            removeAnimals();
            c1.await();
            cleanPen();
            c2.await();
            addAnimals();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        reusingCyclicBarrierExample();
    }

    private static void reusingCyclicBarrierExample() {
        ExecutorService executorService = null;
        try {
            executorService = Executors.newFixedThreadPool(4);
            CyclicBarrierCommon c = new CyclicBarrierCommon();
            //After a CyclicBarrier is broken, all threads are released
            //and the number of threads waiting on the CyclicBarrier goes back to zero.
            //At this point, the CyclicBarrier may be
            //used again for a new set of waiting threads. For example, if our CyclicBarrier limit is 5
            //and we have 15 threads that call await(), then the CyclicBarrier will be activated a total
            //of three times.
            CyclicBarrier c1 = new CyclicBarrier(2);
            CyclicBarrier c2 = new CyclicBarrier(2, () -> System.out.println("PEN CLEANED"));
            for (int i = 0; i < 4; i++) {
                executorService.submit(() -> c.performTaskCyclicBarrier(c1, c2));
            }
        } finally {
            if (executorService != null) {
                executorService.shutdown();
            }
        }
    }

    //CyclicBarriers are used in programs in which we have a fixed number of threads
    //that must wait for each other to reach a common point before continuing execution.
    //
    //the thread pool should be at least large as a cyclic barrier(or bigger)
    //otherwise the code hangs
    //
    private static void sequentiallyExecutionWithCyclicBarrierExample() {
        ExecutorService executorService = null;
        try {
            executorService = Executors.newFixedThreadPool(4);
            CyclicBarrierCommon c = new CyclicBarrierCommon();
            CyclicBarrier c1 = new CyclicBarrier(4);
            CyclicBarrier c2 = new CyclicBarrier(4, () -> System.out.println("PEN CLEANED"));
            for (int i = 0; i < 4; i++) {
                executorService.submit(() -> c.performTaskCyclicBarrier(c1, c2));
            }
        } finally {
            if (executorService != null) {
                executorService.shutdown();
            }
        }
    }

    private static void standardThreadPoolExecutionExample() {
        ExecutorService executorService = null;
        try {
            executorService = Executors.newFixedThreadPool(4);
            CyclicBarrierCommon c = new CyclicBarrierCommon();
            for (int i = 0; i < 4; i++) {
                executorService.submit(() -> c.performTask());
            }
        } finally {
            if (executorService != null) {
                executorService.shutdown();
            }
        }
    }

}
