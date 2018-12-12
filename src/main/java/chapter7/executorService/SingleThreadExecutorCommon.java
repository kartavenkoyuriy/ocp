package chapter7.executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutorCommon {

    public static void main(String[] args) {
        shutdownNowExplanation();
    }

    //shutdownNow() ATTEMPTS to stop all active tasks, never run waiting tasks
    //return list of tasks that were never start
    //
    //It is possible to create a thread that will never terminate, so any attempt to interrupt it may be ignored - HOW?
    private static void shutdownNowExplanation() {
        ExecutorService executorService = null;
        try {
            executorService = Executors.newSingleThreadExecutor();
            // ...do some  work

        } finally {
            if (executorService != null) {
                executorService.shutdownNow();
            }
        }
    }

    //shutdown - reject(RejectedExecutionException) new submitted tasks, process early submitted tasks
    //
    //active(before shutdown()) - executor works
    //isShutdown() - false
    //isTerminated() - false
    //
    //shutting down(after shutdown()) - executor process lasts submitted tasks and terminate
    //isShutdown() - true
    //isTerminated() - false
    //
    //shutdown(after all task finished) - executor finished all tasks and shuted down
    //isShutdown() - true
    //isTerminated() - true
    private static void shutdownExplanation() {
        ExecutorService executorService = null;
        try {
            executorService = Executors.newSingleThreadExecutor();
            // ...do some  work

        } finally {
            //Although this solution works for thread executors that are used once and thrown away,
            //it does not work for thread executors that are persistent throughout the life of the application.
            //For example, you can create a static instance of a thread executor and have all
            //processes share it.
            //
            //In such a scenario, you would need to defne a static method that can be called anytime the
            //user signals that they wish to exit the program. Remember that failure to shut down a thread
            //executor after at least one thread has been created will result in the program hanging.
            if (executorService != null) {
                executorService.shutdown();
            }
            //RejectedExecutionException
//            executorService.execute(() -> System.out.println("bla"));
        }
    }

    //all threads in executorService are ordered,
    //because of calling newSingleThreadExecutor() - so created threads will be processed sequentially in one thread
    //but independent from main() thread
    //here - results are guaranteed to be executed in the order in which they are added to the executor service
    private static void executorSingleThreadServiceNoOrderGuaranteeExample() {
        ExecutorService executorService = null;
        try {
            executorService = Executors.newSingleThreadExecutor();

            System.out.println("begin");
            executorService.execute(() -> System.out.println("zoo"));
            executorService.execute(() -> {
                for (int i = 0; i < 3; i++) {
                    System.out.println("record:" + i);
                }
            });
            executorService.execute(() -> System.out.println("zoo"));
            System.out.println("end");
        } finally {
            if (executorService != null) {
                executorService.shutdown();
            }
        }
    }

}
