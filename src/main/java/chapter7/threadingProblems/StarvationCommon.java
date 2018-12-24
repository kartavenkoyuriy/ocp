package chapter7.threadingProblems;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StarvationCommon {
    //Starvation describes a situation where a thread is unable to gain regular access to shared resources and is unable to make progress.
    //This happens when shared resources are made unavailable for long periods by "greedy" threads.
    //For example, suppose an object provides a synchronized method that often takes a long time to return.
    //If one thread invokes this method frequently, other threads that also need frequent synchronized access
    //to the same object will often be blocked.
    public static void main(String[] args) {
        standardStarvationExample();
    }

    //because of 'synchronized' first thread can't release the work method for other threads
    private static void standardStarvationExample() {
        StarvationCommon starvationCommon = new StarvationCommon();
        for (int i = 0; i < 10; i++) {
            new Thread(starvationCommon::work).start();
        }
    }

    //thread with the lowest priority will have the rarest frequency to execute
    private static void anotherStarvationExample() {
        for (int i = 0; i < 5; i++) {
            int counter = i;
            Thread thread0 = new Thread(() -> {
                while (true) {
                    System.out.println(counter + "works");
                }
            });
            thread0.setName("thread" + counter);
            thread0.setPriority(Thread.MAX_PRIORITY - counter);
            thread0.start();
        }
    }

    //my understanding of what starvation is(partly covered of standard definition)
    //1 and 2 works are sometimes change their execution order
    //1111111111112222222222221111111111111111
    private static void myUnderstandingStarvationExample() {
        ExecutorService executorService = null;
        try {
            executorService = Executors.newFixedThreadPool(2);
            executorService.submit(() -> {
                while (true) {
                    System.out.println("1 works");
                }
            });
            executorService.submit(() -> {
                while (true) {
                    System.out.println("2 works");
                }
            });
            executorService.submit(() -> {
                while (true) {
                    System.out.println("3 works");
                }
            });
            executorService.submit(() -> {
                while (true) {
                    System.out.println("4 works");
                }
            });
        } finally {
            if (executorService != null) {
                executorService.shutdown();
            }
        }
    }

    private synchronized void work() {
        String name = Thread.currentThread().getName();
        String fileName = name + ".txt";
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write("Thread " + name + " wrote this message");
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            System.out.println("Thread[" + name + "] is working");
        }
    }
}
