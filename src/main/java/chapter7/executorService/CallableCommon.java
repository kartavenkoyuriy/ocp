package chapter7.executorService;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class CallableCommon {

    private static void useCallable(Callable<Integer> expression) {
    }

    private static void useSupplier(Supplier<Integer> expression) {
    }

    private static void use(Callable<Integer> expression) {
    }

    private static void use(Supplier<Integer> expression) {
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = null;
        try {
            executorService = Executors.newSingleThreadExecutor();

            //...add tasks

        } finally {
            if (executorService != null) {
                executorService.shutdown();
            }
        }
        //after shutdown - wait some time until executor terminates
        executorService.awaitTermination(1, TimeUnit.NANOSECONDS);
        if (executorService.isTerminated()) {
            System.out.println("terminated");
        } else {
            System.out.println("not terminated");
        }
    }

    private static void supportCheckedExceptionsRunnableCallableExample() {
        ExecutorService executorService = null;
        try {
            executorService = Executors.newSingleThreadExecutor();

            //because of exist return type compiler treats this as Callable and exception may be thrown
            executorService.submit(() -> {
                Thread.sleep(1000);
                return null;
            });

//            because of no return type compiler treats this as Runnable and provide a compile error
//            executorService.submit(() -> {Thread.sleep(1000);});
        } finally {
            if (executorService != null) {
                executorService.shutdown();
            }
        }
    }

    //Callable and Runnable are interchangeable when lambda when no return type and no checked exceptions
    private static void simpleCallableFutureExample() throws InterruptedException, ExecutionException {
        ExecutorService executorService = null;
        try {
            executorService = Executors.newSingleThreadExecutor();
            Future<Integer> integerFuture = executorService.submit(() -> 2 + 2);
            System.out.println(integerFuture.get());
        } finally {
            if (executorService != null) {
                executorService.shutdown();
            }
        }
    }

    private static void supplierCallableExceptionExample() {
        //throwing an exception should be in curly brackets
        //useCallable(() -> throw new IOException());

        //Callable may throw a checked exception
        useCallable(() -> {
            throw new IOException();
        });

        //Supplier doesn't throw a checked Exception
        //useSupplier(() -> {throw new IOException();});

        //compiler doesn't take into account the method body
        //so
        //ambiguous method call
        //use(() -> {throw new IOException();});

        //explicit cast will make it compile
        use((Callable<Integer>) () -> {
            throw new IOException();
        });
    }
}
