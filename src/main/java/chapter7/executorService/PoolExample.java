package chapter7.executorService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class PoolExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
simpleFixedPoolExample();
    }

    private static void simpleFixedPoolExample() throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorService executorService = null;
        try {

            executorService = Executors.newFixedThreadPool(4);
            Future<String> submit = executorService.submit(() -> {
                for (int i = 0; i < 50_000; i++) {
                    System.out.println(i);
                }
                return "s";
            });
            Future<String> submit1 = executorService.submit(() -> {
                for (int i = 50_000; i < 100_000; i++) {
                    System.out.println(i);
                }
                return "s1";
            });
            Future<String> submit2 = executorService.submit(() -> {
                for (int i = 100_000; i < 150_000; i++) {
                    System.out.println(i);
                }
                return "s2";
            });
            Future<String> submit3 = executorService.submit(() -> {
                for (int i = 150_000; i < 200_000; i++) {
                    System.out.println(i);
                }
                return "s3";
            });
            System.out.println("s" + submit.get(10, TimeUnit.SECONDS));
            System.out.println("s1" + submit1.get(10, TimeUnit.SECONDS));
            System.out.println("s2" + submit2.get(10, TimeUnit.SECONDS));
            System.out.println("s3" + submit3.get(10, TimeUnit.SECONDS));
        } finally {
            if(executorService != null){
                executorService.shutdown();
            }
        }
    }

    private static void getAvailableProcessorNumber() {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
