package chapter7._repeat.executorservice;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureExample {

    public static int counter = 0;

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorService service = null;
        Future<?> result = null;
        try {
            service = Executors.newSingleThreadExecutor();
            result = service.submit(() -> {
                for (int i = 0; i < 1_000_000_000; i++) {
                    counter++;
                }
            });
            result.get(1, TimeUnit.NANOSECONDS);
            System.out.println("reached");
        } catch (TimeoutException e) {
            //if
            //result.get(1, TimeUnit.NANOSECONDS);
            //then
            //result.isDone() - false
            //result.cancel(true/false) - true
            //result.isCancelled() - true
            System.out.println(result.isDone());
            System.out.println(result.cancel(true));
            System.out.println(result.isCancelled());
            System.out.println("not reached");
        } finally {
            if (service != null) {
                service.shutdown();
            }
        }
    }
}
