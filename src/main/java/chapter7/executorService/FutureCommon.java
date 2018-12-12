package chapter7.executorService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureCommon {

    private static int count = 0;

    public static void main(String[] args) {
        ExecutorService executorService = null;
        try {
            executorService = Executors.newSingleThreadExecutor();
            Future<?> countFuture = executorService.submit(() -> {
                for (int i = 0; i < 1_000_000; i++) {
                    count++;
                }
            });
            //polling, but don't use the thread class directly
            //1 sec - reached
            //1 micro sec - not reached
            //max for TimeUnit is DAYS
            countFuture.get(1, TimeUnit.SECONDS);
            System.out.println("reached");
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            System.out.println("not reached");
        } finally {
            if (executorService != null){
                executorService.shutdown();
            }
        }
    }

    private static void simpleFutureStateExample() {
        ExecutorService executorService = null;
        try {
            executorService = Executors.newSingleThreadExecutor();

            //zoo.get() is rely on the Runnable/Callable method - if Runnable than it always returns null
            Future<?> zoo = executorService.submit(() -> System.out.println("zoo"));
            //here:
            //zoo.get() - null
            //zoo.get(1, TimeUnit.NANOSECONDS);// - TimeoutException
            //zoo.get(1, TimeUnit.SECONDS);// - null
            //zoo.isDone() - true
            //zoo.isCanceled() - false
            //zoo.cancel(false) - false(because completed normally)
            //zoo.cancel(true) - false(because completed normally)
            System.out.println("finish");
        } /*catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        } */finally {
            if (executorService != null){
                executorService.shutdown();
            }
        }
    }
}
