package chapter7._repeat.executorservice;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceCommon {

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    private static void shotdownNowStopped() {
        ExecutorService service = null;
        try {
            service = Executors.newSingleThreadExecutor();
            service.execute(() -> {
                for (int i = 0; i < 1_000_000; i++) {
                    System.out.println(i);
                }
            });
            service.execute(() -> {
                for (int i = 0; i < 1_000_000; i++) {
                    System.out.println("a" + i);
                }
            });
        } finally {
            if (service != null) {
                //shutdownNow() is stopped the second task
                final List<Runnable> runnables = service.shutdownNow();
                System.out.println("runnables:" + runnables.size());
            }
        }
    }

    private static void shutdownNowIgnored() {
        ExecutorService service = null;
        try {
            service = Executors.newSingleThreadExecutor();
            service.execute(() -> {
                while (true) {
                }
            });
        } finally {
            if (service != null) {
                //shutdownNow() is only ATTEMPT to stop all running tasks, attempt may be ignored
                //returns a List<Runnable> of tasks that were submitted but never started
                final List<Runnable> runnables = service.shutdownNow();
                System.out.println("runnables:" + runnables.size());
            }
        }
    }

    private static void singleThreadExecutorBasic() {
        //tasks inside ExecutorService are ordered and executed sequentially
        //but unordered with main() thread
        ExecutorService service = null;
        try {
            service = Executors.newSingleThreadExecutor();
            System.out.println("start");
            service.execute(() -> System.out.println("inside start"));
            service.execute(() -> {
                for (int i = 0; i < 3; i++) {
                    System.out.println(i);
                }
            });
            service.execute(() -> System.out.println("inside finish"));
            System.out.println("finish");
        } finally {
            if (service != null) {
                service.shutdown();
            }
        }
    }

}
