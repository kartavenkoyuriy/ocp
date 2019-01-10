package chapter7.executorService;

import java.util.concurrent.*;

public class SchedulingCommon {
    public static void main(String[] args) {
    }

    private static void scheduledShutdownExample() {
        ScheduledExecutorService scheduledExecutorService = null;
        try {
            scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

            //initial delay and delay from start to start
            scheduledExecutorService.scheduleAtFixedRate(() -> System.out.println("fixed runnable rate 5 sec"), 0, 5, TimeUnit.SECONDS);
            //initial delay and delay from end of previous to start next
            scheduledExecutorService.scheduleWithFixedDelay(() -> System.out.println("fixed runnable delay 2 sec"), 0, 2,TimeUnit.SECONDS);


        } finally {
            //when finally block is empty - tasks executed correctly with schedule
            //when shutdown() executor in finally block - only first task executed
            //so
            //executor starts new thread with task and go ahead within its main executor task
//            if(scheduledExecutorService != null){
//                scheduledExecutorService.shutdown();
//            }
        }
    }

    //deferred execution
    //both for runnable and callable
    private static void scheduleExample() {
        ScheduledExecutorService scheduledExecutorService = null;
        try {
            scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

            //ScheduledExecutorService is an descendant of ExecutorService
            //ExecutorService executorService = scheduledExecutorService;

            ScheduledFuture<?> runnable = scheduledExecutorService.schedule(() -> System.out.println("runnable"), 10, TimeUnit.SECONDS);
            ScheduledFuture<String> callable = scheduledExecutorService.schedule(() -> {
                System.out.println("callable");
                return "callable";}, 5, TimeUnit.SECONDS);
        } finally {
            if(scheduledExecutorService != null){
                scheduledExecutorService.shutdown();
            }
        }
    }
}
